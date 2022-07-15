import useForm, {isRequired, isSame, validateRegex} from "../../hooks/useForm";
import utils from "../../utils/utils";
import {Fragment, useState} from "react";
import {FormWrapper, RegisterWrapper} from "./Register.style";
import LoadingOverlay from "../reusable/loading-overlay/LoadingOverlay";
import useMessage from "../../hooks/useMessage";

const Register = () => {
    const initialMessage = {
        type: "",
        message: ""
    };

    const [loading, setLoading] = useState(false);

    const initialFormState = {
        firstName: '',
        lastName: '',
        email: '',
        username: '',
        password: '',
        confirmPassword: ''
    };
    const validations = [
        ({firstName, lastName, email, username}) => {
            isRequired(firstName, 'First name is required');
            isRequired(lastName, 'Last name is required');
            isRequired(email, 'Email is required');
            isRequired(username, 'Username is required');
        },
        ({email}) => isRequired(email) || {email: 'E-mail is required'},
        ({email}) => validateRegex(email, utils.regexPatterns.email) || {email: 'E-mail is not valid'},
        ({
             password,
             confirmPassword
         }) => isSame(password, confirmPassword) || {confirmPassword: `Passwords don't match.`}
    ];

    const handleRegister = async () => {
        setLoading(true);
        fetch('http://localhost:8080/api/users/register', {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(values),
        })
            .then((res) => {
                if (res.status === 201) {
                    res.json()
                    setMessageSuccess("Your account has been created successfully. Please check your email to verify your account.");

                } else if (res.status === 400) {
                   setMessageError("Username or email already exists");
                }
                setLoading(false);
                resetForm();
            }).catch(err => {
                setMessageError(err.message);
            setLoading(false);
            resetForm();
        })
    };

    const {
        values,
        isValid,
        touched,
        changeHandler,
        submitHandler,
        resetForm,
        errors
    } = useForm(initialFormState, validations, handleRegister);

    const {
        message,
        setMessageError,
        setMessageSuccess,
        setMessageWarning,
        setMessageInfo
    } = useMessage(initialMessage);


    return (
        <Fragment>
            {loading && <LoadingOverlay/>}
            <RegisterWrapper>
                <FormWrapper onSubmit={submitHandler}>
                    <label>First Name</label>
                    <input type="text" name="firstName" value={values.firstName} onChange={changeHandler}/>

                    <label>Last Name</label>
                    <input type="text" name="lastName" value={values.lastName} onChange={changeHandler}/>

                    <label>Email</label>
                    <input type="text" name="email" value={values.email} onChange={changeHandler}/>
                    {touched.email && errors.email && <span>{errors.email}</span>}

                    <label>Username</label>
                    <input type="text" name="username" value={values.username} onChange={changeHandler}/>

                    <label>Password</label>
                    <input type="password" name="password" value={values.password} onChange={changeHandler}/>

                    <label>Confirm Password</label>
                    <input type="password" name="confirmPassword" value={values.confirmPassword}
                           onChange={changeHandler}/>
                    {touched.confirmPassword && errors.confirmPassword &&
                        <span>{errors.confirmPassword}</span>}

                    <button type="submit" disabled={!isValid}>Register</button>

                    {message ? <p  className={`message-${message.type}`}>{message.message}</p> : null}

                </FormWrapper>
            </RegisterWrapper>
        </Fragment>
    )
}

export default Register;