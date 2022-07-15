import {HeaderWrapper, Logo, LoginIcon, ButtonWrapper, UserIcon, UserIconWrapper} from './Header.style';
import {faArrowRightToBracket} from '@fortawesome/free-solid-svg-icons'
import logoSrc from '../../assets/logo.png'
import {useNavigate} from "react-router-dom";
import {useSelector} from "react-redux";
import {faUser} from "@fortawesome/free-solid-svg-icons";

const Header = () => {
    const user = useSelector((state) => state.user);
    const navigate = useNavigate();
    const handleNavigate = (path) => {
        navigate(path)
    }


    return (
        <HeaderWrapper>
            <Logo src={logoSrc} onClick={() => handleNavigate("/")}></Logo>
            {
                user
                    ? <UserIconWrapper><UserIcon icon={faUser} /> {user.username} </UserIconWrapper>
                    : (<ButtonWrapper onClick={() => handleNavigate("/login")}>Login
                        <LoginIcon icon={faArrowRightToBracket}/></ButtonWrapper>)
            }
        </HeaderWrapper>

    );
}

export default Header;