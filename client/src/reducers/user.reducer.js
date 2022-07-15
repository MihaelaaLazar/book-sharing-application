import { createSlice } from '@reduxjs/toolkit';
import update from 'immutability-helper';

export const userReducer = createSlice({
    name: 'user',
    initialState: null,
    reducers: {
        addUser: (state, action) => {
            return update(state, {$set : action.payload});
        },
        updateUser: (state, action) => {
            return update(state, {$set : action.payload});
        },
        deleteUser: (state) => {
            return update(state, {$set : null});
        },
    },
})

export const { addUser, updateUser, deleteUser } = userReducer.actions

export default userReducer.reducer