import {createSlice} from '@reduxjs/toolkit';
import update from 'immutability-helper';

export const bookDetailsReducer = createSlice({
    name: 'bookDetails',
    initialState: [],
    reducers: {
        addBookDetails: (state, action) => {
            return update(state, {$set: action.payload});
        },
        updateBookDetails: (state, action) => {
            return update(state, {$set: action.payload});
        },
        deleteBookDetails: (state) => {
            return update(state, {$set: null});
        },
    },
})

export const {addBookDetails, updateBookDetails, deleteBookDetails} = bookDetailsReducer.actions

export default bookDetailsReducer.reducer