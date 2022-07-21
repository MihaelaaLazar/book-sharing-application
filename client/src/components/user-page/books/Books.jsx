import {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {addBook} from "../../../reducers/book.reducer";
import BookApi from "../../reusable/apis/bookApi";
import {
    BookCardWrapper, BookInfo,
    BookInfoWrapper,
    BooksWrapper,
    PaginateIconWrapper,
    ReactPaginateWrapper
} from "./Book.style";
import {faChevronRight, faChevronLeft, faEllipsis} from '@fortawesome/free-solid-svg-icons';
import LoadingOverlay from "../../reusable/loading-overlay/LoadingOverlay";


const Books = () => {
    const [pageCount, setPageCount] = useState(0);
    const [currentPageIndex, setCurrentPageIndex] = useState(0);
    const [loading, setLoading] = useState(false);
    const dispatch = useDispatch();
    const books = useSelector(state => state.books);

    let items = 2;

    useEffect(() => {
        const getBooks = async () => {
            const data = await fetchBooks(currentPageIndex, items);
            const total = data.totalCount;
            setPageCount(Math.ceil(total / items));
            dispatch(addBook(data.books))
            setLoading(false)
        };

        getBooks();
        setLoading(false)
    }, [items]);

    const fetchBooks = async (currentPage, _items) => {
        const res = await BookApi.getAllBooksWithPagination(currentPage, items);
        const data = res.json();
        setLoading(false)
        return data;
    };

    const handlePageClick = async ({selected}) => {
        setLoading(true)
        const data = await fetchBooks(selected, items);
        setCurrentPageIndex(selected);
        dispatch(addBook(data.books))
    };
    return (

        <BooksWrapper>
            {loading && <LoadingOverlay/>}
            {books && <BookInfoWrapper>

                {books.slice(currentPageIndex * items, (currentPageIndex * items) + items).map((book) => {
                    return (<BookCardWrapper key={book.bookId}>
                        <BookInfo>
                            <img src={book.imageUrl} alt="placeholder"/>
                            <div><span>Title: </span> {book.title}</div>
                            <div><span>Author: </span> {book.author}</div>
                        </BookInfo>
                    </BookCardWrapper>);
                })}
            </BookInfoWrapper>

            }
            <ReactPaginateWrapper
                previousLabel={<PaginateIconWrapper icon={faChevronLeft}/>}
                nextLabel={<PaginateIconWrapper icon={faChevronRight}/>}
                breakLabel={<PaginateIconWrapper icon={faEllipsis}/>}
                pageCount={pageCount}
                marginPagesDisplayed={2}
                onPageChange={handlePageClick}
                activeClassName={"active"}
            />
        </BooksWrapper>);
}


export default Books;