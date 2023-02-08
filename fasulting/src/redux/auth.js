import { createSlice } from "@reduxjs/toolkit";

export const TOKEN_TIME_OUT = 600 * 1000;

//ACEESS TOKEN
export const tokenSlice = createSlice({
  name: "authToken",
  initialState: {
    authenticated: false,
    accessToken: null,
  },
  reducers: {
    setToken: (state, action) => {
      state.authenticated = true; //현재 로그인 여부
      state.accessToken = action.payload; //accessToken 저장
    },
    deleteToken: (state) => {
      state.authenticated = false;
      state.accessToken = null;
    },
  },
});

export const { setToken, deleteToken } = tokenSlice.actions;
export default tokenSlice.reducer;
