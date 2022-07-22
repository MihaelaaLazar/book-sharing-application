import {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {addBook} from "../../../reducers/book.reducer";
import BookApi from "../../reusable/apis/bookApi";
import {
    BookCardWrapper, BookInfo, BookInfoWrapper, BooksWrapper, PaginateIconWrapper, ReactPaginateWrapper
} from "./Book.style";
import {faChevronRight, faChevronLeft, faEllipsis} from '@fortawesome/free-solid-svg-icons';
import LoadingOverlay from "../../reusable/loading-overlay/LoadingOverlay";


const Books = () => {
    const [pageCount, setPageCount] = useState(0);
    const [currentPageIndex, setCurrentPageIndex] = useState(0);
    const [loading, setLoading] = useState(false);
    const dispatch = useDispatch();
    const books = useSelector(state => state.books);

    let pageSize = 3;

    useEffect(() => {
        const getBooks = async () => {
            const data = await fetchBooks(currentPageIndex, pageSize);
            const total = data.totalCount;
            setPageCount(Math.ceil(total / pageSize));
            dispatch(addBook(data.books))
            setLoading(false)
        };

        getBooks();
        setLoading(false)
    }, [pageSize]);

    const fetchBooks = async (currentPage, _items) => {
        const res = await BookApi.getAllBooksWithPagination(currentPage, pageSize);
        const data = res.json();
        setLoading(false)
        return data;
    };

    const handlePageClick = async ({selected}) => {
        setLoading(true)
        const data = await fetchBooks(selected, pageSize);
        setCurrentPageIndex(selected * pageSize);
        dispatch(addBook(data.books))
    };

    return (

        <BooksWrapper>
            {loading && <LoadingOverlay/>}
            {books && books.length ?
                <BookInfoWrapper>
                    {books.map((book) => {
                        return (<BookCardWrapper key={book.bookId}>
                            <BookInfo>
                                <img src={book.imageUrl} alt={"img"}/>
                                <table>
                                    <tbody>
                                    <tr>
                                        <td>Title</td>
                                        <td>{book.title}</td>
                                    </tr>
                                    <tr>
                                        <td>Author</td>
                                        <td>{book.author}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </BookInfo>
                        </BookCardWrapper>);
                    })}


                </BookInfoWrapper>
                : <div>There is currently no book in the library.</div>}

            {books && books.length ?
                <ReactPaginateWrapper
                    previousLabel={<PaginateIconWrapper icon={faChevronLeft}/>}
                    nextLabel={<PaginateIconWrapper icon={faChevronRight}/>}
                    breakLabel={<PaginateIconWrapper icon={faEllipsis}/>}
                    pageCount={pageCount}
                    marginPagesDisplayed={2}
                    onPageChange={handlePageClick}
                    activeClassName={"active"}
                />
                : null}
        </BooksWrapper>)
}


export default Books;