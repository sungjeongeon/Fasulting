import { createSlice } from "@reduxjs/toolkit";

export const calendarSlice = createSlice({
  name: "calendar", //리듀서 이름
  initialState: {
    year: new Date().getFullYear(),
    month: new Date().getMonth(),
    day: new Date().getDate(),
  }, //데이터 초기값
  reducers: {
    // 캘린더 값 변경
    changeDate: (state, action) => {
      state.year = action.payload.year;
      state.month = action.payload.month;
      state.day = action.payload.day;
      return state;
    },
    resetDate: (state) => {
      state.year = new Date().getFullYear();
      state.month = new Date().getMonth();
      state.day = new Date().getDate();
    }
  },
});

export const { changeDate, resetDate } = calendarSlice.actions;
export default calendarSlice.reducer;
