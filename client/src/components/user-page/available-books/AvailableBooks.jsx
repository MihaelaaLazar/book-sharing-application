import {useEffect, useState} from "react";
import BookApi from "../../reusable/apis/bookApi";
import {useDispatch, useSelector} from "react-redux";
import {addAvailableBook} from "../../../reducers/availableBooks.reducer";
import {
    BookCardWrapper,
    BookInfo,
    BookInfoWrapper,
    BooksWrapper,

} from "../books/Book.style";
import LoadingOverlay from "../../reusable/loading-overlay/LoadingOverlay";
import useModal from "../../../hooks/useModal";
import AvailableBookModal from "./AvailableBookModal";
import Paginate from "../../reusable/paginate/Paginate";

const AvailableBooks = () => {
    const availableBooks = useSelector((state) => state.availableBooks);
    const [pageCount, setPageCount] = useState(0);
    const [currentPageIndex, setCurrentPageIndex] = useState(0);
    const [loading, setLoading] = useState(false);
    const dispatch = useDispatch();

    const pageSize = 3;

    useEffect(() => {
        const fetchData = async () => {
            const response = await fetchAvailableBooks(currentPageIndex, pageSize);
            const total = response.totalCount;
            setPageCount(Math.ceil(total / pageSize));
            dispatch(addAvailableBook(response.availableBook))
            setLoading(false)
        };
        fetchData();
        setLoading(false)
    }, [pageSize])

    const fetchAvailableBooks = async (currentPage, _items) => {
        const res = await BookApi.getAvailableBooksWithPagination(currentPage, pageSize);
        const data = res.json();
        setLoading(false);
        return data;
    }

    const handlePageClick = async ({selected}) => {
        setLoading(true);
        const data = await fetchAvailableBooks(selected, pageSize);
        setCurrentPageIndex(selected * pageSize);
        dispatch(addAvailableBook(data.availableBook));
    };
    const {modal, setModalData} = useModal({
        Component: AvailableBookModal
    });


    return (
        <BooksWrapper>
            {modal}
            {loading && <LoadingOverlay/>}
            {availableBooks && availableBooks.length ?
                <BookInfoWrapper>
                    {availableBooks.map((availableBook, index) => {
                        return <BookCardWrapper key={`book-${index}`}>
                            <BookInfo>
                                <img onClick={() => setModalData({availableBook})}
                                     src={availableBook.bookRef.book.imageUrl} alt={"img"}/>
                                <table>
                                    <tbody>
                                    <tr>
                                        <td>Title</td>
                                        <td>{availableBook.bookRef.book.title}</td>
                                    </tr>
                                    <tr>
                                        <td>Author</td>
                                        <td>{availableBook.bookRef.book.author}</td>
                                    </tr>
                                    <tr>
                                        <td>Owner</td>
                                        <td>{availableBook.bookRef.user.firstName} {availableBook.bookRef.user.lastName}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </BookInfo>
                        </BookCardWrapper>
                    })}

                </BookInfoWrapper>
                : <div>No Books Available</div>
            }
            {availableBooks && availableBooks.length ?
                <Paginate
                    handlePageClick={handlePageClick}
                    pageCount={pageCount}
                    props={availableBooks}/>
                : null}

        </BooksWrapper>);
}
export default AvailableBooks;