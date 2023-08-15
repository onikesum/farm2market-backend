
const SET_TOKEN = 'SET_TOKEN';
const SET_RESPONSE_DATA = 'SET_RESPONSE_DATA';

export const setToken = (token) => ({
    type: SET_TOKEN,
    payload: token,
});
export const setResponseData = (responseData) => ({
    type: SET_RESPONSE_DATA,
    payload: responseData,
});

const initialState = {
    token: '',
    responseData: {},
};

const authReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_TOKEN:
            return { ...state, token: action.payload };
        case SET_RESPONSE_DATA:
            return { ...state, responseData: action.payload };
        default:
            return state;
    }
};

export default authReducer;
