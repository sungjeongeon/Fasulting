import React from "react";
import { configureStore } from "@reduxjs/toolkit";
import userReducer from "./user";

export default configureStore({
  reducer: {
    user: userReducer,
  },
});
