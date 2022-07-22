import {createSlice} from '@reduxjs/toolkit';
import update from 'immutability-helper';

export const bookReducer = createSlice({
    name: 'books',
    initialState: [],
    reducers: {
        addBook: (state, action) => {
            return update(state, {$set: action.payload});
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