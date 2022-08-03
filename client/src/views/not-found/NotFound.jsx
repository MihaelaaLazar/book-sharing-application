import Animation from "../../components/reusable/animation/Animation";
import notFoundAnimation from '../../assets/animations/page-not-found.json';
import {NotFoundWrapper} from "./NotFound.style";

const NotFound = () => {
    return <NotFoundWrapper>
        <Animation animation={notFoundAnimation} id="not-found"/>
    </NotFoundWrapper>
}
export default NotFound;