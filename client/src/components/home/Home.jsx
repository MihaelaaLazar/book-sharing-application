import {AnimationWrapper, ContentWrapper, Title, DescriptionWrapper, ButtonWrapper} from "./Home.style";
import Animation from "../reusable/animation/Animation";
import bookLoverAnimation from '../../assets/animations/book-lover-animation.json';
import {useNavigate} from "react-router-dom";
import {Fragment} from 'react';
import {useSelector} from "react-redux";

const Home = () => {
    const user = useSelector((state) => state.user);

    const navigate = useNavigate();
    const handleClick = (path) => {
        navigate(path)
    }

    return (
        <Fragment>
            <ContentWrapper>
                {user
                    ? <DescriptionWrapper>
                        <Title>Read, share and learn with others book lovers.</Title>
                        <p>Share books with your colleagues.</p>
                        <p> Explore your library.</p>
                        <ButtonWrapper onClick={() => handleClick("/dashboard")}>Explore
                        </ButtonWrapper>
                    </DescriptionWrapper>
                    : <DescriptionWrapper>
                        <Title>Read, share and learn with others book lovers.</Title>
                        <p>Share books with your colleagues.</p>
                        <p> If you don't have an account, join us by clicking here.</p>
                        <ButtonWrapper onClick={() => handleClick("/register")}>
                            Join Us
                        </ButtonWrapper>
                    </DescriptionWrapper>}

                <AnimationWrapper>
                    <Animation animation={bookLoverAnimation} id="book-lover"/>
                </AnimationWrapper>
            </ContentWrapper>
        </Fragment>
    )
}

export default Home;