import React from "react";
import { configureStore } from "@reduxjs/toolkit";
import userReducer from "./user";
import tokenReducer from "./auth";
import CalendarReducer from "./calendar"
import lastReservationHoReducer from "./lastReservationHo"
import modalInfoReducer from "./modalInfo"
import appointmentsReducer from "./appointments"

export default configureStore({
  reducer: {
    user: userReducer,
    authToken: tokenReducer,
    calendar: CalendarReducer,
    lastReservationHo: lastReservationHoReducer,
    modalInfo: modalInfoReducer,
    appointments: appointmentsReducer,
  },
});
