import {useSelector} from "react-redux";
import {useEffect, useState} from "react";

const UserBooks = () => {
    const user = useSelector((state) => state.user);

    const [userBooks, setUserBooks] = useState([]);
    useEffect(() => {
        async function fetchData() {
            const response = await fetch(`http://localhost:8080/api/books/${user.userId}`, {
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("token")}`
                }
            });
            const data = await response.json();
            setUserBooks(data);
        }
        fetchData();
    },[]);

        return(
            <ul>
                {userBooks ? userBooks.map(book => <li key={book.bookId}>{book.title}</li>) : "You don't have any books yet."}
            </ul>
        )
}
export default UserBooks;