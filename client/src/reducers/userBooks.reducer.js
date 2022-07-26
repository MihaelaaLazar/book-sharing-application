import {createSlice} from '@reduxjs/toolkit';
import update from 'immutability-helper';

export const userBooksReducer = createSlice({
    name: 'userBooks',
    initialState: [],
    reducers: {
        addUserBook: (state, action) => {
            return update(state, {$set: action.payload});
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