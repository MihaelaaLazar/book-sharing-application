import {useEffect, useState} from "react";

const AvailableBooks = () => {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        async function fetchData() {
            const response = await fetch("http://localhost:8080/api/booksForRent", {
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("token")}`
                }
            });
            const data = await response.json();
            setBooks(data)
        }
        fetchData();
    }, [])

    return (
        <div>
            <ul>
                {books ? books.map(book => <li key={book.bookForRentId}>{book.bookRef.book.title} <span>Owner: {book.bookRef.user.username}</span></li>) : "There is no books available for rent."}
            </ul>
        </div>
    );
}
export default AvailableBooks;