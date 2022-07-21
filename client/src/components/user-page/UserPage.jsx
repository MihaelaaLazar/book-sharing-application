import {ImageIcon, LabelIconWrapper, UserContent, UserPageWrapper} from "./UserPage.style";
import TabbedComponent from "../reusable/tabbed-component/TabbedComponent";
import UserBooks from "./user-books/UserBooks";
import AvailableBooks from "./available-books/AvailableBooks";
import booksIcon from '../../assets/books.png';
import bookIcon from '../../assets/book.png';
import openBook from '../../assets/open-book.png';
import waiting from '../../assets/waiting.png';
import Books from "./books/Books";


const UserPage = () =>{
    const tabs = [
        {
            label: <LabelIconWrapper><ImageIcon src={booksIcon}/>All Books</LabelIconWrapper>,
            content: <Books/>
        },
        {
            label: <LabelIconWrapper><ImageIcon src={bookIcon}/>Your Books</LabelIconWrapper>,
            content: <UserBooks/>
        },
        {
            label: <LabelIconWrapper><ImageIcon src={openBook}/>Available Books</LabelIconWrapper>,
            content: <AvailableBooks/>
        },
        {
            label: <LabelIconWrapper><ImageIcon src={waiting}/>Waiting List</LabelIconWrapper>,
            content: <div>x</div>
        },
    ];
    return (
        <UserPageWrapper>
            <UserContent>
                    <TabbedComponent tabs={tabs}/>
            </UserContent>
        </UserPageWrapper>
    )

}
export default UserPage;