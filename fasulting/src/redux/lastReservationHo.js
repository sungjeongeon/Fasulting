import { createSlice } from "@reduxjs/toolkit";

export const lastReservationHoSlice = createSlice({
  name: "lastReservationHo",
  initialState: {
    user_id: null,
    user_name: null,
    user_email: null,
    user_number: null,
    user_birth: null,
    year: null,
    month: null,
    day: null,
    day_of_week: null, // 1~7 (일~화)
    sub_category_name: null,
    opinion: null,
    price: null,
    reservation_id: null,
  },
  reducers: {
    changeReserveId: (state, action) => {
      state.user_id = action.payload.user_id;
      state.user_name = action.payload.user_name;
      state.user_email = action.payload.user_email;
      state.user_number = action.payload.user_number;
      state.user_birth = action.payload.user_birth;
      state.year = action.payload.year;
      state.month = action.payload.month;
      state.day = action.payload.day;
      state.day_of_week = action.payload.day_of_week;
      state.sub_category_name = action.payload.sub_category_name;
      state.opinion = action.payload.opinion;
      state.price = action.payload.price;
      state.reservation_id = action.payload.reservation_id;
      return state
    }
  }
})

export const { changeReserveId } = lastReservationHoSlice.actions
export default lastReservationHoSlice.reducer;