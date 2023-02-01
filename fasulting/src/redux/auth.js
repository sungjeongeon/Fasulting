import { createSlice } from "@reduxjs/toolkit";

export const TOKEN_TIME_OUT = 600 * 1000;

//ACEESS TOKEN
export const tokenSlice = createSlice({
  name: "authToken",
  initialState: {
    authenticated: false,
    accessToken: null,
    expireTime: null,
  },
  reducers: {
    SET_TOKEN: (state, action) => {
      state.authenticated = true; //현재 로그인 여부
      state.accessToken = action.payload; //accessToken 저장
      state.expireTime = new Date().getTime + TOKEN_TIME_OUT; //만료시간
    },
    DELETE_TOKEN: (state) => {
      state.authenticated = false;
      state.accessToken = null;
      state.expireTime = null;
    },
  },
});

export const { SET_TOKEN, DELETE_TOKEN } = tokenSlice.actions;
export default tokenSlice.reducer;
