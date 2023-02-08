import { createSlice } from "@reduxjs/toolkit";

export const lastReservationHoSlice = createSlice({
  name: "lastReservationHo",
  initialState: {
    // user_id: null,
    user_name: null,
    // user_email: null,
    user_number: null,
    user_birth: null,
    date: null,
    // year: null,
    // month: null,
    // day: null,
    // day_of_week: null, // 1~7 (일~화) => 필요?
    sub_category_name: null,
    opinion: null,
    price: null,
    reservation_id: null,
    beforeImg: null,
    afterImg: null,
    loading: true,
  },
  reducers: {
    changeReserveInfo: (state, action) => {
      // state.user_id = action.payload.user_id;
      state.user_name = action.payload.userName;
      // state.user_email = action.payload.user_email;
      state.user_number = action.payload.userNumber;
      state.user_birth = action.payload.userBirth;
      // state.year = action.payload.year;
      // state.month = action.payload.month;
      // state.day = action.payload.day;
      // state.day_of_week = action.payload.day_of_week;
      state.sub_category_name = action.payload.subCategoryName;
      state.opinion = action.payload.content;
      state.price = action.payload.estimate;
      state.beforeImg = action.payload.beforeImg;
      state.afterImg = action.payload.afterImg;
      // console.log(action.payload)
      return state
    },
    // changeReserveId: (state, action) => {
    //   state.reservation_id = action.payload;
    //   // console.log(action.payload)
    //   return state
    // },
    changeLoading: (state) => {
      state.loading = !state.loading
      return state
    }
  }
})

export const { changeReserveInfo, changeLoading } = lastReservationHoSlice.actions
export default lastReservationHoSlice.reducer;