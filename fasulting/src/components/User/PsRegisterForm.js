import React from "react";
import { useFormik } from "formik";
import * as yup from "yup";
import { TextField, Button, Link, Checkbox } from "@mui/material";
import styles from "./Form.module.css";

import { FormikStepper, FormikStep } from "formik-stepper";

const validationSchema = yup.object({
  email: yup
    .string()
    .email("올바른 이메일 주소를 입력해주세요.")
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
  psname: yup,
  psprofile: yup,
  psintro: yup,
  psaddress: yup,
  psnumber: yup,
  pshomepage: yup,
  psdirector: yup,
  psregistration: yup,
  psregistrationimg: yup,
  doctor: yup,
  category: yup,
});

export default function PsRegisterForm() {
  const formik = useFormik({
    initialValues: {
      email: "",
      password: "",
      repassword: "",
      psname: "",
      psprofile: "",
      psintro: "",
      psaddress: "",
      psnumber: "",
      pshomepage: "",
      psdirector: "",
      psregistration: "",
      psregistrationimg: "",
      doctor: "",
      category: "",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      console.log(values);
    },
  });

  return (
    <>
      <div className={styles.formwrapper}>
        <form onSubmit={formik.handleSubmit}>
          <FormikStepper
            onSumit={formik.onSubmit}
            nextButton={{ label: "Step" }}
            prevButton={{ label: "Back" }}
          >
            {/* First Step */}
            <FormikStep label="Personal Data">
              <div className={styles.inputItem}>
                <div className={styles.labelFirst}>이메일</div>
                <TextField
                  fullWidth
                  placeholder="id@fasulting.com"
                  name="email"
                  value={formik.values.email}
                  onChange={formik.handleChange}
                  error={formik.touched.email && Boolean(formik.errors.email)}
                  helperText={formik.touched.email ? formik.errors.email : ""}
                />
              </div>
              <div className={styles.inputItem}>
                <div className={styles.label}>비밀번호</div>
                <TextField
                  fullWidth
                  name="password"
                  placeholder="숫자+영문자+특수문자 조합으로 8자리 이상"
                  type="password"
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
                  id="repassword"
                  name="repassword"
                  placeholder="비밀번호를 다시 입력해주세요."
                  type="password"
                  value={formik.values.repassword}
                  onChange={formik.handleChange}
                  error={
                    formik.touched.repassword &&
                    Boolean(formik.errors.repassword)
                  }
                  helperText={
                    formik.touched.repassword ? formik.errors.repassword : ""
                  }
                />
              </div>
            </FormikStep>
            {/* Second Step */}
            <FormikStep>
              <div className={styles.inputItem}>
                <div className={styles.labelFirst}>이메일</div>
                <TextField
                  fullWidth
                  placeholder="id@fasulting.com"
                  name="email"
                  value={formik.values.email}
                  onChange={formik.handleChange}
                  error={formik.touched.email && Boolean(formik.errors.email)}
                  helperText={formik.touched.email ? formik.errors.email : ""}
                />
              </div>
              <div className={styles.inputItem}>
                <div className={styles.label}>비밀번호</div>
                <TextField
                  fullWidth
                  name="password"
                  placeholder="숫자+영문자+특수문자 조합으로 8자리 이상"
                  type="password"
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
                  id="repassword"
                  name="repassword"
                  placeholder="비밀번호를 다시 입력해주세요."
                  type="password"
                  value={formik.values.repassword}
                  onChange={formik.handleChange}
                  error={
                    formik.touched.repassword &&
                    Boolean(formik.errors.repassword)
                  }
                  helperText={
                    formik.touched.repassword ? formik.errors.repassword : ""
                  }
                />
              </div>
            </FormikStep>
            {/* Third Step */}
            <FormikStep></FormikStep>
          </FormikStepper>
        </form>
      </div>
    </>
  );
}
