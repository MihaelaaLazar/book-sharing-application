import {
    BookAuthor, BookDescription, BookDetails,
    BookTitle, BookTitleWrapper,
    CloseButton, DropdownWrapper,
    ModalCard,
    ModalContent,
    ModalThumbnail,
    ModalWrapper, RentTheBookButton
} from "./ModalStyle.style";
import {faXmark} from "@fortawesome/free-solid-svg-icons";
import {useSelector} from "react-redux";
import RentedBookApi from "../../reusable/apis/rentedBookApi";
import {useState} from "react";
import useMessage from "../../../hooks/useMessage";

const RENTAL_PERIOD = {
    oneWeek: {
        label: "One week",
        value: "ONE_WEEK"
    },
    twoWeeks: {
        label: "Two weeks",
        value: "TWO_WEEKS"
    },
    threeWeeks: {
        label: "Three weeks",
        value: "THREE_WEEKS"
    },
    oneMonth: {
        label: "One month",
        value: "ONE_MONTH"
    },
    twoMonths: {
        label: "Two months",
        value: "TWO_MONTHS"

    },
    threeMonths: {
        label: "Three months",
        value: "THREE_MONTHS"
    }
}
const AvailableBookModal = ({availableBook, onClose}) => {

    const bookRefData = availableBook.bookRef;
    const bookData = availableBook.bookRef.book;
    const userData = availableBook.bookRef.user;
    const loggedUser = useSelector((state) => state.user);
    const [rentalPeriod, setRentalPeriod] = useState();
    const initialMessage = {
        type: "",
        message: ""
    };
    const {
        message,
        setMessageError,
        setMessageSuccess,
    } = useMessage(initialMessage);


    const handleRentBook = async () => {
        await RentedBookApi.rentBook(loggedUser.userId, bookRefData.bookRefId, rentalPeriod);
        loggedUser.userId === bookRefData.user.userId
            ? setMessageError("You cannot rent your own book")
            : setMessageSuccess("Book rented successfully");

    }
    const handleChange = (e) => {
        setRentalPeriod(e.target.value);
    }


    return <ModalWrapper>
        <CloseButton icon={faXmark} onClick={onClose}/>
        <ModalCard>
            <ModalThumbnail>
                <img src={bookData.imageUrl} alt="Book thumbnail"/>
            </ModalThumbnail>
            <ModalContent>
                <BookTitleWrapper>
                    <BookTitle>{bookData.title}</BookTitle>
                    <BookAuthor>{bookData.author}</BookAuthor>
                </BookTitleWrapper>
                <BookDescription>{bookData.description}</BookDescription>
                <BookDetails>
                    <tbody>
                    <tr key={bookData.bookId}>
                        <td>Book Owner:</td>
                        <td>{userData.firstName} {userData.lastName}</td>
                    </tr>
                    </tbody>
                </BookDetails>
                <DropdownWrapper value={rentalPeriod} onChange={handleChange}>
                    {Object.keys(RENTAL_PERIOD).map(option => <option
                        value={RENTAL_PERIOD[option].value}>{RENTAL_PERIOD[option].label}</option>)}
                </DropdownWrapper>
                <RentTheBookButton onClick={handleRentBook}>Rent the book</RentTheBookButton>
                {message ? <p className={`message-${message.type}`}>{message.message}</p> : null}

            </ModalContent>
        </ModalCard>
    </ModalWrapper>
}

export default AvailableBookModal;