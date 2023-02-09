import { createSlice } from "@reduxjs/toolkit";

export const appointmentsSlice = createSlice({
  name: 'appointments',
  initialState: {
    appointmentList: []
        // {
        //   title: '황보싸피',
        //   startDate: new Date(2023, 1, 11, 14, 30),
        //   endDate: new Date(2023, 1, 11, 15, 0),
        //   sub_category: ['광대', '사각턱', '이마'],
        //   reservationId: 4,
        // }, 
      
  },
  reducers: {
    update: (state, action) => {
      console.log(action.payload)
      state.appointmentList = action.payload
    },
    cancel: (state, action) => {
      const deleteId = action.payload
      state.appointmentList = state.appointmentList.filter((appointment) => appointment.reservationSeq !== deleteId)
      // console.log(current(state.appointmentList))
    }
  }


})

export const { update, cancel } = appointmentsSlice.actions
export default appointmentsSlice.reducer;