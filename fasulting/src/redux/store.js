import React from "react";
import { combineReducers, configureStore } from "@reduxjs/toolkit";
import userReducer from "./user";
import tokenReducer from "./auth";
import CalendarReducer from "./calendar";
import lastReservationHoReducer from "./lastReservationHo";
import modalInfoReducer from "./modalInfo";
import appointmentsReducer from "./appointments";
import storage from "redux-persist/lib/storage";
import user from "./user";
import persistReducer from "redux-persist/es/persistReducer";

const reducers = combineReducers({
  reducer: {
    user: userReducer,
    authToken: tokenReducer,
    calendar: CalendarReducer,
    lastReservationHo: lastReservationHoReducer,
    modalInfo: modalInfoReducer,
    appointments: appointmentsReducer,
  },
  devTools:
    window.__REDUX_DEVTOOLS_EXTENSION__ &&
    window.__REDUX_DEVTOOLS_EXTENSION__(),
});

const persistConfig = {
  key: "root",
  storage,
  whitelist: user,
};

const persistedReducer = persistReducer(persistConfig, reducers);
const store = configureStore({
  reducer: persistedReducer,
});
export default store;
