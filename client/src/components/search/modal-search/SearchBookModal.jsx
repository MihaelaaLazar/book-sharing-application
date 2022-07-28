import {faXmark} from "@fortawesome/free-solid-svg-icons";
import {
    BookAuthor,
    BookDescription,
    BookDetails,
    BookTitle,
    BookTitleWrapper,
    CloseButton,
    ModalCard,
    ModalContent,
    ModalThumbnail,
    ModalWrapper,
} from "../../user-page/available-books/ModalStyle.style";


const SearchBookModal = ({book, onClose}) => {
    return <ModalWrapper>
        <CloseButton icon={faXmark} onClick={onClose}/>
        <ModalCard>
            <ModalThumbnail>
                {book.book
                    ? <img src={book.book.imageUrl} alt="Book thumbnail"/>
                    : <img src={book.imageUrl} alt="Book thumbnail"/>
                }
            </ModalThumbnail>
            <ModalContent>
                <BookTitleWrapper>
                    {book.book
                        ? <BookTitle>{book.book.title}</BookTitle>
                        : <BookTitle>{book.title}</BookTitle>
                    }
                    {book.book
                        ? <BookAuthor>{book.book.author}</BookAuthor>
                        : <BookAuthor>{book.author}</BookAuthor>
                    }
                </BookTitleWrapper>
                <BookDescription>
                    {book.book
                        ? <BookDescription>{book.book.description}</BookDescription>
                        : <BookDescription>{book.description}</BookDescription>
                    }
                </BookDescription>
                <BookDetails>
                    {/*TODO: add some details about search results*/}
                </BookDetails>
            </ModalContent>
        </ModalCard>
    </ModalWrapper>
}

export default SearchBookModal;