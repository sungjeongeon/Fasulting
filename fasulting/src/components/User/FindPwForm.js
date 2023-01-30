import React from "react";
import { useFormik } from "formik";
import * as yup from "yup";
import { Typography, TextField, Button } from "@mui/material";
import styles from "./Form.module.css";
const validationSchema = yup.object({
  email: yup
    .string()
    .email("올바른 이메일 형식이 아닙니다.")
    .required("이메일을 입력해주세요."),
});

export default function LoginForm() {
  const formik = useFormik({
    initialValues: {
      email: "",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      alert(JSON.stringify(values, null, 2));
    },
  });

  return (
    <div className={styles.formwrapper}>
      <Typography variant="h4" sx={{ fontWeight: "bold" }}>
        비밀번호 찾기
      </Typography>
      <form onSubmit={formik.handleSubmit}>
        <div className={styles.inputForm}>
          <div className={styles.inputItem}>
            <div className={styles.label}>이메일</div>
            <TextField
              fullWidth
              id="email"
              name="email"
              placeholder="아이디(이메일)을 입력해주세요."
              value={formik.values.email}
              onChange={formik.handleChange}
              error={formik.touched.email && Boolean(formik.errors.email)}
              helperText={formik.touched.email ? formik.errors.email : ""}
            />
          </div>
          <Button
            color="primary"
            variant="contained"
            fullWidth
            type="submit"
            sx={{ mt: 3, mb: 2 }}
          >
            인증번호 보내기
          </Button>
        </div>
      </form>
    </div>
  );
}
