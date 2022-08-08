import {Navigate} from "react-router-dom";
import {useSelector} from "react-redux";
import {isEmpty} from "lodash";

const RequireAuth = ({children}) => {
    const user = useSelector(state => state.user);
    const isAuthenticated = !isEmpty(user);

    return isAuthenticated ? children : <Navigate to={"/"} replace={true}/>
}
export default RequireAuth;