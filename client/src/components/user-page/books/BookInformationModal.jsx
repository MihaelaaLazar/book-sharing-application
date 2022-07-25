import {
    AddOnWaiting,
    BookAuthor, BookDescription, BookDetails,
    BookTitle,
    BookTitleWrapper,
    CloseButton,
    ModalCard,
    ModalContent,
    ModalThumbnail,
    ModalWrapper
} from "../available-books/ModalStyle.style";
import {faXmark} from "@fortawesome/free-solid-svg-icons";
import {useEffect} from "react";
import BookApi from "../../reusable/apis/bookApi";
import {useDispatch, useSelector} from "react-redux";
import {addBookDetails} from "../../../reducers/bookDetails.reducer";
import {isEmpty} from "lodash";
import UserApi from "../../reusable/apis/userApi";
import useMessage from "../../../hooks/useMessage";

const mapBooksFromState = (state) => {
    const booksDetails = state.bookDetails;
    if (isEmpty(booksDetails)) {
        return [];
    }
    return [
        ...booksDetails.map(book => {
            return {
                ...book,
            }
        })
    ]
}

const BookInformationModal = ({book, onClose}) => {
    const bookDetails = useSelector(mapBooksFromState);
    const user = useSelector(state => state.user);
    const bookData = bookDetails[0];
    const rentedData = bookDetails[1];
    const availableData = bookDetails[2];
    const dispatch = useDispatch();

    const initialMessage = {
        type: "",
        message: ""
    };
    const {
        message,
        setMessageError,
        setMessageSuccess,
    } = useMessage(initialMessage);


    useEffect(() => {
        const fetchData = async () => {
            const res = await BookApi.getBookByBookId(book.bookId)
            const data = await res.json();
            dispatch(addBookDetails(data))
        }
        fetchData();
    }, []);

    const handleAddOnWaitingList = async () => {
        const res =  await UserApi.addOnWaitingList(user.userId, book.bookId);
        if(res.status === 200) {
            setMessageSuccess("Successfully added to waiting list");
        } else if(res.status === 409) {
            setMessageError("You are not allowed to add to waiting list");
        }
        else {
            setMessageError("You are already in waiting list");
        }

    }

    return <ModalWrapper>
        <CloseButton icon={faXmark} onClick={onClose}/>
        <ModalCard>
            <ModalThumbnail>
                <img src={bookData?.imageUrl} alt="Book thumbnail"/>
            </ModalThumbnail>
            <ModalContent>
                <BookTitleWrapper>
                    <BookTitle>{bookData?.title}</BookTitle>
                    <BookAuthor>{bookData?.author}</BookAuthor>
                </BookTitleWrapper>
                <BookDescription>{bookData?.description}</BookDescription>
                <BookDetails>
                    <tbody>
                    <tr key={bookData?.bookId}>
                        <td>Book Owner:</td>
                        <td>
                            {!isEmpty(rentedData) && rentedData.booksRefDto?.user?.username}
                            {!isEmpty(availableData) && availableData.bookRef?.user?.username}

                        </td>
                    </tr>
                    <tr>
                        {!isEmpty(rentedData) && <td>Rented by: </td>}
                        <td>
                            {!isEmpty(rentedData) && rentedData.user.username}
                        </td>
                    </tr>
                    <tr>
                        {!isEmpty(rentedData) && <td>Returning Date: </td>}
                        {!isEmpty(availableData) && <td>Status: </td>}

                        <td>
                            {!isEmpty(rentedData)
                                ? rentedData.returningDate
                                : "Available"}
                        </td>
                    </tr>
                    </tbody>
                </BookDetails>
                {!isEmpty(rentedData) && <AddOnWaiting onClick={handleAddOnWaitingList}>Add on waiting list</AddOnWaiting>}
                {message ? <p className={`message-${message.type}`}>{message.message}</p> : null}
            </ModalContent>
        </ModalCard>
    </ModalWrapper>
}
export default BookInformationModal;