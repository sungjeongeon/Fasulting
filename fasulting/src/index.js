import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { Provider } from "react-redux";
import { CookiesProvider } from "react-cookie";
import store from "./redux/store";
import setAuthorizationToken from "./api/setAuthorizationToken";
const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <>
    <CookiesProvider>
      <Provider store={store}>
        {/* 본문 */}
        <App />
      </Provider>
    </CookiesProvider>
  </>
);
