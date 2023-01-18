import React from "react";
import styles from "./Header.module.css"
import { Link } from "react-router-dom"
import { useState } from "react";

function Header() {
  // 로그인 여부에 따라, nav bar 링크가 달라지므로 state 이용
  const [login, setLogin] = useState(true)

  return (
    <div>
      <h1>Header</h1>
      <div className={styles.navbar}>
        <img className={styles.logo} src="assets/images/fasulting_logo.png" alt="logo"/>
        {login ? 
        <div>
          <p className={`${styles.reserve} ${styles.common}`}><Link to={"/reserve"} style={{textDecoration: "none"}}>나의 예약</Link></p>
          <p className={`${styles.mypage} ${styles.common}`}><Link to={"/mypage"} style={{textDecoration: "none"}}>나의 활동</Link></p>
          <p className={`${styles.greeting} ${styles.common}`}>반갑습니다!</p>
        </div>
        : 
        <div>
          <p className={`${styles.login} ${styles.common}`}><Link to={"/login"} style={{textDecoration: "none"}}>로그인</Link></p>
          <p className={`${styles.register} ${styles.common}`}><Link to={"/register"} style={{textDecoration: "none"}}>회원가입</Link></p>
        </div>
        }
      </div>
    </div>
  );
}

export default Header;
