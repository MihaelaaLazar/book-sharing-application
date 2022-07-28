import {useParams} from "react-router-dom";
import {useEffect} from "react";
import BookApi from "../reusable/apis/bookApi";
import {useDispatch, useSelector} from "react-redux";
import {addResults} from "../../reducers/searchResults.reducer";
import {useState} from "react";
import {addBook} from "../../reducers/book.reducer";
import {
    BookInfoWrapper,
    BooksWrapper,
} from "../user-page/books/Book.style";
import LoadingOverlay from "../reusable/loading-overlay/LoadingOverlay";
import Paginate from "../reusable/paginate/Paginate";
import BooksInfo from "../reusable/modal/BooksInfo";
import useModal from "../../hooks/useModal";
import SearchBookModal from "./modal-search/SearchBookModal";

const SearchResults = () => {
    const params = useParams();
    const searchResults = useSelector(state => state.searchResults)
    const dispatch = useDispatch();
    const [pageCount, setPageCount] = useState(0);
    const [currentPageIndex, setCurrentPageIndex] = useState(0);
    const [loading, setLoading] = useState(false);

    const bookInfo = searchResults.searchResult;


    let pageSize = 3;

    useEffect(() => {
        const fetchData = async () => {
            const {query} = params;
            const res = await searchBooks(query, currentPageIndex, pageSize);
            setPageCount(Math.ceil(res.totalCount / pageSize));
            dispatch(addResults(res))
        }
        fetchData();
    }, [])

    const searchBooks = async (_query, currentPage, _items) => {
        const res = await BookApi.searchBook(_query, currentPage, pageSize);
        const data = await res.json();
        setLoading(false)
        return data;
    };

    const handlePageClick = async ({query, selected}) => {
        setLoading(true)
        const data = await searchBooks(query, selected, pageSize);
        setCurrentPageIndex(selected * pageSize);
        dispatch(addBook(data.books))
    };

    const {modal, setModalData} = useModal({
        Component: SearchBookModal
    });

    return (
        <BooksWrapper>
            {modal}
            {loading && <LoadingOverlay/>}
            {bookInfo && bookInfo.length
                ? <BookInfoWrapper>
                    {bookInfo.map((bookInfoItem, index) => {
                        return <BooksInfo
                            key={`bookInfo-${index}`}
                            book={bookInfoItem.bookRef ? bookInfoItem.bookRef.book : bookInfoItem}
                            setModalData={() => bookInfoItem.bookRef
                                ? setModalData({book: bookInfoItem.bookRef})
                                : setModalData({book: bookInfoItem})}
                        />
                    })}
                </BookInfoWrapper>
                : <div>No results found</div>}
            <Paginate
                handlePageClick={handlePageClick}
                pageCount={pageCount}
                props={bookInfo}/>
        </BooksWrapper>


    )
}
export default SearchResults;