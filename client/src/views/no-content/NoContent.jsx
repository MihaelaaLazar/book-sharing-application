import {NoContentWrapper, TitleWrapper} from "./NoContent.style";
import Animation from "../../components/reusable/animation/Animation";
import searchAnimation from '../../assets/animations/search-animation.json';

const NoContent = () => {

    return <NoContentWrapper>
        <Animation id="no-content" animation={searchAnimation}/>
        <TitleWrapper>
            <h2>We couldn't find any books based on your search</h2>
        </TitleWrapper>
    </NoContentWrapper>
}
export default NoContent;