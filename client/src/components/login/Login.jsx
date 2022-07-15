import useForm, {isRequired} from "../../hooks/useForm";
import bcrypt from "bcryptjs";
import {Fragment, useState} from "react";
import {LoginForm, LoginWrapper} from "./Login.style";
import LoadingOverlay from "../reusable/loading-overlay/LoadingOverlay";
import useMessage from "../../hooks/useMessage";


const Login = () => {

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

        fetch('http://localhost:8080/api/users/login', {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(values),
        })
            .then(res => {
                return res.json()
            })
            .then(data => {
                bcrypt.hashSync(values.password, 10);
                localStorage.setItem("token", data.token);
                setLoading(false);
                setMessageSuccess("Login successful");
                resetForm();
            })
            .catch(err => {
                setMessageError("Something went wrong. Try again.");
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