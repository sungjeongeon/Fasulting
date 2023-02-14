import React from "react";
import { Formik, useFormik } from "formik";
import * as yup from "yup";
import "react-toastify/dist/ReactToastify.css";
import { toast, ToastContainer } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import {
  TextField,
  Button,
  Link,
  Checkbox,
  Typography,
  Container,
  Box,
  FormControlLabel,
} from "@mui/material";
import styles from "./Form.module.css";
import Paper from "@mui/material/Paper";
import axiosAPi from "../../api/axiosApi";
import { persistReducer } from "redux-persist";
import { classNames } from "clsx";

const validationSchema = yup.object({
  email: yup
    .string()
    .email("올바른 이메일 형식이 아닙니다.")
    .required("이메일을 입력해주세요."),
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
  name: yup
    .string()
    .matches(/^[가-힣a-zA-Z]+$/, "올바른 이름을 입력해주세요.")
    .required("이름을 입력해주세요."),
  birth: yup
    .string()
    .matches(
      /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/,
      "생년월일 8자리를 입력해주세요."
    )
    .required("생년월일을 입력해주세요."),
  number: yup
    .string()
    .matches(/^[0-9]{2,3}[0-9]{3,4}[0-9]{4}/, "올바른 번호를 입력해주세요.")
    .required("전화번호를 입력해주세요."),
  agreement: yup.bool().oneOf([true], "약관에 동의해주세요."),
});

export default function RegisterForm() {
  const navigate = useNavigate();

  const [useable, setUseable] = useState(null);
  //아이디 중복체크
  const idCheck = (e) => {
    e.preventDefault();
    axiosAPi.get(`/user/${formik.values.email}`).then((response) => {
      console.log(response.data);
      if (response.status === 200) {
        //alert("이미 존재하는 아이디입니다."); // 백엔드로 보낸 데이터 결과 200 일 경우
        setUseable(false);
      } else if (response.status === 204) {
        //alert("사용 가능한 아이디입니다.");
        setUseable(true);
      } else {
        alert("사용 불가한 아이디입니다.");
      }
    });
  };
  const formik = useFormik({
    initialValues: {
      email: "",
      password: "",
      repassword: "",
      name: "",
      birth: "",
      number: "",
      agreement: false,
    },
    validationSchema,
    onSubmit: async (values) => {
      const { email, password, repassword, name, birth, number } = values;
      try {
        await axiosAPi.post("/user/regist", {
          email,
          password,
          repassword,
          name,
          birth,
          number,
        });
        toast.success(
          <h3>
            회원가입이 완료되었습니다.
            <br />
            로그인 하세요😄
          </h3>,
          {
            position: toast.POSITION.TOP_CENTER,
            autoClose: 2000,
          }
        );
        setTimeout(() => {
          navigate("/login");
        }, 2000);
      } catch (e) {
        // 서버에서 받은 에러 메시지 출력
        console.log(e.response.data.message);
      }
    },
  });

  const navigatePsregist = () => {
    navigate("/psregist");
  };
  return (
    <>
      <ToastContainer />
      <Container component="main" maxWidth="sm" sx={{ mb: 4 }}>
        <Paper
          variant="outlined"
          sx={{ my: { xs: 3, md: 6 }, p: { xs: 2, md: 3 } }}
        >
          <Typography component="h1" variant="h4" align="center">
            회원가입
          </Typography>

          <form onSubmit={formik.handleSubmit}>
            <div className={styles.inputItem}>
              <div className={styles.labelFirst}>이메일</div>
              <div class={styles.flexInput}>
                <TextField
                  className={styles.textfield}
                  placeholder="id@fasulting.com"
                  name="email"
                  value={formik.values.email}
                  onChange={formik.handleChange}
                  error={formik.touched.email && Boolean(formik.errors.email)}
                  helperText={formik.touched.email ? formik.errors.email : ""}
                />
                <span className={styles.btn} onClick={idCheck}>
                  중복확인
                </span>
              </div>
              {useable === null ? (
                ""
              ) : useable ? (
                <span className={styles.trueColor}>
                  사용가능한 아이디입니다.
                </span>
              ) : (
                <span className={styles.errorColor}>
                  이미 사용중인 아이디 입니다.
                </span>
              )}
            </div>
            <div className={styles.inputItem}>
              <div className={styles.label}>비밀번호</div>
              <TextField
                fullWidth
                name="password"
                placeholder="숫자+영문자+특수문자 조합으로 8자리 이상"
                type="password"
                autoComplete="off"
                value={formik.values.password}
                onChange={formik.handleChange}
                error={
                  formik.touched.password && Boolean(formik.errors.password)
                }
                helperText={
                  formik.touched.password ? formik.errors.password : ""
                }
              />
            </div>
            <div className={styles.inputItem}>
              <div className={styles.label}>비밀번호 확인</div>
              <TextField
                fullWidth
                name="repassword"
                placeholder="비밀번호를 다시 입력해주세요."
                type="password"
                autoComplete="off"
                value={formik.values.repassword}
                onChange={formik.handleChange}
                error={
                  formik.touched.repassword && Boolean(formik.errors.repassword)
                }
                helperText={
                  formik.touched.repassword ? formik.errors.repassword : ""
                }
              />
            </div>
            <div className={styles.inputItem}>
              <div className={styles.label}>이름</div>
              <TextField
                fullWidth
                name="name"
                placeholder="이름을 입력해주세요."
                type="text"
                value={formik.values.name}
                onChange={formik.handleChange}
                error={formik.touched.name && Boolean(formik.errors.name)}
                helperText={formik.touched.name ? formik.errors.name : ""}
              />
            </div>
            <div className={styles.inputItem}>
              <div className={styles.label}>생년월일</div>
              <TextField
                fullWidth
                name="birth"
                placeholder="YYYYMMDD"
                value={formik.values.birth}
                onChange={formik.handleChange}
                error={formik.touched.birth && Boolean(formik.errors.birth)}
                helperText={formik.touched.birth ? formik.errors.birth : ""}
              />
            </div>
            {/* <div className={styles.inputItem}>
              <div className={styles.label}>생년월일</div>
              <PhoneInput country="ko" />
              </div> */}
            <div className={styles.inputItem}>
              <div className={styles.label}>휴대폰 번호</div>
              <TextField
                fullWidth
                name="number"
                placeholder="하이픈(-) 없이 입력해주세요."
                value={formik.values.number}
                onChange={formik.handleChange}
                error={formik.touched.number && Boolean(formik.errors.number)}
                helperText={formik.touched.number ? formik.errors.number : ""}
              />
            </div>
            <div className={styles.inputItem}>
              <div className={`${styles.label} ${styles.labelcolor}`}>
                개인정보 제공 동의
              </div>
              <div>
                <FormControlLabel
                  control={
                    <Checkbox
                      name="agreement"
                      color="primary"
                      required
                      onChange={formik.handleChange}
                      checked={formik.values.agreement}
                    />
                  }
                  label={
                    <Box component="div" fontSize={12}>
                      예약 진행, 고객 상담, 고객 관리 및 고객 문의를 위해 예약자
                      이름, 예약자 휴대폰 번호를 수집하여 해당 병원 업체에
                      제공함에 동의합니다.
                    </Box>
                  }
                />
              </div>
            </div>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              size="large"
            >
              회원가입
            </Button>
          </form>
          <span onClick={navigatePsregist} className={styles.flexCenter}>
            의사로 회원가입 하시나요?
          </span>
        </Paper>
      </Container>
    </>
  );
}
