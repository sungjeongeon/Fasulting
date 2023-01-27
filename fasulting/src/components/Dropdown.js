import React from "react";
import styles from "./Dropdown.module.css"
import SettingsIcon from '@mui/icons-material/Settings';
import LogoutIcon from '@mui/icons-material/Logout';

function Dropdown() {
  return (
    <ul className={styles.box}>
      <li className={styles.center}>
        <SettingsIcon fontSize="small"/>
        <span className={styles.font}>비밀번호 변경</span>
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