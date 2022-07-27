import {createSlice} from '@reduxjs/toolkit';
import update from 'immutability-helper';

export const searchResultsReducer = createSlice({
    name: 'searchResults',
    initialState: [],
    reducers: {
        addResults: (state, action) => {
            return update(state, {$set: action.payload});
        },
        clearResults: (state) => {
            return update(state, {$set: null});
        },
    },
})

export const {addResults, clearResults} = searchResultsReducer.actions

export default searchResultsReducer.reducer