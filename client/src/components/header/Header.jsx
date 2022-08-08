import {HeaderWrapper, Logo, LoginIcon, ButtonWrapper, UserIconWrapper, IconWrapper} from './Header.style';
import {faArrowRightToBracket, faUser} from '@fortawesome/free-solid-svg-icons'
import logoSrc from '../../assets/logo.png'
import {useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import {useState, useRef} from "react";
import UserPopup from "../user-page/user-books/user-popup/UserPopup";
import useOnClickOutside from "../../hooks/useOnClickOutside";
import {Fragment} from "react";
import Search from "../search/Search";


const Header = () => {
    const user = useSelector((state) => state.user);
    const navigate = useNavigate();
    const [openPopup, setOpenPopup] = useState(false);
    const popupRef = useRef();

    const handleNavigate = (path) => {
        navigate(path)
    }

    const handleOpenPopup = () => {
        setOpenPopup(true);
    }

    useOnClickOutside(popupRef, () => {
        setOpenPopup(false);
    });


    return (
        <HeaderWrapper>
            <Logo src={logoSrc} onClick={() => handleNavigate("/")}></Logo>
            {user
                ? <Fragment>
                    <Search/>
                    <UserIconWrapper>
                        <IconWrapper icon={faUser} onClick={handleOpenPopup}/>
                        {openPopup && <UserPopup ref={popupRef}/>}
                    </UserIconWrapper>
                </Fragment>
                : (<ButtonWrapper onClick={() => handleNavigate("/login")}>Login
                    <LoginIcon icon={faArrowRightToBracket}/></ButtonWrapper>)
            }
        </HeaderWrapper>

    );
}

export default Header;