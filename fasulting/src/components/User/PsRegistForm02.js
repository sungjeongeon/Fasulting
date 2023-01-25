import React, { useState } from "react";
import {
  Button,
  TextField,
  Typography,
  FormControl,
  FormHelperText,
  Grid,
} from "@mui/material/";
import styled from "styled-components";
import { Link } from "@material-ui/core";

// mui의 css 우선순위가 높기때문에 important를 설정 - 실무하다 보면 종종 발생 우선순위 문제
const FormHelperTexts = styled(FormHelperText)`
  width: 100%;
  padding-left: 16px;
  font-weight: 700 !important;
  color: #d32f2f !important;
`;

const RegisterCard = () => {
  const [checked, setChecked] = useState(false);
  const [emailError, setEmailError] = useState("");
  const [passwordState, setPasswordState] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const handleAgree = (event) => {
    setChecked(event.target.checked);
  };

  const onhandlePost = async (data) => {
    const { email, name, password } = data;
    // const postData = { email, name, password };

    // post
    //     await axios
    //       .post('/member/join', postData)
    //       .then(function (response) {
    //         console.log(response, '성공');
    //         history.push('/login');
    //       })
    //       .catch(function (err) {
    //         console.log(err);
    //         setRegisterError('회원가입에 실패하였습니다. 다시한번 확인해 주세요.');
    //       });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = new FormData(e.currentTarget);
    const joinData = {
      email: data.get("email"),
      password: data.get("password"),
      rePassword: data.get("rePassword"),
      name: data.get("name"),
      birth: data.get("birth"),
      phone: data.get("phone"),
    };
    const { email, password, rePassword } = joinData;

    // 이메일 유효성 체크
    const emailRegex =
      /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if (!emailRegex.test(email))
      setEmailError("올바른 이메일 형식이 아닙니다.");
    else setEmailError("");

    // 비밀번호 유효성 체크
    const passwordRegex =
      /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
    if (!passwordRegex.test(password))
      setPasswordState(
        "숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요!"
      );
    else setPasswordState("");

    // 비밀번호 같은지 체크
    if (password !== rePassword)
      setPasswordError("비밀번호가 일치하지 않습니다.");
    else setPasswordError("");

    if (
      emailRegex.test(email) &&
      passwordRegex.test(password) &&
      password === rePassword &&
      checked
    ) {
      onhandlePost(joinData);
    }
  };

  return (
    <>
      <Grid container spacing={2}>
        <Grid item xs={12}>
          <TextField
            required
            autoFocus
            fullWidth
            type="text"
            id="psname"
            name="psname"
            label="병원명"
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            fullWidth
            type="text"
            id="psintro"
            name="psintro"
            label="병원 소개"
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            fullWidth
            type="text"
            id="psaddress"
            name="psaddress"
            label="병원 주소"
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            fullWidth
            type="number"
            id="psnumber"
            name="psnumber"
            label="병원 전화번호"
          />
        </Grid>
      </Grid>
      <Link href="#" variant="body2">
        일반 회원으로 가입하시나요?
      </Link>
    </>
  );
};

export default RegisterCard;
