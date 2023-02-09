import React from "react";
import styles from "./Header.module.css";
import { Link, useLocation } from "react-router-dom";
import { useState } from "react";
import ArrowDropDownIcon from "@mui/icons-material/ArrowDropDown";
import ArrowDropUpIcon from "@mui/icons-material/ArrowDropUp";
import Dropdown from "./Dropdown";
import { useSelector } from "react-redux";
import { useEffect } from "react";
function Header() {
  // 로그인 여부에 따라, nav bar 링크가 달라지므로 state 이용

  const [is_login, setIsLogin] = useState(false);
  const userData = useSelector((store) => store.user);
  console.log(userData);
  useEffect(() => {
    if (userData.userSeq) {
      setIsLogin(true);
    } else {
      setIsLogin(false);
    }
  }, [userData]);
  const nowpath = useLocation().pathname;

  // 병원회원인지에 따라 nav bar 또 변경
  const [isHos, setIsHos] = useState(false);

  // 드롭다운 클릭 state
  const [open, setOpen] = useState(false);
  const openState = () => setOpen((current) => !current);

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
        {is_login ? (
          isHos ? (
            <div>
              <div className={`${styles.mypageho} ${styles.common}`}>
                <Link
                  to={"/mypageho"}
                  style={{ textDecoration: "none" }}
                  className={
                    nowpath === "/mypageho"
                      ? styles.activate
                      : styles.notactivate
                  }
                >
                  마이페이지
                </Link>
              </div>
              <div className={`${styles.manage} ${styles.common}`}>
                <Link
                  to={"/myreservationho"}
                  style={{ textDecoration: "none" }}
                  className={
                    nowpath === "/myreservationho"
                      ? styles.activate
                      : styles.notactivate
                  }
                >
                  예약관리
                </Link>
              </div>
              <div className={`${styles.greeting} ${styles.common}`}>
                <span className={styles.activate}>psname</span>
                <span>님</span>
                <br />
                <span className={styles.center}>반갑습니다!</span>
              </div>
            </div>
          ) : (
            <div>
              <div className={`${styles.reserve} ${styles.common}`}>
                <Link
                  to={"/myreservation"}
                  style={{ textDecoration: "none" }}
                  className={
                    nowpath === "/myreservation"
                      ? styles.activate
                      : styles.notactivate
                  }
                >
                  나의 예약
                </Link>
              </div>
              <div className={`${styles.myactivity} ${styles.common}`}>
                <Link
                  to={"/myactivity"}
                  style={{ textDecoration: "none" }}
                  className={
                    nowpath === "/myactivity"
                      ? styles.activate
                      : styles.notactivate
                  }
                >
                  나의 활동
                </Link>
              </div>
              <div
                className={`${styles.greeting} ${styles.common}`}
                onClick={openState}
              >
                <div>
                  <span className={styles.activate}>{userData.userName}</span>
                  <span>님</span>
                  <br />
                  <span className={styles.center}>
                    반갑습니다!
                    <div className={styles.btn}>
                      {open ? <ArrowDropUpIcon /> : <ArrowDropDownIcon />}
                    </div>
                  </span>
                </div>
                {open && <Dropdown />}
              </div>
            </div>
          )
        ) : // 로그인 안되어있을 때 (+) 로그인이나 회원가입 페이지로 갔을 때는 logo 제외 안보이게끔
        nowpath === "/login" || nowpath === "/register" ? null : (
          <div>
            <div className={`${styles.login} ${styles.common}`}>
              <Link
                to={"/login"}
                style={{ textDecoration: "none" }}
                className={styles.notactivate}
              >
                로그인
              </Link>
            </div>
            <div className={`${styles.register} ${styles.common}`}>
              <Link
                to={"/register"}
                style={{ textDecoration: "none" }}
                className={styles.notactivate}
              >
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
