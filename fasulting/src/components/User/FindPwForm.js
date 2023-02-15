import React, { useEffect, useState } from "react";
import { useFormik } from "formik";
import * as yup from "yup";
import { Typography, TextField, Button } from "@mui/material";
import styles from "./Form.module.css";
import axiosAPi from "../../api/axiosApi";
const emailValidationSchema = yup.object({
  email: yup
    .string()
    .email("올바른 이메일 형식이 아닙니다.")
    .required("이메일을 입력해주세요."),
});
const passwordValidationSchema = yup.object({
  password: yup
    .string()
    .matches(
      /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/,
      "숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요."
    )
    .required("비밀번호를 입력해주세요."),
  repassword: yup
    .string()
    .oneOf([yup.ref("password")], "비밀번호가 일치하지 않습니다.")
    .required("비밀번호를 재입력해주세요."),
});

export default function LoginForm() {
  const [isSended, setIsSended] = useState(false);
  const [timer, setTimer] = useState(0);
  const [code, setCode] = useState("");

  useEffect(() => {
    const id = setInterval(() => {
      setTimer((timer) => timer - 1);
    }, 1000);
    if (timer === 0) {
      clearInterval(id);
    }
    return () => clearInterval(id);
  }, [timer]);

  const emailFormik = useFormik({
    initialValues: {
      email: "",
    },
    validationSchema: emailValidationSchema,
    onSubmit: (values) => {
      // id 중복 여부 확인
      axiosAPi
        .get(`/user/${values.email}`)
        .then((res) => {
          if (res.status === 200) {
            // id 있음, 인증코드 발송
            console.log("id 있음");
            axiosAPi
              .get(`/email/reset/${values.email}`)
              .then((res) => {
                console.log(res);
                setIsSended(true);
                setTimer(180);
              })
              .catch((e) => console.log(e));
          } else {
            // id 없음, 알림창
            console.log("id 없음");
            alert("등록되지 않은 ID입니다.");
          }
        })
        .catch((e) => console.log(e));
    },
  });

  const passwordFormik = useFormik({
    initialValues: {
      password: "",
      repassword: "",
    },
    validationSchema: passwordValidationSchema,
    onSubmit: (values) => {
      console.log(values);
    },
  });

  const codeChanged = (e) => {
    setCode(e.target.value);
  };

  const submitCode = () => {
    axiosAPi
      .post("/email/access", {
        email: emailFormik.values.email,
        emailCode: code,
      })
      .then((res) => console.log(res))
      .catch((e) => console.log(e));
  };

  return (
    <div className={styles.formwrapper}>
      <h1>{timer}</h1>
      <Typography variant="h4" sx={{ fontWeight: "bold" }}>
        비밀번호 찾기
      </Typography>
      <div className={styles.inputForm}>
        <form onSubmit={emailFormik.handleSubmit}>
          {/* 이메일 확인 form */}
          <div className={styles.label}>이메일</div>
          <div className={styles.emailForm}>
            <TextField
              sx={{ width: "80%" }}
              id="email"
              name="email"
              placeholder="아이디(이메일)을 입력해주세요."
              value={emailFormik.values.email}
              onChange={emailFormik.handleChange}
              error={
                emailFormik.touched.email && Boolean(emailFormik.errors.email)
              }
              helperText={
                emailFormik.touched.email ? emailFormik.errors.email : ""
              }
            />
            {!isSended ? (
              <Button
                color="primary"
                variant="contained"
                type="submit"
                sx={{ width: "18%", height: "56px" }}
              >
                인증코드 전송
              </Button>
            ) : (
              <Button
                color="primary"
                variant="outlined"
                type="submit"
                sx={{ width: "18%", height: "56px" }}
              >
                재전송
              </Button>
            )}
          </div>
          <Typography
            variant="subtitle1"
            sx={{ mt: 1 }}
            color="primary"
            gutterBottom
          >
            인증번호가 전송되었습니다 ( 남은 시간 {parseInt(timer / 60)} :{" "}
            {String(timer % 60).padStart(2, "0")} )
          </Typography>
          {/* 인증코드 확인 Form */}
          <div className={styles.label}>인증코드</div>
          <div className={styles.codeForm}>
            <TextField
              id="code"
              name="code"
              value={code}
              onChange={codeChanged}
              placeholder="인증코드를 입력해주세요."
              sx={{ width: "80%" }}
            />
            <Button
              color="primary"
              variant="contained"
              onClick={submitCode}
              sx={{ width: "18%", height: "56px" }}
            >
              확인
            </Button>
          </div>
        </form>
        <form onSubmit={passwordFormik.handleSubmit}>
          {/* 비밀번호 입력 Form */}
          <div className={styles.label}>새 비밀번호</div>
          <TextField
            fullWidth
            name="password"
            placeholder="숫자+영문자+특수문자 조합 8자리 이상"
            type="password"
            autoComplete="off"
            value={passwordFormik.values.password}
            onChange={passwordFormik.handleChange}
            error={
              passwordFormik.touched.password &&
              Boolean(passwordFormik.errors.password)
            }
            helperText={
              passwordFormik.touched.password
                ? passwordFormik.errors.password
                : ""
            }
          />
          {/* 비밀번호 확인 Form */}
          <div className={styles.label}>비밀번호 확인</div>
          <TextField
            fullWidth
            name="repassword"
            placeholder="비밀번호를 다시 입력해주세요."
            type="password"
            autoComplete="off"
            value={passwordFormik.values.repassword}
            onChange={passwordFormik.handleChange}
            error={
              passwordFormik.touched.repassword &&
              Boolean(passwordFormik.errors.repassword)
            }
            helperText={
              passwordFormik.touched.repassword
                ? passwordFormik.errors.repassword
                : ""
            }
          />
          <Button
            fullWidth
            color="primary"
            variant="contained"
            type="submit"
            sx={{ mt: 4 }}
          >
            비밀번호 변경
          </Button>
        </form>
      </div>
    </div>
  );
}
