import {BookCardWrapper, BookInfo} from "../../user-page/books/Book.style";

const BooksInfo = ({book, setModalData}) => {

    return book ? <BookCardWrapper key={book.id}>
        <BookInfo>
            <img src={book.imageUrl} onClick={setModalData} alt={"img"}/>
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
                </tbody>
            </table>
        </BookInfo>
    </BookCardWrapper> : null

}
export default BooksInfo;