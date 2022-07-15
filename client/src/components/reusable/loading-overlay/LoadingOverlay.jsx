import Animation from "../animation/Animation";
import loadingSpinner from '../../../assets/animations/loadingSpinner.json';
import {LoadingOverlayWrapper} from "./LoadingOverlay.style";

const LoadingOverlay = () => {
    return (
        <LoadingOverlayWrapper>
            <Animation animation={loadingSpinner} id="loading-overlay"/>
        </LoadingOverlayWrapper>
    )
}
export default LoadingOverlay;