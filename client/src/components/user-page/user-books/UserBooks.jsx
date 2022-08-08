import {useDispatch, useSelector} from "react-redux";
import {useEffect, useState} from "react";
import {
    AddBookButton,
    BookListWrapper,
    UserBookInfoWrapper
} from "./UserBooks.style";
import BookApi from "../../reusable/apis/bookApi";
import LoadingOverlay from "../../reusable/loading-overlay/LoadingOverlay";
import {addUserBook} from "../../../reducers/userBooks.reducer";
import {useNavigate} from "react-router-dom";
import useModal from "../../../hooks/useModal";
import UserBookModal from "./user-modal/UserBookModal";
import BooksInfo from "../../reusable/info-card/BooksInfo";
import Paginate from "../../reusable/paginate/Paginate";


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

    const {modal, setModalData} = useModal({
        Component: UserBookModal
    });

    return (
        <BookListWrapper>
            {loading && <LoadingOverlay/>}
            {modal}
            {userBooks && userBooks.length ?
                <UserBookInfoWrapper>
                    {userBooks.map((bookInfoItem, index) => {
                        return <BooksInfo
                            key={`book-${index}`}
                            book={bookInfoItem}
                            setModalData={() => setModalData({book: bookInfoItem})}/>
                    })
                    }

                </UserBookInfoWrapper>
                : <div> There is no book yet</div>}

            <AddBookButton onClick={handleClick}>Add Book</AddBookButton>

            {userBooks && userBooks.length
                ? <Paginate
                    handlePageClick={handlePageClick}
                    pageCount={pageCount}
                    props={userBooks}/>
                : null}


        </BookListWrapper>
    )
}
export default UserBooks;