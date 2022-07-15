import {useState} from "react";
const useMessage = (initialMessage = {}) => {

    const [message, setMessage] = useState(initialMessage);

    const setMessageError = (message) => {
        setMessage({message, type: "error"});
    }
    const setMessageSuccess = (message) => {
        setMessage({message, type: "success"});

    }
    const setMessageWarning = (message) => {
        setMessage({message, type: "warning"});
    }
    const setMessageInfo = (message) => {
        setMessage({message, type: "info"});
    }
    return {message, setMessageError, setMessageSuccess, setMessageWarning, setMessageInfo};
}
export default useMessage;