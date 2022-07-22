import {createSlice} from '@reduxjs/toolkit';
import update from 'immutability-helper';
import {uniqBy} from "lodash";

export const userBooksReducer = createSlice({
    name: 'userBooks',
    initialState: [],
    reducers: {
        addUserBook: (state, action) => {
            const books = [...JSON.parse(JSON.stringify(state)), ...action.payload];
            return update(state, {$set:uniqBy(books, book => book.bookId)})
        },
        updateUserBook: (state, action) => {
            return update(state, {$set: action.payload});
        },
        deleteUserBook: (state) => {
            return update(state, {$set: null});
        },
    },
})

export const {addUserBook, updateUserBook, deleteUserBook} = userBooksReducer.actions

export default userBooksReducer.reducer