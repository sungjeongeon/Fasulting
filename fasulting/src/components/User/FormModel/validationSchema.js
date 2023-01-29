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
    phone: yup
      .string()
      .matches(/^[0-9]{2,3}[0-9]{3,4}[0-9]{4}/, "올바른 번호를 입력해주세요.")
      .required("전화번호를 입력해주세요."),
    agreement: yup
      .bool()
      .oneOf([true], "You need to accept the terms and conditions"),
  }),
];
