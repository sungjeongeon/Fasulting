import { createSlice } from "@reduxjs/toolkit";

export const lastReservationHoSlice = createSlice({
  name: "lastReservationHo",
  initialState: {
    user_name: null,
    user_number: null,
    user_birth: null,
    date: null,
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
      state.user_name = action.payload.userName;
      state.user_number = action.payload.userNumber;
      state.user_birth = action.payload.userBirth;
      state.sub_category_name = action.payload.subCategoryName;
      state.opinion = action.payload.content;
      state.price = action.payload.estimate;
      state.beforeImg = action.payload.beforeImg;
      state.afterImg = action.payload.afterImg;
      return state
    },
    changeLoading: (state) => {
      state.loading = false
      return state
    }
  }
})

export const { changeReserveInfo, changeLoading } = lastReservationHoSlice.actions
export default lastReservationHoSlice.reducer;