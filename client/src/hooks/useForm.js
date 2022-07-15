import {useState} from "react";

export const isRequired = (value) => {
    return value != null && value.trim().length > 0;
}

export const isSame = (value1, value2) => {
    return value1 === value2;
}

export const validateRegex = (value, regex) => {
    return regex.test(String(value).toLowerCase());
}

const validate = (validations, values) => {
    const errors = validations
        .map(validation => validation(values))
        .filter(validation => typeof validation === 'object');
    return {isValid: errors.length === 0, errors: errors.reduce((errors, error) => ({...errors, ...error}), {})};
}

function useForm(initialState = {}, validations = [], onSubmit = () => {}) {
    const {isValid: initialIsValid, errors: initialErrors} = validate(validations, initialState);
    const [values, setValues] = useState(initialState);
    const [errors, setErrors] = useState(initialErrors);
    const [isValid, setValid] = useState(initialIsValid);
    const [touched, setTouched] = useState({});

    const changeHandler = e => {
        const newValues = {...values, [e.target.name]: e.target.value};
        const {isValid, errors} = validate(validations, newValues);

        setValues(newValues);
        setValid(isValid);
        setErrors(errors);
        setTouched({...touched, [e.target.name]: true});
    };

    const resetForm = () => {
        setValues(initialState);
        setErrors(initialErrors);
        setValid(initialIsValid);
        setTouched({});
    }

    const submitHandler = e => {
        e.preventDefault();
        onSubmit(values);
    }
    return {values, changeHandler, isValid, errors, touched, submitHandler, resetForm};
}

export default useForm;