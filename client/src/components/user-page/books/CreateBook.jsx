import {Fragment, useState} from "react";
import BookApi from "../../reusable/apis/bookApi";
import {useSelector} from "react-redux";
import useForm, {isRequired} from "../../../hooks/useForm";
import useMessage from "../../../hooks/useMessage";
import LoadingOverlay from "../../reusable/loading-overlay/LoadingOverlay";
import {FormWrapper, RegisterWrapper} from "../../register/Register.style";

const CreateBook = () => {
    const user = useSelector((state) => state.user);

    const initialMessage = {
        type: "",
        message: ""
    };
    const [file, setFiles] = useState("")
    const [loading, setLoading] = useState(false);

    const initialFormState = {
        title: '',
        author: '',
        dateOfPublication: '',
        description: ''
    };

    const validations = [
        ({title, author, dateOfPublication, description}) => {
            isRequired(title, 'Title is required');
            isRequired(author, 'Author is required');
            isRequired(dateOfPublication, 'Date of publication is required');
            isRequired(description, 'Description is required');
        },
    ];

    const handleCreateBook = async () => {
        setLoading(true);
        BookApi.addBook(user.userId, values, file)
            .then((res) => {
                if (res.status === 201) {
                    res.json()
                    setMessageSuccess("Book created successfully");

                } else if (res.status === 400) {
                    setMessageError("Something went wrong, try again");
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
        changeHandler,
        submitHandler,
        resetForm,
    } = useForm(initialFormState, validations ,handleCreateBook);

    const {
        message,
        setMessageError,
        setMessageSuccess,
    } = useMessage(initialMessage);

    const onFileChange = (event) => {
        setFiles(event.target.files[0]);
    }

    return (
        <Fragment>
            {loading && <LoadingOverlay/>}
            <RegisterWrapper>
                <FormWrapper className={"book"} onSubmit={submitHandler}>
                    <label>Title</label>
                    <input type="text" name="title" value={values.title} onChange={changeHandler}/>

                    <label>Author</label>
                    <input type="text" name="author" value={values.author} onChange={changeHandler}/>

                    <label>Date of Publication</label>
                    <input type="date" name="dateOfPublication" value={values.dateOfPublication} onChange={changeHandler}/>

                    <label>Description</label>
                    <textarea  name="description" value={values.description} onChange={changeHandler}/>


                    <label>File</label>
                    <input className={"file"} type="file" name="file"
                           onChange={onFileChange}/>

                    <button type="submit" disabled={!isValid}>Add</button>

                    {message ? <p className={`message-${message.type}`}>{message.message}</p> : null}

                </FormWrapper>
            </RegisterWrapper>
        </Fragment>
    )

}
export default CreateBook;