import React from "react";
import styles from "./Header.module.css";
import { Link, useLocation } from "react-router-dom";
import { useState } from "react";
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import ArrowDropUpIcon from '@mui/icons-material/ArrowDropUp';
import Dropdown from "./Dropdown";
import Paper from '@mui/material/Paper';


function Header() {
  // 로그인 여부에 따라, nav bar 링크가 달라지므로 state 이용
  const [login, setLogin] = useState(true);
  const nowpath = useLocation().pathname;

  // 드롭다운 클릭 state
  const [open, setOpen] = useState(false)
  const openState = () => setOpen((current) => !current)

  return (
    <div>
      <div className={styles.header}>
        <Link to="/">
          <img
            className={styles.logo}
            src="/assets/images/fasulting_logo.png"
            alt="logo"
          />
        </Link>
        {login ? (
          <div>
            <div className={`${styles.reserve} ${styles.common}`}>
              <Link to={"/myreservation"} style={{ textDecoration: "none" }}
                className={nowpath === "/myreservation" ? styles.activate : styles.notactivate}
              >
                나의 예약
              </Link>
            </div>
            <div className={`${styles.mypage} ${styles.common}`}>
              <Link to={"/myactivity"} style={{ textDecoration: "none" }}
                className={nowpath === "/myactivity" ? styles.activate : styles.notactivate}
              >
                나의 활동
              </Link>
            </div>
            <div className={`${styles.greeting} ${styles.common}`}
              onClick={openState}
            >
              <div>
                <span className={styles.activate}>username</span>
                <span>님</span><br />
                <span className={styles.center}>반갑습니다!
                  <div className={styles.btn}>
                    { open ? <ArrowDropUpIcon /> : <ArrowDropDownIcon /> } 
                  </div>
                </span>
              </div>
              { open && <Dropdown />}
            </div>
          </div>
        ) : // 로그인이나 회원가입 페이지로 갔을 때는 logo 제외 안보이게끔
        nowpath === "/login" || nowpath === "/register" ? null : (
          <div>
            <div className={`${styles.login} ${styles.common}`}>
              <Link to={"/login"} style={{ textDecoration: "none" }}>
                로그인
              </Link>
            </div>
            <div className={`${styles.register} ${styles.common}`}>
              <Link to={"/register"} style={{ textDecoration: "none" }}>
                회원가입
              </Link>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default Header;
