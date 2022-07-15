import {AnimationWrapper, ContentWrapper, Title, DescriptionWrapper, RegisterButton} from "./Home.style";
import Animation from "../reusable/animation/Animation";
import bookLoverAnimation from '../../assets/animations/bookLoverAnimation.json';
import {useNavigate} from "react-router-dom";
import {Fragment, useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {addUser} from "../../reducers/user.reducer";
import UserPage from "../user-page/UserPage";

const Home = () => {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.user);

    const navigate = useNavigate();
    const handleClick = (e) => {
        e.preventDefault();
        navigate("/register")
    }
    const token = localStorage.getItem("token");
    useEffect(() => {
        if (token) {
            const res = fetch(`http://localhost:8080/api/users/verify/${token}`);
            res.then(async res => {
                if (res.status === 401) {
                    localStorage.removeItem("token");
                    navigate("/login")
                }
                const user = await res.json();
                dispatch(addUser(user));
            });
        }
    }, []);

    return (
        <Fragment>
            {user
                ? <UserPage/>
                : <ContentWrapper> <DescriptionWrapper>
                    <Title>Read, share and learn with others book lovers.</Title>
                    <p>Share books with your colleagues.</p>
                    <p> If you don't have an account, join us by clicking here.</p>
                    <RegisterButton onClick={handleClick}>
                        Join Us
                    </RegisterButton>
                </DescriptionWrapper>
                    <AnimationWrapper>
                        <Animation animation={bookLoverAnimation} id="book-lover"/>
                    </AnimationWrapper>
                </ContentWrapper>}
        </Fragment>
    )
}

export default Home;