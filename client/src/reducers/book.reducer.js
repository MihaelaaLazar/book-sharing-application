import {createSlice, current} from '@reduxjs/toolkit';
import update from 'immutability-helper';
import {isEqual, uniqBy} from "lodash";

export const bookReducer = createSlice({
    name: 'books',
    initialState: [],
    reducers: {
        addBook: (state, action) => {

            const books = [...JSON.parse(JSON.stringify(state)), ...action.payload];
            return update(state, {$set:uniqBy(books, item => item.bookId)})
        },
        updateBook: (state, action) => {
            return update(state, {$set: action.payload});
        },
        deleteBook: (state) => {
            return update(state, {$set: null});
        },
    },
})

export const {addBook, updateBook, deleteBook} = bookReducer.actions

export default bookReducer.reducer