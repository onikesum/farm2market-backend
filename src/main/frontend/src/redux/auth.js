import { createSlice } from '@reduxjs/toolkit';

const authSlice = createSlice({
    name: 'auth',
    initialState: {
        token: '',
        responseData: {},
        ID: '',
        isLoggedIn: false,
    },
    reducers: {
        setToken: (state, action) => {
            state.token = action.payload;
            state.isLoggedIn = true;
        },
        setResponseData: (state, action) => {
            state.responseData = action.payload;
        },
        setID: (state, action) => {
            state.ID = action.payload;
        },
    },
});

export const { setToken, setResponseData, setID } = authSlice.actions;

export default authSlice.reducer;
