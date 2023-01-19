import React, { useState } from 'react';
import {
  Button,
  TextField,
  FormControl,
  FormControlLabel,
  Checkbox,
  FormHelperText,
  Grid,
  Box,
  Card,
  Typography,
  Container,
} from '@mui/material/';
import styled from 'styled-components';

// mui의 css 우선순위가 높기때문에 important를 설정 - 실무하다 보면 종종 발생 우선순위 문제
const FormHelperTexts = styled(FormHelperText)`
  width: 100%;
  padding-left: 16px;
  font-weight: 700 !important;
  color: #d32f2f !important;
`;

const RegisterCard = () => {
  const [checked, setChecked] = useState(false);
  const [emailError, setEmailError] = useState('');
  const [passwordState, setPasswordState] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [nameError, setNameError] = useState('');
  const [birthError, setBirthError] = useState('');
  const [phoneError, setPhoneError] = useState('');


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
      email: data.get('email'),
      password: data.get('password'),
      rePassword: data.get('rePassword'),
      name: data.get('name'),
      birth: data.get('birth'),
      phone: data.get('phone'),
    };
    const {email, name, password, rePassword } = joinData;

    // 이메일 유효성 체크
    const emailRegex = /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if (!emailRegex.test(email)) setEmailError('올바른 이메일 형식이 아닙니다.');
    else setEmailError('');

    // 비밀번호 유효성 체크
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
    if (!passwordRegex.test(password))
      setPasswordState('숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요!');
    else setPasswordState('');

    // 비밀번호 같은지 체크
    if (password !== rePassword) setPasswordError('비밀번호가 일치하지 않습니다.');
    else setPasswordError('');

    // 이름 유효성 검사
    const nameRegex = /^[가-힣a-zA-Z]+$/;
    if (!nameRegex.test(name) || name.length < 1) setNameError('올바른 이름을 입력해주세요.');
    else setNameError('');

    //생년월일 유효성 검사
    const birthRegex = /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
    if (!birthRegex.test(name) || name.length < 1) setBirthError('생년월일 8자리를 입력해주세요.');
    else setNameError('');

    //전화번호 유효성 검사
    const phoneRegex = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/;
    if (!phoneRegex.test(name) || name.length < 1) setPhoneError('올바른 번호를 입력해주세요.');
    else setNameError('');
    // 회원가입 동의 체크
    if (!checked) alert('회원가입 약관에 동의해주세요.');

    if (
      emailRegex.test(email) &&
      passwordRegex.test(password) &&
      password === rePassword &&
      nameRegex.test(name) &&
      checked
    ) {
      onhandlePost(joinData);
    }
  };

  return (
      <Container component="main" maxWidth="xs">
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Typography component="h1" variant="h5">
            회원가입
          </Typography>
        <Card style={{padding: '40px'}}>
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <FormControl component="fieldset" variant="standard">
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <TextField
                    required
                    autoFocus
                    fullWidth
                    type="email"
                    id="email"
                    name="email"
                    label="이메일 주소"
                    error={emailError !== '' || false}
                  />
                </Grid>
                <FormHelperTexts>{emailError}</FormHelperTexts>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    type="password"
                    id="password"
                    name="password"
                    label="비밀번호"
                    error={passwordState !== '' || false}
                  />
                </Grid>
                <FormHelperTexts>{passwordState}</FormHelperTexts>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    type="password"
                    id="rePassword"
                    name="rePassword"
                    label="비밀번호 재입력"
                    error={passwordError !== '' || false}
                  />
                </Grid>
                <FormHelperTexts>{passwordError}</FormHelperTexts>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    id="name"
                    name="name"
                    label="이름"
                    error={nameError !== '' || false}
                  />
                </Grid>
                <FormHelperTexts>{nameError}</FormHelperTexts>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    id="birth"
                    name="birth"
                    label="생년월일"
                    error={birthError !== '' || false}
                  />
                </Grid>
                <FormHelperTexts>{birthError}</FormHelperTexts>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    id="phone"
                    name="phone"
                    label="전화번호"
                    error={phoneError !== '' || false}
                  />
                </Grid>
                <FormHelperTexts>{phoneError}</FormHelperTexts>
                <Grid item xs={12}>
                  <FormControlLabel
                    control={<Checkbox onChange={handleAgree} color="primary" />}
                    label="개인정보제공동의"
                  />
                </Grid>
              </Grid>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                size="large"
              >
                회원가입
              </Button>
            </FormControl>
            {/* <FormHelperTexts>{registerError}</FormHelperTexts> */}
          </Box>
          </Card>
        </Box>
    </Container>
  );
};

export default RegisterCard;