import {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {addBook} from "../../../reducers/book.reducer";
import BookApi from "../../reusable/apis/bookApi";
import { BookInfoWrapper, BooksWrapper} from "./Book.style";
import LoadingOverlay from "../../reusable/loading-overlay/LoadingOverlay";
import useModal from "../../../hooks/useModal";
import BookInformationModal from '../books/BookInformationModal';
import BooksInfo from "../../reusable/info-card/BooksInfo";
import Paginate from "../../reusable/paginate/Paginate";


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
        const data = await res.json();
        setLoading(false);

        return data;
    };

    const handlePageClick = async ({selected}) => {
        setLoading(true)
        const data = await fetchBooks(selected, pageSize);

        setCurrentPageIndex(selected * pageSize);
        dispatch(addBook(data.books))
    };
    const {modal, setModalData} = useModal({
        Component: BookInformationModal
    });

    return (
        <BooksWrapper>
            {modal}
            {loading && <LoadingOverlay/>}
            {books && books.length ? <BookInfoWrapper>
                    {books.map((bookInfoItem, index) => {
                        return <BooksInfo
                            key={`bookInfo-${index}`}
                            book={bookInfoItem}
                            setModalData={() => setModalData({book: bookInfoItem})}/>
                    })
                    }
                </BookInfoWrapper>
                : <div>There is currently no book in the library.</div>}

            <Paginate
                handlePageClick={handlePageClick}
                pageCount={pageCount}
                props={books}/>

        </BooksWrapper>)
}


export default Books;