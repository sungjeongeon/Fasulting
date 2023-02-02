import { createSlice } from "@reduxjs/toolkit";

export const calendarSlice = createSlice({
  name: "calendar", //리듀서 이름
  initialState: {
    year: null,
    month: null,
    day: null,
  }, //데이터 초기값
  reducers: {
    // 캘린더 값 변경
    changeDate: (state, action) => {
      // console.log(action.payload)
      state.year = action.payload.year;
      state.month = action.payload.month;
      state.day = action.payload.day;
      return state;
    },
  },
});

export const { changeDate } = calendarSlice.actions;
export default calendarSlice.reducer;
