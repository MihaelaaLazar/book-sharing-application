import {LabelIcon, LabelIconWrapper, UserContent, UserPageWrapper} from "./UserPage.style";
import TabbedComponent from "../reusable/tabbed-component/TabbedComponent";
import {faBookOpenReader, faBookOpen, faBookSkull} from "@fortawesome/free-solid-svg-icons";
import UserBooks from "./user-books/UserBooks";
import AvailableBooks from "./available-books/AvailableBooks";

const UserPage = () =>{
    const tabs = [
        {
            label: <LabelIconWrapper><LabelIcon icon={faBookOpenReader}/>Your Books</LabelIconWrapper>,
            content: <UserBooks/>
        },
        {
            label: <LabelIconWrapper><LabelIcon icon={faBookOpen}/>Available Books</LabelIconWrapper>,
            content: <AvailableBooks/>
        },
        {
            label: <LabelIconWrapper><LabelIcon icon={faBookSkull}/>Waiting List</LabelIconWrapper>,
            content: <div>x</div>
        }
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