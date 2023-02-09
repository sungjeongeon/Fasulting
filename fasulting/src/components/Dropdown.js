import React from "react";
import { Link, useNavigate } from "react-router-dom";
import styles from "./Dropdown.module.css";
import SettingsIcon from "@mui/icons-material/Settings";
import LogoutIcon from "@mui/icons-material/Logout";
import axiosAPi from "../api/axiosApi";
import { useSelector } from "react-redux";
import setAuthorizationToken from "../api/setAuthorizationToken";
import { toast } from "react-toastify";

function Dropdown() {
  //로그아웃 onClickHandler
  const userData = useSelector((store) => store.user);
  const token = useSelector((store) => store.authToken);
  const navigate = useNavigate();
  const logout = async () => {
    try {
      await axiosAPi
        .get(`/user/logout/${userData.userSeq}`, {
          headers: {
            Authorization: token.accessToken,
            // "Access-Control-Allow-Origin": "*",
            // "Access-Control-Allow-Credentials": "true",
          },
        })
        .then((res) => {
          toast.success(<h3>로그아웃 완료</h3>, {
            position: toast.POSITION.TOP_CENTER,
            autoClose: 1000,
          });
          setTimeout(() => {
            navigate("/");
          }, 1000);
          console.log(res.data);
        });
    } catch (e) {
      console.log(e);
    }
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
