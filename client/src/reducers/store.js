import { configureStore } from '@reduxjs/toolkit';
import userReducer from "./user.reducer";
import bookReducer from "./book.reducer";

const store = configureStore({
    reducer: {
        user : userReducer,
        books : bookReducer
    },
})
export default store;