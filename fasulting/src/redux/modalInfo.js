import { createSlice } from "@reduxjs/toolkit";

export const modalInfoSlice = createSlice({
  name: "modalInfo",
  initialState: {
    modalstate: false,
    userName: null,
    subList: null,
    reservationId: null,
    reservationStart: null,
    dayOfWeek: null,
  },
  reducers: {
    changeInfo: (state, action) => {
      state.modalstate = !state.modalstate;
      state.userName = action.payload.title;
      state.subList = action.payload.subCategoryName;
      state.reservationId = action.payload.reservationSeq;
      state.reservationStart = action.payload.startDate;
      state.dayOfWeek = action.payload.dayOfWeek;
      console.log(action.payload)
      return state
    },
    modalStateChange: (state) => {
      state.modalstate = !state.modalstate;
      return state
    },
  }

})

export const { changeInfo, modalStateChange } = modalInfoSlice.actions
export default modalInfoSlice.reducer;