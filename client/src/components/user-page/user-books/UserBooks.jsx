import {useDispatch, useSelector} from "react-redux";
import {useEffect, useState} from "react";
import {
    AddBookButton,
    BookListWrapper,
    CardInfoWrapper,
    CardWrapper,
    UserBookInfoWrapper
} from "./UserBooks.style";
import BookApi from "../../reusable/apis/bookApi";
import LoadingOverlay from "../../reusable/loading-overlay/LoadingOverlay";
import {PaginateIconWrapper, ReactPaginateWrapper} from "../books/Book.style";
import {faChevronLeft, faChevronRight, faEllipsis} from "@fortawesome/free-solid-svg-icons";
import {addUserBook} from "../../../reducers/userBooks.reducer";
import {useNavigate} from "react-router-dom";

const UserBooks = () => {
    const user = useSelector((state) => state.user);
    const userBooks = useSelector((state) => state.userBooks);
    const [loading, setLoading] = useState(false);
    const [currentPageIndex, setCurrentPageIndex] = useState(0);
    const [pageCount, setPageCount] = useState(0);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    let pageSize = 3;


    useEffect(() => {
        const fetchData = async (userId) => {
            const response = await fetchBooks(userId, currentPageIndex, pageSize);
            const total = response.totalCount;
            dispatch(addUserBook(response.books));
            setLoading(false);
            setPageCount(Math.ceil(total / pageSize));
        }
        if (user?.userId) {
            fetchData(user.userId);
            setLoading(false);
        }
    }, [user, pageSize]);

    const fetchBooks = async (userId, currentPage, _pageSize) => {
        const res = await BookApi.getAllBooksWithUserIdAndPagination(userId, currentPage, pageSize);
        const data = await res.json();
        setLoading(false);
        return data;
    };
    const handlePageClick = async ({selected}) => {
        setLoading(true);
        const response = await fetchBooks(user.userId, selected, pageSize);
        dispatch(addUserBook(response.books));
        setCurrentPageIndex(selected);
    };

    const handleClick = async () => {
        setLoading(true);
        navigate(`/dashboard/create`)
    }

    return (
        <BookListWrapper>
            {loading && <LoadingOverlay/>}
            {
                userBooks && userBooks.length ?
                    <UserBookInfoWrapper>
                        {userBooks.slice(currentPageIndex, pageSize).map((book) => {
                            return (<CardWrapper key={book.bookId}>
                                <CardInfoWrapper>
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
                                </CardInfoWrapper>
                            </CardWrapper>);
                        })}

                    </UserBookInfoWrapper>
                    : <div> There is no book yet</div>}
            <AddBookButton onClick={handleClick}>Add Book</AddBookButton>
            {userBooks && userBooks.length
                ? <ReactPaginateWrapper
                    previousLabel={<PaginateIconWrapper icon={faChevronLeft}/>}
                    nextLabel={<PaginateIconWrapper icon={faChevronRight}/>}
                    breakLabel={<PaginateIconWrapper icon={faEllipsis}/>}
                    pageCount={pageCount}
                    marginPagesDisplayed={2}
                    onPageChange={handlePageClick}
                    activeClassName={"active"}/>
                : null}


        </BookListWrapper>
    )
}
export default UserBooks;