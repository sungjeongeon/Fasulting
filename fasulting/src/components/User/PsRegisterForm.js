import React from "react";
import { useFormik } from "formik";
import * as yup from "yup";
import { TextField, Button, Link, Checkbox } from "@mui/material";
import styles from "./Form.module.css";

import { FormikStepper, FormikStep } from "formik-stepper";
import { Field, Form, Formik, FormikConfig, FormikValues } from "formik";

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
      <FormikStepper /// Accept all Formik props
        onSubmit={formik.handleSubmit} /// onSubmit Function
        orientation="vertical"
        labelsColor="secondary" /// The text label color can be root variables or css => #fff
        withStepperLine /// false as default and If it is false, it hides stepper line
        nextBtnLabel="step" /// Next as default
        prevBtnLabel="return" /// Prev as default
        submitBtnLabel="Done" /// Submit as default
        nextBtnColor="primary" /// as default and The color can be root variables or css => #fff
        prevBtnColor="danger" /// as default and The color can be root variables or css => #fff
        submitBtnColor="success" /// as default and The color can be root variables or css => #fff
      >
        {/* First Step */}
        <FormikStep
          label="Profile Info" /// The text label of Step
          withIcons="fa fa-user" // to add icon into the circle must add icon as class Name like Fontawesome
          withNumbers /// If true, it hides the icon and shows the step number
          iconColor="white" /// The color can be root variables or css => #fff
          circleColor="danger" /// The color can be root variables or css => #fff
        >
          <div className={styles.inputItem}>
            <div className={styles.labelFirst}>이메일</div>
            <InputField
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
              error={formik.touched.password && Boolean(formik.errors.password)}
              helperText={formik.touched.password ? formik.errors.password : ""}
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
                formik.touched.repassword && Boolean(formik.errors.repassword)
              }
              helperText={
                formik.touched.repassword ? formik.errors.repassword : ""
              }
            />
          </div>
        </FormikStep>
        {/* Second Step */}
        <FormikStep>
          {/* <div className={styles.inputItem}>
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
              error={formik.touched.password && Boolean(formik.errors.password)}
              helperText={formik.touched.password ? formik.errors.password : ""}
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
                formik.touched.repassword && Boolean(formik.errors.repassword)
              }
              helperText={
                formik.touched.repassword ? formik.errors.repassword : ""
              }
            />
          </div> */}
        </FormikStep>
        {/* Third Step */}
        <FormikStep></FormikStep>
      </FormikStepper>
    </>
  );
}
