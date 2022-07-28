import useForm, {isRequired} from "../../hooks/useForm";
import {Fragment, useState} from "react";
import {LoginForm, LoginWrapper} from "./Login.style";
import LoadingOverlay from "../reusable/loading-overlay/LoadingOverlay";
import useMessage from "../../hooks/useMessage";
import {useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import UserApi from "../reusable/apis/userApi";
import {updateUser} from "../../reducers/user.reducer";


const Login = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const initialLoginState = {
        username: '',
        password: ''
    };
    const initialMessage = {
        message: "",
        type: ""
    };

    const {
        message,
        setMessageError,
        setMessageSuccess,
        setMessageWarning,
        setMessageInfo
    } = useMessage(initialMessage);


    const [loading, setLoading] = useState(false);

    const validations = [
        ({username}) => isRequired(username) || {username: 'Username is required'},
        ({password}) => isRequired(password) || {password: 'Password is required'}
    ];

    const handleLogin = () => {
        setLoading(true);
        UserApi.login(values)
            .then(data => {
                localStorage.setItem("token", data.token);
                setLoading(false);
                setMessageSuccess("Login successful");
                navigate("/")
                dispatch(updateUser(data))
                resetForm();
            })
            .catch(err => {
                console.log(err)
                setMessageError(err.message);
                setLoading(false);
            });
    }
    const {
        values,
        isValid,
        changeHandler,
        submitHandler,
        resetForm,
    } = useForm(initialLoginState, validations, handleLogin);

    return (
        <Fragment>
            {loading && <LoadingOverlay/>}
            <LoginWrapper>
                <LoginForm onSubmit={submitHandler}>
                    <label>Username</label>
                    <input type="text" name="username" value={values.username} onChange={changeHandler}/>
                    <label>Password</label>
                    <input type="password" name="password" value={values.password} onChange={changeHandler}/>

                    <button type="submit" disabled={!isValid}>Login</button>

                    {message ? <p className={`message-${message.type}`}>{message.message}</p> : null}
                </LoginForm>
            </LoginWrapper>
        </Fragment>
    )
}
export default Login;