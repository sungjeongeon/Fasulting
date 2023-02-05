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

const validationSchema = [
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
    [psname.name]: yup.string().required("병원명을 입력해주세요."),
    [psprofile.name]: yup.string(),
    [psintro.name]: yup.string(),
    [psaddress.name]: yup.string(),
    [psnumber.name]: yup
      .string()
      .matches(
        /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]))-(\d{3,4})-(\d{4})$/,
        "전화번호 형식에 맞게 입력해주세요."
      )
      .required("병원 전화번호를 입력해주세요."),
    [pshomepage.name]: yup
      .string()
      .matches(
        /(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/,
        "올바른 주소를 입력해주세요."
      )
      .required("병원 홈페이지 주소를 입력해주세요."),
  }),
  yup.object().shape({
    [psdirector.name]: yup
      .string()
      .matches(/^[가-힣a-zA-Z]+$/, "올바른 이름을 입력해주세요.")
      .required("병원장 이름을 입력해주세요."),
    [psregistration.name]: yup
      .string()
      .matches(
        /^[0-9]{3}-[0-9]{2}-[0-9]{5}$/,
        "000-00-00000 형식에 맞게 입력해주세요."
      )
      .required("사업자 등록번호를 입력해주세요."),
    [psregistrationimg.name]: yup
      .mixed()
      .required("사업자 등록증을 업로드해주세요."),
    // doctor,
    // category,
  }),
];

export default validationSchema;
