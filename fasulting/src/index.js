import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { Provider } from "react-redux";
import store from "./redux/store";
import { CookiesProvider } from "react-cookie";
import GlobalStyle from "./GlobalStyle";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <GlobalStyle />
    <CookiesProvider>
      <Provider store={store}>
        {/* 본문 */}
        <App />
      </Provider>
    </CookiesProvider>
  </React.StrictMode>
);
