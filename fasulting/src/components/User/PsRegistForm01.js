import React from "react";
import styles from "./Form.module.css";
import InputField from "./InputField";

export default function PsRegistForm01(props) {
  const {
    formField: { email, password, repassword },
  } = props;

  return (
    <>
      <div className={styles.inputItem}>
        <div className={styles.labelFirst}>이메일</div>
        <InputField
          fullWidth
          placeholder="id@fasulting.com"
          name={email.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>비밀번호</div>
        <InputField
          fullWidth
          type="password"
          placeholder="숫자+영문자+특수문자 조합으로 8자리 이상"
          autoComplete="off"
          name={password.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>비밀번호 확인</div>
        <InputField
          fullWidth
          type="password"
          placeholder="비밀번호를 다시 입력해주세요."
          autoComplete="off"
          name={repassword.name}
        />
      </div>
    </>
  );
}
