import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import BookApi from "../reusable/apis/bookApi";
import {useDispatch, useSelector} from "react-redux";
import {addResults} from "../../reducers/searchResults.reducer";
import {addBook} from "../../reducers/book.reducer";
import {
    BookInfoWrapper,
    BooksWrapper,
} from "../user-page/books/Book.style";
import LoadingOverlay from "../reusable/loading-overlay/LoadingOverlay";
import Paginate from "../reusable/paginate/Paginate";
import BooksInfo from "../reusable/info-card/BooksInfo";
import useModal from "../../hooks/useModal";
import SearchBookModal from "./modal-search/SearchBookModal";
import NoContent from "../../views/no-content/NoContent";

const SearchResults = () => {
    const params = useParams();
    const searchResults = useSelector(state => state.searchResults)
    const dispatch = useDispatch();
    const [pageCount, setPageCount] = useState(0);
    const [currentPageIndex, setCurrentPageIndex] = useState(0);
    const [loading, setLoading] = useState(false);

    let pageSize = 3;

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            const {query} = params;
            const res = await searchBooks(query, currentPageIndex, pageSize);
            const totalCount = res.totalCount;

            setPageCount(Math.ceil(totalCount / pageSize));
            dispatch(addResults(res.searchResults))

        }
        fetchData();
    }, [currentPageIndex, params, pageCount]);

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
            {searchResults && searchResults.length
                ? <BookInfoWrapper>
                    {searchResults.map((bookInfoItem, index) => {
                        return <BooksInfo
                            key={`bookInfo-${index}`}
                            book={bookInfoItem.book}
                            setModalData={() => setModalData({book: bookInfoItem})}
                        />
                    })}
                </BookInfoWrapper>
                : <NoContent/>}
            <Paginate
                handlePageClick={handlePageClick}
                pageCount={pageCount}
                props={searchResults}/>
        </BooksWrapper>


    )
}
export default SearchResults;