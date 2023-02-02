import React from "react";
import { configureStore } from "@reduxjs/toolkit";
import userReducer from "./user";
import tokenReducer from "./auth";
import CalendarReducer from "./calendar"

export default configureStore({
  reducer: {
    user: userReducer,
    authToken: tokenReducer,
    calendar: CalendarReducer,
  },
});
