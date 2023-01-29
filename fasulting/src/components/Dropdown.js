import React from "react";
import { Link } from "react-router-dom";
import styles from "./Dropdown.module.css"
import SettingsIcon from '@mui/icons-material/Settings';
import LogoutIcon from '@mui/icons-material/Logout';

function Dropdown() {
  // 로그아웃 onClickHandler
  // const onClick = () => {
  //   axios.get('/user/logout/')
  //   .then(res => {
  //     console.log(res.data)
  //   })
  // }
  return (
    <ul className={styles.box}>
      <li className={styles.center}>
        <SettingsIcon fontSize="small"/>
        <Link to={"/findpw"} style={{ textDecoration: "none"}} className={styles.font}>
          비밀번호 변경
        </Link>
      </li>
      <hr/>
      <li className={styles.center}>
        <LogoutIcon fontSize="small"/>
        <span className={styles.font}>로그아웃</span>
      </li>
    </ul>
  )
}

export default Dropdown