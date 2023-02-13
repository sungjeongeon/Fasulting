import React from "react";
import { Link, useNavigate } from "react-router-dom";
import styles from "./Dropdown.module.css";
import SettingsIcon from "@mui/icons-material/Settings";
import LogoutIcon from "@mui/icons-material/Logout";
import axiosAPi from "../api/axiosApi";
import { useSelector } from "react-redux";
import setAuthorizationToken from "../api/setAuthorizationToken";
import { toast, ToastContainer } from "react-toastify";
import persistStore from "redux-persist/es/persistStore";
import store from "../redux/store";
import { useEffect } from "react";

function Dropdown() {
  //로그아웃 onClickHandler
  const userData = useSelector((store) => store.user);
  const psData = useSelector((store) => store.ps);
  const token = useSelector((store) => store.authToken);
  const persistor = persistStore(store);

  const logout = async () => {
    try {
      if (userData.userSeq) {
        await axiosAPi
          .get(`/user/logout/${userData.userSeq}`, {
            headers: {
              Authorization: token.accessToken,
              // "Access-Control-Allow-Origin": "*",
              // "Access-Control-Allow-Credentials": "true",
            },
            withCredentials: true,
          })
          .then((res) => {
            if (res.data.statusCode === 200) {
              toast.success(<h3>로그아웃 완료</h3>, {
                position: toast.POSITION.TOP_CENTER,
                autoClose: 1000,
              });
              //persistor.purge();
              setTimeout(() => {
                persistor.purge();
                window.location.replace("/");
              }, 100);
            }
          });
      } else {
        await axiosAPi
          .get(`/ps/logout/${psData.psSeq}`, {
            headers: {
              Authorization: token.accessToken,
              // "Access-Control-Allow-Origin": "*",
              // "Access-Control-Allow-Credentials": "true",
            },
            withCredentials: true,
          })
          .then((res) => {
            if (res.data.statusCode === 200) {
              toast.success(<h3>로그아웃 완료</h3>, {
                position: toast.POSITION.TOP_CENTER,
                autoClose: 1000,
              });
              //persistor.purge();
              setTimeout(() => {
                persistor.purge();
                window.location.replace("/");
              }, 100);
            }
          });
      }
    } catch (e) {
      console.log(e);
    }
  };
  return (
    <>
      <ToastContainer />
      <ul className={styles.box}>
        <li className={styles.center}>
          <SettingsIcon fontSize="small" />
          <Link
            to={"/findpw"}
            style={{ textDecoration: "none" }}
            className={styles.font}
          >
            비밀번호 변경
          </Link>
        </li>
        <hr />
        <li className={styles.center}>
          <LogoutIcon fontSize="small" />
          <span className={styles.font} onClick={logout}>
            로그아웃
          </span>
        </li>
      </ul>
    </>
  );
}

export default Dropdown;
