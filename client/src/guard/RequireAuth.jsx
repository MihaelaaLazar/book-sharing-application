import { Navigate } from "react-router-dom";

const RequireAuth = ({children}) =>{

    const isAuthenticated = localStorage.getItem("token");
    return isAuthenticated ? children : <Navigate to={"/"} replace={true}/>
}
export default RequireAuth;