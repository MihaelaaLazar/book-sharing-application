package com.endava.services;

import com.endava.cloudinary.CloudinaryService;
import com.endava.models.BookDto;
import com.endava.models.BooksForRentDto;
import com.endava.models.BooksRefDto;
import com.endava.models.RentedBooksDto;
import com.endava.repositories.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookRefRepo bookRefRepo;

    @Autowired
    private BooksForRentRepo booksForRentRepo;

    @Autowired
    private RentedBooksRepo rentedBooksRepo;

    @Autowired
    private CloudinaryService cloudinaryService;


    public List<BookDto> getAllBooks() {
        return bookRepo.findAll();
    }

    public ResponseEntity<?> createBook(UUID userId, BookDto book, MultipartFile file) {
        if (bookRepo.findByTitle(book.getTitle()).isPresent()) {
            return ResponseEntity
                    .status(400)
                    .body("Book already exists");
        } else {
            String url = cloudinaryService.uploadFile(file);
            book.setImageUrl(url);
            bookRepo.save(book);
            BooksRefDto booksRefDto = new BooksRefDto(null, userId, book.getBookId());
            bookRefRepo.save(booksRefDto);
            booksForRentRepo.save(new BooksForRentDto(null, booksRefDto));
            return ResponseEntity
                    .status(201)
                    .body("Book created.");
        }
    }

    public Stream<Object> getBooksByUserId(UUID userId) {
        List<BooksRefDto> booksRefDto = bookRefRepo.findByUserUserId(userId);
        return booksRefDto.stream()
                .map(bookRefDto -> bookRepo.findByBookId(bookRefDto.getBook().getBookId()));
    }

    public Stream<?> getBooksByTitleOrAuthor(String query) {
        List<BookDto> books = bookRepo.findByQuery(query);
        Stream<?> availableBooks = books.stream()
                .filter(book -> booksForRentRepo.findByBookId(book.getBookId()) != null);

        Stream<?> rentedBooks = books.stream()
                .filter(book -> rentedBooksRepo.findOneBookByBookId(book.getBookId()) != null)
                .map(book -> rentedBooksRepo.findOneBookByBookId(book.getBookId()))
                .filter(rentedBook -> rentedBook.getReturningDate() != null);

        return Stream.concat(availableBooks, rentedBooks);
    }

    public ResponseEntity<?> getBookByTitleOrAuthorWithPagination(String query, int page, int pageSize) {
        JSONObject responseBody = new JSONObject();

        Set<BookDto> books = bookRepo._findByQuery(query);

        Set<BookDto> availableBooks = books.stream()
                .filter(book -> booksForRentRepo.findByBookId(book.getBookId()) != null)
                .collect(Collectors.toSet());

        Set<RentedBooksDto> rentedBooks = books.stream()
                .filter(book -> rentedBooksRepo.findOneBookByBookId(book.getBookId()) != null)
                .map(book -> rentedBooksRepo.findOneBookByBookId(book.getBookId()))
                .filter(rentedBook -> rentedBook.getReturningDate() != null)
                .collect(Collectors.toSet());

        Set<?> allBooks = Stream.concat(rentedBooks.stream(), availableBooks.stream())
                .collect(Collectors.toSet());

        List<?> booksList = new ArrayList<>(allBooks);
        PagedListHolder<?> pagedListHolder = new PagedListHolder<>(booksList);


        long count = booksList.size();
        pagedListHolder.setPageSize(pageSize);
        pagedListHolder.setPage(page);

        responseBody.put("searchResult", pagedListHolder.getPageList());
        responseBody.put("totalCount", count);

        return ResponseEntity
                .status(200)
                .body(responseBody.toString());

    }

    public ResponseEntity<?> updateBook(UUID bookId, BookDto book) {
        BookDto bookDto = bookRepo.findByBookId(bookId);
        if (bookDto == null) {
            return
                    ResponseEntity
                            .status(404)
                            .body("Book not found.");
        }
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setDescription(book.getDescription());
        bookDto.setDateOfPublication(book.getDateOfPublication());
        bookRepo.save(bookDto);
        return ResponseEntity
                .status(201)
                .body("Book updated.");
    }

    public ResponseEntity<?> getBooksWithPagination(int page, int pageSize) {
        JSONObject responseBody = new JSONObject();
        long count = bookRepo.countByBookId();
        Page<BookDto> books = bookRepo.findAll(PageRequest.of(page, pageSize));

        responseBody.put("totalCount", count);
        responseBody.put("books", books.getContent());

        return ResponseEntity
                .status(200)
                .body(responseBody.toString());
    }

    public ResponseEntity<?> getBooksWithUserIdAndPagination(UUID userId, int page, int pageSize) {
        JSONObject responseBody = new JSONObject();

        List<BooksRefDto> booksDto = bookRefRepo.findAllByUserId(userId);
        Set<BookDto> books = booksDto.stream().map(bookRefDto ->
                bookRepo.findByBookId(bookRefDto.getBook().getBookId())).collect(Collectors.toSet());

        List<BooksForRentDto> booksForRentDto = booksForRentRepo.findAllByUserId(userId);
        Set<BookDto> _booksForRent = booksForRentDto.stream().map(bookForRent ->
                bookRepo.findByBookId(bookForRent.getBookRef().getBook().getBookId())).collect(Collectors.toSet());

        List<RentedBooksDto> rentedBooks = rentedBooksRepo.findAllByUserId(userId);
        Set<BookDto> _rentedBooks = rentedBooks.stream().map(rentedBooksDto ->
                bookRepo.findByBookId(rentedBooksDto.getBookRef().getBook().getBookId())).collect(Collectors.toSet());

        Set<BookDto> allBooks = Stream.concat(books.stream(), Stream.concat(_booksForRent.stream(), _rentedBooks.stream()))
                .collect(Collectors.toSet());

        List<BookDto> booksList = new ArrayList<>(allBooks);
        PagedListHolder<BookDto> pagedListHolder = new PagedListHolder<>(booksList);

        long count = booksList.size();
        pagedListHolder.setPageSize(pageSize);
        pagedListHolder.setPage(page);

        responseBody.put("books", pagedListHolder.getPageList());
        responseBody.put("totalCount", count);
        return ResponseEntity
                .status(200)
                .body(responseBody.toString());
    }

    public ResponseEntity<?> getBookByBookId(UUID bookId) {
        BookDto bookDto = bookRepo.findByBookId(bookId);
        RentedBooksDto rentedBook = rentedBooksRepo.findOneBookByBookId(bookId);
        BooksForRentDto booksForRentDto = booksForRentRepo.findByBookId(bookId);
        return ResponseEntity
                .status(200)
                .body(Stream.of(bookDto, rentedBook, booksForRentDto).collect(Collectors.toList()));
    }
}
