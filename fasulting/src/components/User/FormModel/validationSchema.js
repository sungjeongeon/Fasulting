import * as yup from "yup";
import checkoutFormModel from "./checkoutFormModel";
const {
  formField: {
    email,
    password,
    repassword,
    psname,
    psprofile,
    psintro,
    psaddress,
    psnumber,
    pshomepage,
    psdirector,
    psregistration,
    psregistrationimg,
    doctor,
    category,
  },
} = checkoutFormModel;

export default [
  yup.object().shape({
    [email.name]: yup
      .string()
      .email("올바른 이메일 형식이 아닙니다.")
      .required("이메일을 입력해주세요."),
    [password.name]: yup
      .string()
      .matches(
        /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/,
        "숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요."
      )
      .required("비밀번호를 입력해주세요."),
    [repassword.name]: yup
      .string()
      .oneOf([yup.ref("password")], "비밀번호가 일치하지 않습니다.")
      .required("비밀번호를 재입력해주세요."),
  }),
  yup.object().shape({
    [psname.name]: yup
      .string()
      .matches(/^[가-힣a-zA-Z]+$/, "올바른 이름을 입력해주세요.")
      .required("이름을 입력해주세요."),
    [psprofile.name]: yup.string().required("프로필사진을 첨부해주세요."),
    [psintro.name]: yup.string().required("한줄 소개를 입력해주세요."),
    [psaddress.name]: yup.string().required("병원 주소를 입력해주세요."),
    [psnumber.name]: yup.string(),
    [pshomepage.name]: yup.string(),
  }),
  yup.object().shape({
    psdirector,
    psregistration,
    psregistrationimg,
    doctor,
    category,
  }),
];
