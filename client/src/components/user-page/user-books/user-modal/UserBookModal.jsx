import {
    BookAuthor, BookDescription, BookDetails,
    BookTitle,
    BookTitleWrapper,
    CloseButton, DropdownWrapper, ModalButtonWrapper,
    ModalCard,
    ModalContent,
    ModalThumbnail,
    ModalWrapper
} from "../../available-books/ModalStyle.style";
import {faXmark} from "@fortawesome/free-solid-svg-icons";
import {Fragment, useEffect, useState} from "react";
import BookApi from "../../../reusable/apis/bookApi";
import {useDispatch, useSelector} from "react-redux";
import {addBookDetails} from "../../../../reducers/bookDetails.reducer";
import {isEmpty} from "lodash";
import useMessage from "../../../../hooks/useMessage";
import RentedBookApi from "../../../reusable/apis/rentedBookApi";
import utils from '../../../../utils/utils'

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
const UserBookModal = ({book, onClose}) => {
    const bookDetails = useSelector(mapBooksFromState);
    const user = useSelector(state => state.user);
    const bookData = bookDetails[0];
    const rentedData = bookDetails[1];
    const availableData = bookDetails[2];
    const dispatch = useDispatch();
    const [rentalPeriod, setRentalPeriod] = useState({});

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
            const res = await BookApi.getBookByBookId(book.bookId);
            const data = await res.json();
            dispatch(addBookDetails(data))
        }
        fetchData();
    }, []);

    const handleChange = (e) => {
        setRentalPeriod(e.target.value);
    }

    const handleExtendPeriod = async () => {
        const res = await RentedBookApi.extendPeriod(rentedData.rentedBookId, rentalPeriod);
        res.status === 200
            ? setMessageSuccess("Successfully extended the rental period")
            : setMessageError("You already extended the rental period");
    }

    const isRentedByUserLogged = rentedData?.user?.userId === user?.userId

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
                    <tr>
                        <td>Book Owner:</td>
                        <td>
                            {!isEmpty(rentedData) && rentedData.bookRef?.user?.username}
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
                {isRentedByUserLogged
                    ? <Fragment>
                        <DropdownWrapper value={rentalPeriod} onChange={handleChange}>
                            {Object.keys(utils.defaultRentalPeriod).map(option => <option
                                key={`option-${utils.defaultRentalPeriod[option].value}`}
                                value={utils.defaultRentalPeriod[option].value}>{utils.defaultRentalPeriod[option].label}</option>)}
                        </DropdownWrapper>
                        <ModalButtonWrapper onClick={handleExtendPeriod}>Extend Period</ModalButtonWrapper>
                        {message ? <p className={`message-${message.type}`}>{message.message}</p> : null}
                    </Fragment>
                    : null}
            </ModalContent>
        </ModalCard>
    </ModalWrapper>
}
export default UserBookModal;