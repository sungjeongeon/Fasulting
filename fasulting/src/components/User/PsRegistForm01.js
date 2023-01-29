// import React, { useState } from "react";
// import {
//   Button,
//   TextField,
//   Typography,
//   FormControl,
//   FormHelperText,
//   Grid,
// } from "@mui/material/";
// import styled from "styled-components";
// import { Link } from "@material-ui/core";

// // mui의 css 우선순위가 높기때문에 important를 설정 - 실무하다 보면 종종 발생 우선순위 문제
// const FormHelperTexts = styled(FormHelperText)`
//   width: 100%;
//   padding-left: 16px;
//   font-weight: 700 !important;
//   color: #d32f2f !important;
// `;

// const RegisterCard = () => {
//   const [checked, setChecked] = useState(false);
//   const [emailError, setEmailError] = useState("");
//   const [passwordState, setPasswordState] = useState("");
//   const [passwordError, setPasswordError] = useState("");

//   const handleAgree = (event) => {
//     setChecked(event.target.checked);
//   };

//   const onhandlePost = async (data) => {
//     const { email, name, password } = data;
//     // const postData = { email, name, password };

//     // post
//     //     await axios
//     //       .post('/member/join', postData)
//     //       .then(function (response) {
//     //         console.log(response, '성공');
//     //         history.push('/login');
//     //       })
//     //       .catch(function (err) {
//     //         console.log(err);
//     //         setRegisterError('회원가입에 실패하였습니다. 다시한번 확인해 주세요.');
//     //       });
//   };

//   const handleSubmit = (e) => {
//     e.preventDefault();

//     const data = new FormData(e.currentTarget);
//     const joinData = {
//       email: data.get("email"),
//       password: data.get("password"),
//       rePassword: data.get("rePassword"),
//       name: data.get("name"),
//       birth: data.get("birth"),
//       phone: data.get("phone"),
//     };
//     const { email, password, rePassword } = joinData;

//     // 이메일 유효성 체크
//     const emailRegex =
//       /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
//     if (!emailRegex.test(email))
//       setEmailError("올바른 이메일 형식이 아닙니다.");
//     else setEmailError("");

//     // 비밀번호 유효성 체크
//     const passwordRegex =
//       /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
//     if (!passwordRegex.test(password))
//       setPasswordState(
//         "숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요!"
//       );
//     else setPasswordState("");

//     // 비밀번호 같은지 체크
//     if (password !== rePassword)
//       setPasswordError("비밀번호가 일치하지 않습니다.");
//     else setPasswordError("");

//     if (
//       emailRegex.test(email) &&
//       passwordRegex.test(password) &&
//       password === rePassword &&
//       checked
//     ) {
//       onhandlePost(joinData);
//     }
//   };

//   return (
//     <>
//       <Grid container spacing={2}>
//         <Grid item xs={12}>
//           <TextField
//             required
//             autoFocus
//             fullWidth
//             type="email"
//             id="email"
//             name="email"
//             label="이메일 주소"
//             error={emailError !== "" || false}
//           />
//         </Grid>
//         <FormHelperTexts>{emailError}</FormHelperTexts>
//         <Grid item xs={12}>
//           <TextField
//             required
//             fullWidth
//             type="password"
//             id="password"
//             name="password"
//             label="비밀번호"
//             error={passwordState !== "" || false}
//           />
//         </Grid>
//         <FormHelperTexts>{passwordState}</FormHelperTexts>
//         <Grid item xs={12}>
//           <TextField
//             required
//             fullWidth
//             type="password"
//             id="rePassword"
//             name="rePassword"
//             label="비밀번호 재입력"
//             error={passwordError !== "" || false}
//           />
//         </Grid>
//         <FormHelperTexts>{passwordError}</FormHelperTexts>
//       </Grid>
//       <Link href="#" variant="body2">
//         일반 회원으로 가입하시나요?
//       </Link>
//     </>
//   );
// };

// export default RegisterCard;

import React from "react";
import ReactDOM from "react-dom";
import { useFormik } from "formik";
import * as yup from "yup";
import {
  Container,
  Box,
  Typography,
  Card,
  FormControl,
  FormControlLabel,
  Radio,
  RadioGroup,
  TextField,
  Button,
  Link,
} from "@mui/material";
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
          // name="email"
          // value={formik.values.email}
          // onChange={formik.handleChange}
          // error={formik.touched.email && Boolean(formik.errors.email)}
          // helperText={formik.touched.email ? formik.errors.email : ""}
          name={email.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>비밀번호</div>
        <InputField
          fullWidth
          type="password"
          placeholder="숫자+영문자+특수문자 조합으로 8자리 이상"
          // type="password"
          // value={formik.values.password}
          // onChange={formik.handleChange}
          // error={formik.touched.password && Boolean(formik.errors.password)}
          // helperText={formik.touched.password ? formik.errors.password : ""}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>비밀번호 확인</div>
        <InputField
          fullWidth
          id="repassword"
          name="repassword"
          placeholder="비밀번호를 다시 입력해주세요."
          // type="password"
          // value={formik.values.repassword}
          // onChange={formik.handleChange}
          // error={formik.touched.repassword && Boolean(formik.errors.repassword)}
          // helperText={formik.touched.repassword ? formik.errors.repassword : ""}
        />
      </div>
    </>
  );
}
