import React from "react";
import { Link } from "react-router-dom";
import styles from "./Dropdown.module.css";
import SettingsIcon from "@mui/icons-material/Settings";
import LogoutIcon from "@mui/icons-material/Logout";
import axiosAPi from "../api/axiosApi";
import { useSelector } from "react-redux";
import setAuthorizationToken from "../api/setAuthorizationToken";

function Dropdown() {
  //로그아웃 onClickHandler
  const userData = useSelector((store) => store.user);
  const logout = () => {
    axiosAPi.get(`/user/logout/${userData.userSeq}`).then((res) => {
      setAuthorizationToken();
      console.log(res.data);
    });
  };
  return (
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
  );
}

export default Dropdown;
