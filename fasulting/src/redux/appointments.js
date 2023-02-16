import { createSlice } from "@reduxjs/toolkit";

export const appointmentsSlice = createSlice({
  name: "appointments",
  initialState: {
    appointmentList: [],
  },
  reducers: {
    update: (state, action) => {
      console.log(action.payload);
      state.appointmentList = action.payload;
    },
    cancel: (state, action) => {
      const deleteId = action.payload;
      state.appointmentList = state.appointmentList.filter(
        (appointment) => appointment.reservationSeq !== deleteId
      );
    },
  },
});

export const { update, cancel } = appointmentsSlice.actions;
export default appointmentsSlice.reducer;
