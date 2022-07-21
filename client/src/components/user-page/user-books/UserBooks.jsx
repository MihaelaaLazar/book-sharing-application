import { useSelector} from "react-redux";
import {useEffect, useState} from "react";
import {AddBookButton, BookListItem, BookListWrapper} from "./UserBooks.style";
import BookApi from "../../reusable/apis/bookApi";

const UserBooks = () => {
    const user = useSelector((state) => state.user);
    const [userBooks, setUserBooks] = useState([]);

    useEffect(() => {
        async function fetchData(userId) {
            const response = await BookApi.bookByUserId(userId);
            setUserBooks(response);
        }
        if(user?.userId){
            fetchData(user.userId);
        }
    }, [user]);

    return (
        <BookListWrapper>
            {
                userBooks
                    ? userBooks.map(book => <BookListItem key={book.bookId}>
                        <div><span className={"book-label"}>Title: </span><span className={"book-details"}>{book.title}</span> </div>
                        <div><span className={"book-label"}>Author: </span><span className={"book-details"}>{book.author}</span></div>
                    </BookListItem>)
                    : "You don't have any books yet."
            }
            <AddBookButton onClick={() => console.log("Add")}>Add Book</AddBookButton>
        </BookListWrapper>
    )
}
export default UserBooks;