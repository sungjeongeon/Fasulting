import React from "react";
import {
  combineReducers,
  configureStore,
  getDefaultMiddleware,
} from "@reduxjs/toolkit";
import userReducer from "./user";
import tokenReducer from "./auth";
import psReducer from "./ps";
import CalendarReducer from "./calendar";
import lastReservationHoReducer from "./lastReservationHo";
import modalInfoReducer from "./modalInfo";
import appointmentsReducer from "./appointments";

//persist-reducer 관련
import storage from "redux-persist/lib/storage";
import { persistReducer } from "redux-persist";

const reducers = combineReducers({
  user: userReducer,
  ps: psReducer,
  authToken: tokenReducer,
  calendar: CalendarReducer,
  lastReservationHo: lastReservationHoReducer,
  modalInfo: modalInfoReducer,
  appointments: appointmentsReducer,

  // devTools:
  //   window.__REDUX_DEVTOOLS_EXTENSION__ &&
  //   window.__REDUX_DEVTOOLS_EXTENSION__(),
});

const persistConfig = {
  key: "root",
  storage,
  whitelist: ["user", "authToken"],
};

const persistedReducer = persistReducer(persistConfig, reducers);
const store = configureStore({
  reducer: persistedReducer,
  middleware: getDefaultMiddleware({
    serializableCheck: false,
  }),
});
export default store;
