import {useEffect, useState} from "react";
import {BookCard, BookContainer} from "./WaitingCard.style";

const WaitingCard = ({book}) => {
     const [remainingTime, setRemainingTime] = useState(0);

    useEffect(() => {
        if (book) {
            const now = new Date();
            const endDate = new Date(book.returningDate);
            const diff = endDate.getTime() - now.getTime();
            const days = Math.floor(diff / (1000 * 60 * 60 * 24));
            setRemainingTime(days);
        }
    }, [book])


    return book ? <BookContainer key={book.id}>
        <BookCard>
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
                <tr>
                    <td>Remaining time</td>
                    <td>{remainingTime} days</td>
                </tr>
                </tbody>
            </table>
        </BookCard>
    </BookContainer> : <div>You are not in the waiting list</div>
}
export default WaitingCard;