import React from "react";
import { Field, useFormik } from "formik";
import * as yup from "yup";
import "react-toastify/dist/ReactToastify.css";
import { toast, ToastContainer } from "react-toastify";
import { Link } from "react-router-dom";
import { Typography, TextField, Button } from "@mui/material";
import styles from "./Form.module.css";
import Paper from "@mui/material/Paper";
import { Navigate, useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { loginUser } from "../../redux/user";
import { changeLoading, setToken } from "../../redux/auth";
import axiosAPi from "../../api/axiosApi";
import { useEffect } from "react";
import { useState } from "react";
import setAuthorizationToken from "../../api/setAuthorizationToken";
import { loginPs, loginps } from "../../redux/ps";

const validationSchema = yup.object({
  email: yup
    .string()
    .email("올바른 이메일 형식을 입력해주세요.")
    .required("이메일을 입력해주세요."),
  password: yup.string().required("패스워드를 입력해주세요."),
});

export default function LoginForm() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [usertype, setUsertype] = useState("");

  const handleChange = (e) => {
    console.log(`선택한 값 : ${e.target.value}`);
    setUsertype(e.target.value);
  };
  const formik = useFormik(
    {
      initialValues: {
        email: "",
        password: "",
      },
      validationSchema: validationSchema,

      onSubmit: async (values) => {
        if (usertype === "") {
          toast.error(<h3>회원유형을 선택해주세요 !</h3>, {
            position: toast.POSITION.TOP_CENTER,
            autoClose: 1000,
            hideProgressBar: true,
          });
        } else if (usertype === "user") {
          try {
            await axiosAPi
              .post("/user/login", values, {
                withCredentials: true,
              })
              .then((res) => {
                if (res.data.statusCode === 200) {
                  //console.log(res.data);
                  dispatch(
                    loginUser({
                      userSeq: res.data.responseObj.userSeq,
                      userName: res.data.responseObj.userName,
                      adminYn: res.data.responseObj.adminYn,
                      userEmail: values.email,
                      userPwd: values.password,
                    })
                  );
                  //토큰 받아오기
                  const accessToken = res.headers.get("authorization");
                  //console.log(accessToken);
                  dispatch(setToken({ accessToken: accessToken }));
                  // API 요청하는 콜마다 헤더에 accessToken 담아 보내도록 설정
                  // axiosAPi.defaults.headers.common[
                  //   "Authorization"
                  // ] = `Bearer ${accessToken}`;
                  //setAuthorizationToken(accessToken);
                  //console.log(res.data);
                  toast.success(
                    <h3>
                      로그인 완료 <br />
                      반갑습니다 !{" "}
                    </h3>,
                    {
                      position: toast.POSITION.TOP_CENTER,
                      autoClose: 2000,
                    }
                  );
                  setTimeout(() => {
                    if (res.data.responseObj.adminYn === true) {
                      navigate("/admin");
                    } else {
                      navigate("/");
                    }
                    dispatch(changeLoading())
                  }, 2000);
                } else if (res.status === 204) {
                  toast.error(
                    <h3>
                      아이디나 비밀번호를 <br />
                      다시 확인해주세요.😢
                    </h3>,
                    {
                      position: toast.POSITION.TOP_CENTER,
                      autoClose: 2000,
                    }
                  );
                }
              });
          } catch {}
        } else if (usertype === "ps") {
          try {
            await axiosAPi
              .post("/ps/login", values, {
                withCredentials: true,
              })
              .then((res) => {
                if (res.data.message === "success") {
                  console.log(res);
                  if (res.data.responseObj.confirmYn === true) {
                    console.log(res.data);
                    dispatch(
                      loginPs({
                        psSeq: res.data.responseObj.psSeq,
                        psName: res.data.responseObj.psName,
                        psEmail: values.email,
                        psPwd: values.password,
                      })
                    );
                    //토큰 받아오기
                    const accessToken = res.headers.get("Authorization");
                    //console.log(accessToken);
                    dispatch(setToken({ accessToken: accessToken }));
                    // API 요청하는 콜마다 헤더에 accessToken 담아 보내도록 설정
                    // axiosAPi.defaults.headers.common[
                    //   "Authorization"
                    // ] = `Bearer ${accessToken}`;
                    //setAuthorizationToken(accessToken);
                    //console.log(res.data);
                    toast.success(
                      <h3>
                        로그인 완료 <br />
                        반갑습니다 !{" "}
                      </h3>,
                      {
                        position: toast.POSITION.TOP_CENTER,
                        autoClose: 2000,
                      }
                    );
                    setTimeout(() => {
                      navigate("/mypageho");
                      dispatch(changeLoading())
                    }, 2000);
                  } else {
                    toast.error(
                      <h3>
                        승인 대기중인 계정입니다. <br />
                        잠시후 다시 로그인해주세요!{" "}
                      </h3>,
                      {
                        position: toast.POSITION.TOP_CENTER,
                        autoClose: 2000,
                      }
                    );
                    setTimeout(() => {
                      navigate("/");
                    }, 2000);
                  }
                } else if (res.data.message === "fail") {
                  toast.success(
                    <h3>
                      유저정보가 존재하지 않습니다. <br />
                    </h3>,
                    {
                      position: toast.POSITION.TOP_CENTER,
                      autoClose: 2000,
                    }
                  );
                }
              });
          } catch (e) {
            console.log(e);
          }
        }
      },
      // console.log(
      //   JSON.stringify(
      //     values,
      //     ["email", "password", "repassword", "name", "birth", "phone"],
      //     2
      //   )
      // );
    }

    //   // alert(JSON.stringify(values, null, 2));
    //   // if (
    //   //   values.email === "ssafy@naver.com" &&
    //   //   values.password === "ssafy1234!"
    //   // ) {
    //   //   console.log("로그인 성공");
    //   //   return navigate("/");
    //   // }
  );

  return (
    <>
      <ToastContainer />
      <div className={styles.formwrapper}>
        <Paper
          variant="outlined"
          sx={{ my: { xs: 3, md: 6 }, p: { xs: 2, md: 3 } }}
        >
          <Typography component="h1" variant="h4" align="center">
            로그인
          </Typography>
          <form onSubmit={formik.handleSubmit}>
            <div className={styles.inputForm}>
              {/* <FormControl>
              <RadioGroup
                row
                aria-labelledby="demo-row-radio-buttons-group-label"
                name="row-radio-buttons-group"
              >
                <FormControlLabel
                  control={<Radio />}
                  label="일반 회원"
                  name="usertype"
                />
                <FormControlLabel
                  control={<Radio />}
                  label="병원 회원"
                  name="usertype"
                />
              </RadioGroup>
            </FormControl> */}
              <div className={styles.radiogroup}>
                <div className={styles.radio}>
                  <input
                    id="user"
                    value="user"
                    name="platform"
                    type="radio"
                    //checked={this.state.selectValue === "user"}
                    onChange={handleChange}
                  />
                  <label htmlFor="user">일반회원</label>
                </div>
                <div className={styles.radio}>
                  <input
                    id="ps"
                    value="ps"
                    name="platform"
                    type="radio"
                    //checked={this.state.selectValue === "ps"}
                    onChange={handleChange}
                  />
                  <label htmlFor="ps">병원회원</label>
                </div>
              </div>
              <div className={styles.inputItem}>
                <div className={styles.label}>이메일</div>
                <TextField
                  fullWidth
                  name="email"
                  placeholder="아이디(이메일)을 입력해주세요."
                  value={formik.values.email}
                  onChange={formik.handleChange}
                  error={formik.touched.email && Boolean(formik.errors.email)}
                  helperText={formik.touched.email ? formik.errors.email : ""}
                />
              </div>
              <div className={styles.inputItem}>
                <div className={styles.flexBetween}>
                  <div className={styles.label}>비밀번호</div>
                  <Link
                    to={"/findpw"}
                    style={{ textDecoration: "none", color: "#72a1a6" }}
                    variant="body2"
                    className={styles.label}
                  >
                    비밀번호를 잊으셨나요?
                  </Link>
                </div>
                <TextField
                  fullWidth
                  name="password"
                  placeholder="비밀번호를 입력해주세요"
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
              <Button
                color="primary"
                variant="contained"
                fullWidth
                type="submit"
                sx={{ mt: 3, mb: 2 }}
              >
                로그인
              </Button>
              <div className={styles.flexCenter}>
                <div>아직 아이디가 없으신가요?</div>
                <div>
                  <Link
                    to={"/register"}
                    style={{ textDecoration: "none", color: "#72a1a6" }}
                  >
                    일반 회원가입 |{" "}
                  </Link>
                  <Link
                    to={"/psregist"}
                    style={{ textDecoration: "none", color: "#72a1a6" }}
                  >
                    {" "}
                    의사 회원가입
                  </Link>
                </div>
              </div>
            </div>
          </form>
        </Paper>
      </div>
    </>
  );
}
