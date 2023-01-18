import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import Header from "./components/Header";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    {/* 헤더 (내브바) */}
    <Header />
    {/* 본문 */}
    <App />
  </React.StrictMode>
);
