import { configureStore } from '@reduxjs/toolkit';
import userReducer from "./user.reducer";
import bookReducer from "./book.reducer";
import userBooksReducer from "./userBooks.reducer";
import availableBooks from "./availableBooks.reducer";
import bookDetailsReducer from "./bookDetails.reducer";

const store = configureStore({
    reducer: {
        user : userReducer,
        books : bookReducer,
        userBooks : userBooksReducer,
        availableBooks : availableBooks,
        bookDetails : bookDetailsReducer,
    },
})
export default store;