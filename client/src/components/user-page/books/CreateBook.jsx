import {Fragment, useRef, useState} from "react";
import BookApi from "../../reusable/apis/bookApi";
import {useSelector} from "react-redux";
import useForm, {isRequired} from "../../../hooks/useForm";
import useMessage from "../../../hooks/useMessage";
import LoadingOverlay from "../../reusable/loading-overlay/LoadingOverlay";
import {Container, FormWrapper, Icon} from "../../reusable/form/Form.style";
import {faCloudArrowUp} from "@fortawesome/free-solid-svg-icons";
import placeholder from '../../../assets/placeholder.png';

const CreateBook = () => {
    const user = useSelector((state) => state.user);
    let imageRef = useRef();

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
                res.status === 201
                    ? setMessageSuccess("Book created successfully")
                    : setMessageError("Something went wrong, try again");
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
    } = useForm(initialFormState, validations, handleCreateBook);

    const {
        message,
        setMessageError,
        setMessageSuccess,
    } = useMessage(initialMessage);

    const onFileChange = (event) => {
        setFiles(event.target.files[0]);
        const reader = new FileReader();
        reader.onload = (e) => {
            imageRef.current.src = e.target.result;
        }
        reader.readAsDataURL(event.target.files[0]);
    }

    return (
        <Fragment>
            {loading && <LoadingOverlay/>}
            <Container>
                <FormWrapper className={"book"} onSubmit={submitHandler}>
                    <label>Title</label>
                    <input type="text" name="title" value={values.title} onChange={changeHandler}/>

                    <label>Author</label>
                    <input type="text" name="author" value={values.author} onChange={changeHandler}/>

                    <label>Date of Publication</label>
                    <input type="date" name="dateOfPublication" value={values.dateOfPublication}
                           onChange={changeHandler}/>

                    <label>Description</label>
                    <textarea name="description" value={values.description} onChange={changeHandler}/>

                    <label className={"file"} for="file-upload">
                        <Icon icon={faCloudArrowUp}/>
                        Select an image</label>
                    <input id={"file-upload"} className={"file"} type="file" name="file"
                           onChange={onFileChange}/>
                    <img id={"image-preview"} ref={imageRef} className={"image-preview"} src={placeholder} alt={"image preview"}/>

                    <button type="submit" disabled={!isValid}>Add</button>

                    {message ? <p className={`message-${message.type}`}>{message.message}</p> : null}

                </FormWrapper>
            </Container>
        </Fragment>
    )

}
export default CreateBook;