import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import axiosAPi from "../../api/axiosApi";
import styles from "./PsRegistForm.module.css";

export default function InputEmail(props) {
  //아이디 중복체크
  const [id, setId] = useState("");

  const changeId = (e) => {
    setId(e.target.value);
  };
  const idCheck = (e) => {
    e.preventDefault();
    // const id = idRef.current.value;
    const regEmail =
      /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
    props.setUseable(null);
    if (regEmail.test(id)) {
      console.log(id);
      //유효성 검사 통과
      props.setReg(true);
      axiosAPi.get(`/ps/${id}`).then((response) => {
        if (response.status === 200) {
          //alert("이미 존재하는 아이디입니다."); // 백엔드로 보낸 데이터 결과 200 일 경우
          props.setUseable(false);
        } else if (response.status === 204) {
          //alert("사용 가능한 아이디입니다.");
          props.setUseable(true);
        } else {
          alert("사용 불가한 아이디입니다.");
        }
      });
    } else {
      props.setReg(false);
      props.setUseable(null);
    }
    props.setValueid(id);
  };

  useEffect(() => {
    setId(props.valueid);
  }, [props.valueid]);
  return (
    <div>
      <div className={styles.emailForm}>
        <input
          className={styles.input}
          type="text"
          placeholder="example@example.com"
          name="email"
          // ref={idRef}
          value={id}
          onChange={changeId}
        />
        <span className={styles.btn} onClick={idCheck}>
          중복확인
        </span>
      </div>
      {props.reg === null ? (
        ""
      ) : !props.reg ? (
        <div className={styles.errorColor}>
          올바른 이메일 형식을 입력해주세요.
        </div>
      ) : props.useable === null ? (
        ""
      ) : props.useable ? (
        <div className={styles.trueColor}>사용가능한 아이디입니다.</div>
      ) : (
        <div className={styles.errorColor}>이미 존재하는 아이디 입니다.</div>
      )}
    </div>
  );
}
