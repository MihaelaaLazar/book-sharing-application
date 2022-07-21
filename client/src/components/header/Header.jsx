import {HeaderWrapper, Logo, LoginIcon, ButtonWrapper, UserIcon, UserIconWrapper, IconWrapper} from './Header.style';
import {faArrowRightToBracket, faMagnifyingGlass} from '@fortawesome/free-solid-svg-icons'
import logoSrc from '../../assets/logo.png'
import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {faUser} from "@fortawesome/free-solid-svg-icons";
import {deleteUser} from "../../reducers/user.reducer";

const Header = () => {
    const user = useSelector((state) => state.user);
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const handleNavigate = (path) => {
        navigate(path)
    }
    const handleLogout = () => {
        const jwtToken = localStorage.getItem("token");
        if (jwtToken) {
            dispatch(deleteUser(user))
            localStorage.removeItem("token");
            navigate("/")

        }
    }
    return (
        <HeaderWrapper>
            <Logo src={logoSrc} onClick={() => handleNavigate("/")}></Logo>
            {
                user
                    ? <UserIconWrapper>
                        <IconWrapper icon={faMagnifyingGlass}/>
                        <IconWrapper icon={faUser}/>
                        <ButtonWrapper onClick={handleLogout}>Logout</ButtonWrapper>
                    </UserIconWrapper>
                    : (<ButtonWrapper onClick={() => handleNavigate("/login")}>Login
                        <LoginIcon icon={faArrowRightToBracket}/></ButtonWrapper>)
            }
        </HeaderWrapper>

    );
}

export default Header;