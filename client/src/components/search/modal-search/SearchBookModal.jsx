import {faXmark} from "@fortawesome/free-solid-svg-icons";
import {
    BookAuthor,
    BookDescription,
    BookDetails,
    BookTitle,
    BookTitleWrapper,
    CloseButton,
    ModalCard,
    ModalContent,
    ModalThumbnail,
    ModalWrapper,
} from "../../user-page/available-books/ModalStyle.style";
import {useEffect, useState} from "react";
import BookApi from "../../reusable/apis/bookApi";


const SearchBookModal = ({book, onClose}) => {
    const [bookDetails, setBookDetails] = useState({});

    useEffect(() => {
        const fetchBookDetails = async () => {
            const response = await BookApi.getBookByBookId(book.book.bookId);
            const _bookDetails = await response.json();
            setBookDetails(_bookDetails);
        }
        fetchBookDetails();
    }, [book]);



    return <ModalWrapper>
        <CloseButton icon={faXmark} onClick={onClose}/>
        <ModalCard>
            <ModalThumbnail>
                {book.book
                    ? <img src={book.book.imageUrl} alt="Book thumbnail"/>
                    : <img src={book.imageUrl} alt="Book thumbnail"/>
                }
            </ModalThumbnail>
            <ModalContent>
                <BookTitleWrapper>
                    <BookTitle>{book.book.title}</BookTitle>
                    <BookAuthor>{book.book.author}</BookAuthor>

                </BookTitleWrapper>
                <BookDescription>
                    <BookDescription>{book.book.description}</BookDescription>
                </BookDescription>
                <BookDetails>
                    <tbody>
                    <tr>
                        <td>Book owner:</td>
                        <td>{book.user.username}</td>
                    </tr>
                    </tbody>
                    {/*TODO: add book details, rent book button or extend time button */}
                </BookDetails>
            </ModalContent>
        </ModalCard>
    </ModalWrapper>
}

export default SearchBookModal;