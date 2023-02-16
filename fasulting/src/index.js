import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { Provider, createStore } from "react-redux";
import setAuthorizationToken from "./api/setAuthorizationToken";

import { PersistGate } from "redux-persist/integration/react";
import { persistStore } from "redux-persist";
import store from "./redux/store";

const root = ReactDOM.createRoot(document.getElementById("root"));
let persistor = persistStore(store);
root.render(
  <Provider store={store}>
    <PersistGate loading={null} persistor={persistor}>
      {/* 본문 */}
      <App />
    </PersistGate>
  </Provider>
);
