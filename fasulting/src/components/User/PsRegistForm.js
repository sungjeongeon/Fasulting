import React from "react";
import * as yup from "yup";
import {
  FormikStepper,
  FormikStep,
  InputField,
  RadioField,
  SelectField,
  CheckBoxField,
} from "formik-stepper";
import { Formik, Field, ErrorMessage, useFormik } from "formik";
import { useState } from "react";
import "formik-stepper/dist/style.css";
import { FormGroup, Input, TextField } from "@mui/material";
import { Label } from "@mui/icons-material";
import { toast, ToastContainer } from "react-toastify";
import { Container } from "@mui/system";
import { CssBaseline, Paper, Typography } from "@mui/material";
import axiosAPi from "../../api/axiosApi";
import { useNavigate } from "react-router-dom";
import styles from "./PsRegistForm.module.css";
const validationSchema = yup.object().shape({
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

  psname: yup.string().required("병원명을 입력해주세요."),
  psprofile: yup.string(),
  psintro: yup.string(),
  psaddress: yup.string().required("병원 주소를 입력해주세요."),
  psnumber: yup
    .string()
    .matches(
      /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]))-(\d{3,4})-(\d{4})$/,
      "하이픈(-)을 포함한 전화번호를 입력해주세요."
    )
    .required("병원 전화번호를 입력해주세요."),
  pshomepage: yup
    .string()
    .matches(
      /(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/,
      "올바른 주소를 입력해주세요."
    ),

  psdirector: yup
    .string()
    .matches(/^[가-힣a-zA-Z]+$/, "올바른 이름을 입력해주세요.")
    .required("병원장 이름을 입력해주세요."),
  psregistration: yup
    .string()
    .matches(
      /^[0-9]{3}-[0-9]{2}-[0-9]{5}$/,
      "000-00-00000 형식에 맞게 입력해주세요."
    )
    .required("사업자 등록번호를 입력해주세요."),
  //psregistrationimg: yup.mixed().required("사업자 등록증을 업로드해주세요."),
});

export default function PsRegistForm() {
  const navigate = useNavigate();
  const [resimg, setResimg] = useState([]);
  const [proimg, setProimg] = useState([]);
  const onChange = (e) => {
    const uploadimg = e.target.files[0];
    setResimg(uploadimg);
  };
  const onChangepro = (e) => {
    const uploadimg = e.target.files[0];
    setProimg(uploadimg);
  };
  const onSubmit = async (values) => {
    //window.alert(JSON.stringify(values, null, 2));
    // setSubmitting(false); //// Important
    console.log("values", values);
    const formData = new FormData();
    formData.append("email", values.email);
    formData.append("password", values.password);
    formData.append("name", values.psname);
    formData.append("address", values.psaddress);
    formData.append("registration", values.psregistration);
    formData.append("number", values.psnumber);
    formData.append("director", values.psdirector);
    if (values.pshomepage === "") {
    } else {
      formData.append("hompage", values.pshompage); //null가능
    }
    if (values.psintro === "") {
    } else {
      formData.append("intro", values.psintro); //null 가능
    }
    formData.append("registrationImg", resimg);
    if (proimg.length === 0) {
    } else {
      formData.append("profileImg", proimg); //null 가능
    }
    // for (var key of formData.keys()) {
    //   console.log(key);
    // }

    // for (var value of formData.values()) {
    //   console.log(value);
    // }
    try {
      await axiosAPi.post("/ps/regist", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      toast.success(
        <h3>
          회원가입이 완료되었습니다.
          <br />
          승인 대기중입니다.
        </h3>,
        {
          position: toast.POSITION.TOP_CENTER,
          autoClose: 2000,
        }
      );
      setTimeout(() => {
        navigate("/");
      }, 2000);
    } catch (e) {
      console.log(e.response.data.message);
    }
  };

  return (
    <>
      <ToastContainer />
      <Container component="main" maxWidth="sm" sx={{ mb: 4 }}>
        <CssBaseline />
        <Paper
          variant="outlined"
          sx={{ my: { xs: 3, md: 6 }, p: { xs: 2, md: 3 } }}
        >
          <Typography component="h1" variant="h4" align="center">
            병원 등록
          </Typography>
          <FormikStepper
            /// Accept all Formik props
            onSubmit={onSubmit} /// onSubmit Function
            initialValues={{
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
            }}
            validationSchema={validationSchema}
            labelsColor="secondary" /// The text label color can be root variables or css => #fff
            withStepperLine /// false as default and If it is false, it hides stepper line
            // nextBtnLabel="step" /// Next as defaul
            submitButton={{
              label: "회원가입",
              style: { background: "#72A1A6" },
            }}
            prevButton={{ label: "이전", style: { background: "#7C7877" } }}
            nextButton={{ label: "다음", style: { background: "#72A1A6" } }}
          >
            {/*  First Step */}
            <FormikStep
              label="개인 정보" /// The text label of Step
              withIcons="fa fa-user" // to add icon into the circle must add icon as class Name like Fontawesome
              withNumbers /// If true, it hides the icon and shows the step number
              labelColor="#72A1A6" /// css-colors => #fff
              circleColor="#72A1A6" /// css-colors => #fff
            >
              <div className={styles.inputItem}>
                <div className={styles.labelFirst}>이메일</div>
                <InputField placeholder="id@fasulting.com" name="email" />
              </div>
              <div className={styles.inputItem}>
                <div className={styles.label}>비밀번호</div>
                <InputField
                  type="password"
                  placeholder="숫자+영문자+특수문자 조합으로 8자리 이상"
                  autoComplete="off"
                  name="password"
                />
              </div>
              <div className={styles.inputItem}>
                <div className={styles.label}>비밀번호 확인</div>
                <InputField
                  type="password"
                  placeholder="비밀번호를 다시 입력해주세요."
                  autoComplete="off"
                  name="repassword"
                />
              </div>
            </FormikStep>
            {/* Second Step */}
            <FormikStep
              label="병원 관련 등록"
              withIcons="fa fa-lock"
              iconColor="white"
              labelColor="#72A1A6" /// css-colors => #fff
              circleColor="#72A1A6"
            >
              <div className={styles.inputItem}>
                <div className={styles.labelFirst}>병원명</div>
                <InputField placeholder="병원명을 입력해주세요" name="psname" />
              </div>
              <div className={`${styles.inputItem} ${styles.marginBottom}`}>
                <div className={`${styles.label} ${styles.margin}`}>
                  병원 프로필사진 (선택)
                </div>
                <label className={styles.inputfile} htmlFor="inputfile">
                  파일 선택
                </label>
                <input
                  id="inputfile"
                  placeholder="병원 프로필사진"
                  type="file"
                  accept="image/jpg,impge/png,image/jpeg,image/gif"
                  onChange={onChangepro}
                  style={{ display: "none" }}
                ></input>
                {proimg.name}
              </div>
              <div className={styles.inputItem}>
                <div className={styles.label}>병원 한줄 소개 (선택)</div>
                <InputField
                  placeholder="병원 한줄 소개를 입력해주세요"
                  name="psintro"
                />
              </div>
              <div className={styles.inputItem}>
                <div className={styles.label}>병원 주소</div>
                <InputField
                  placeholder="병원 주소를 입력해주세요"
                  name="psaddress"
                />
              </div>
              {/* <div className={styles.inputItem}>
        <div className={styles.label}>병원 주소 </div>
        {isOpen && (
          <DaumPost
            onToggleModal={onToggleModal}
            address={psAddress}
            setAddress={setpsAddress}
          />
        )}
        <InputField
          fullWidth
          placeholder="병원 주소를 입력해주세요."
          name={psaddress"
          value={psAddress}
          onClick={onToggleModal}
        />
        {/* <Button type="primary" onClick={onToggleModal}>
          주소 찾기
        </Button> *
      </div> */}
              <div className={styles.inputItem}>
                <div className={styles.label}>병원 전화번호</div>
                <InputField
                  placeholder="병원 전화번호를 입력해주세요."
                  name="psnumber"
                />
              </div>
              <div className={styles.inputItem}>
                <div className={styles.label}>병원 홈페이지URL (선택)</div>
                <InputField
                  placeholder="병원 홈페이지URL을 입력해주세요."
                  name="pshomepage"
                />
              </div>
            </FormikStep>

            {/* Third Step */}

            <FormikStep
              label="병원 인증"
              withIcons="fa fa-lock"
              labelColor="#72A1A6" /// css-colors => #fff
              circleColor="#72A1A6"
            >
              <div className={styles.inputItem}>
                <div className={styles.labelFirst}>병원장</div>
                <InputField
                  placeholder="병원장 이름을 입력해주세요"
                  name="psdirector"
                />
              </div>
              <div className={styles.inputItem}>
                <div className={styles.label}>병원 사업자 등록번호</div>
                <InputField
                  placeholder="병원 사업자 등록 번호를 입력해주세요"
                  name="psregistration"
                />
              </div>

              <div className={`${styles.inputItem} ${styles.marginBottom}`}>
                <div className={`${styles.label} ${styles.margin}`}>
                  병원 사업자 등록증
                </div>
                <label className={styles.inputfile} htmlFor="inputfile">
                  파일 선택
                </label>
                <input
                  id="inputfile"
                  placeholder="병원 사업자 등록증을 업로드 해주세요"
                  type="file"
                  accept="image/jpg,impge/png,image/jpeg,image/gif"
                  onChange={onChange}
                  style={{ display: "none" }}
                ></input>
                {resimg.name}
              </div>
            </FormikStep>
          </FormikStepper>
        </Paper>
      </Container>
    </>
  );
}
