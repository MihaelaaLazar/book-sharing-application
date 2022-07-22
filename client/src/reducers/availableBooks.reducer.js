import { createSlice } from '@reduxjs/toolkit';
import update from 'immutability-helper';

export const availableBooks = createSlice({
    name: 'availableBooks',
    initialState: null,
    reducers: {
        addAvailableBook: (state, action) => {
            return update(state, {$set : action.payload});
        },
        updateAvailableBook: (state, action) => {
            return update(state, {$set : action.payload});
        },
        deleteAvailableBook: (state) => {
            return update(state, {$set : null});
        },
    },
})

export const { addAvailableBook, updateAvailableBook, deleteAvailableBook } = availableBooks.actions

export default availableBooks.reducer