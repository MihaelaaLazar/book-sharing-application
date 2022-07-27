import {useDispatch, useSelector} from "react-redux";
import {deleteUser} from "../../../reducers/user.reducer";
import {useNavigate} from "react-router-dom";
import {
    LogoutButton, PopupContent, PopupHeader, PopupHeaderText, PopupInformation, PopupWrapper
} from "./UserPopup.style";
import {forwardRef} from "react";


const UserPopup = forwardRef((props,ref) => {

    const user = useSelector(state => state.user);
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const handleLogout = () => {
        const jwtToken = localStorage.getItem("token");
        if (jwtToken) {
            dispatch(deleteUser(user))
            localStorage.removeItem("token");
            navigate("/")
        }
    }
    return (<PopupWrapper ref={ref}>
        <PopupHeader>
            <PopupHeaderText>
                Your information
            </PopupHeaderText>
        </PopupHeader>
        <PopupContent>
            <PopupInformation>
                <div>
                    <div>
                        {user.firstName} {user.lastName}
                    </div>
                    <div>
                        {user.username}
                    </div>
                    <div>
                        {user.email}
                    </div>
                </div>
            </PopupInformation>
            <LogoutButton onClick={handleLogout}>Logout</LogoutButton>
        </PopupContent>
    </PopupWrapper>)


})
export default UserPopup;