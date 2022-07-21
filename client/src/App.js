import './App.css';
import Header from "./components/header/Header";
import {HorizontalLine} from "./components/header/Header.style";
import {useEffect} from "react";
import {useDispatch} from "react-redux";
import {addUser} from "./reducers/user.reducer";
import {useNavigate} from "react-router-dom";
import UserApi from "./components/reusable/apis/userApi";

function App({children}) {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const token = localStorage.getItem("token");

    useEffect(() => {
        if (token) {
            UserApi.verify(token).then(res => {
                if (res.status === 401) {
                    localStorage.removeItem("token");
                    navigate("/login")
                }else{
                    dispatch(addUser(res))
                }
            })
        }
    }, []);

    return (
      <div className="main">
          <Header/>
          <HorizontalLine/>
          {children}
      </div>
  );
}

export default App;
