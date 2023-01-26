import React from "react";
import { useFormik } from "formik";
import * as yup from "yup";
import {
  Container,
  Box,
  Typography,
  Card,
  TextField,
  Button,
  Link,
  FormControlLabel,
  Checkbox,
} from "@mui/material";
import { CssBaseline } from "@mui/material";
import "react-phone-number-input/style.css";
import PhoneInput from "react-phone-number-input";

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
      /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/,
      "생년월일 8자리를 입력해주세요."
    )
    .required("생년월일을 입력해주세요."),
  phone: yup
    .string()
    .matches(/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/, "올바른 번호를 입력해주세요.")
    .required("전화번호를 입력해주세요."),
  agreement: yup
    .bool()
    .oneOf([true], "You need to accept the terms and conditions"),
});

export default function RegisterForm() {
  const formik = useFormik({
    initialValues: {
      email: "",
      password: "",
      repassword: "",
      name: "",
      birth: "",
      phone: "",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      alert(JSON.stringify(values, null, 2));
    },
  });

  return (
    <Container component="main" maxWidth="xs">
      <Box
        sx={{
          marginTop: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Typography component="h1" variant="h4">
          회원가입
        </Typography>
        <Card style={{ padding: "40px" }}>
          <form onSubmit={formik.handleSubmit}>
            <Box noValidate sx={{ mt: 1 }}>
              <TextField
                fullWidth
                id="email"
                name="email"
                label="이메일 입력"
                value={formik.values.email}
                onChange={formik.handleChange}
                margin="normal"
                error={formik.touched.email && Boolean(formik.errors.email)}
                helperText={formik.touched.email ? formik.errors.email : ""}
              />
              <TextField
                fullWidth
                id="password"
                name="password"
                label="패스워드 입력"
                type="password"
                value={formik.values.password}
                onChange={formik.handleChange}
                margin="normal"
                error={
                  formik.touched.password && Boolean(formik.errors.password)
                }
                helperText={
                  formik.touched.password ? formik.errors.password : ""
                }
              />
              <TextField
                fullWidth
                id="repassword"
                name="repassword"
                label="패스워드 재입력"
                type="password"
                value={formik.values.repassword}
                onChange={formik.handleChange}
                margin="normal"
                error={
                  formik.touched.repassword && Boolean(formik.errors.repassword)
                }
                helperText={
                  formik.touched.repassword ? formik.errors.repassword : ""
                }
              />
              <TextField
                fullWidth
                id="name"
                name="name"
                label="이름 입력"
                type="text"
                value={formik.values.name}
                onChange={formik.handleChange}
                margin="normal"
                error={formik.touched.name && Boolean(formik.errors.name)}
                helperText={formik.touched.name ? formik.errors.name : ""}
              />
              <TextField
                fullWidth
                id="birth"
                name="birth"
                label="YYYYMMDD"
                type="number"
                value={formik.values.birth}
                onChange={formik.handleChange}
                margin="normal"
                error={formik.touched.birth && Boolean(formik.errors.birth)}
                helperText={formik.touched.birth ? formik.errors.birth : ""}
              />
              <PhoneInput country="ko" />
              <TextField
                fullWidth
                id="phone"
                name="phone"
                label="전화번호를 입력해주세요."
                type="text"
                value={formik.values.phone}
                onChange={formik.handleChange}
                margin="normal"
                error={formik.touched.phone && Boolean(formik.errors.phone)}
                helperText={formik.touched.phone ? formik.errors.phone : ""}
              />
              <TextField
                type="checkbox"
                name="agreement"
                error={formik.touched.name && Boolean(formik.errors.name)}
                helperText={formik.touched.name ? formik.errors.name : ""}
              />
              개인정보제공동의
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                size="large"
              >
                회원가입
              </Button>
              <Link href="/psregist" variant="body2">
                의사로 회원가입 하시나요?
              </Link>
            </Box>
          </form>
        </Card>
      </Box>
    </Container>
  );
}
