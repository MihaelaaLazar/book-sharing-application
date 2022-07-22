import {
    BookAuthor, BookDescription, BookDetails,
    BookTitle, BookTitleWrapper,
    CloseButton,
    ModalCard,
    ModalContent,
    ModalThumbnail,
    ModalWrapper, RentTheBookButton
} from "./BookDetailsModal.style";
import {faXmark} from "@fortawesome/free-solid-svg-icons";

const BookDetailsModal = ({availableBook, onClose}) => {
    const bookData = availableBook.bookRef.book;
    const userData = availableBook.bookRef.user;


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
                <RentTheBookButton>Rent the book</RentTheBookButton>
            </ModalContent>
        </ModalCard>
    </ModalWrapper>
}

export default BookDetailsModal;