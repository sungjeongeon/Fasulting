import React from "react";
import { useFormik } from "formik";
import * as yup from "yup";
import "react-toastify/dist/ReactToastify.css";
import { toast, ToastContainer } from "react-toastify";
import { Link } from "react-router-dom";
import {
  Typography,
  FormControl,
  FormControlLabel,
  Radio,
  RadioGroup,
  TextField,
  Button,
} from "@mui/material";
import styles from "./Form.module.css";
import Paper from "@mui/material/Paper";
import axios from "../../api/axios";
import { setRefreshToken } from "../../storage/Cookie";
import { Navigate, useNavigate } from "react-router-dom";

const validationSchema = yup.object({
  email: yup
    .string()
    .email("올바른 이메일 형식을 입력해주세요.")
    .required("이메일을 입력해주세요."),
  password: yup.string().required("패스워드를 입력해주세요."),
});

export default function LoginForm() {
  const navigate = useNavigate();

  const formik = useFormik({
    initialValues: {
      usertype: "",
      email: "",
      password: "",
    },
    validationSchema: validationSchema,
    onSubmit: async (values) => {
      try {
        console.log(values);
        await axios.post("/user/login", values);
        toast.success(<h3>반갑습니다 !</h3>, {
          position: toast.POSITION.TOP_CENTER,
          autoClose: 2000,
        });
        setTimeout(() => {
          navigate("/login");
        }, 2000);
      } catch (e) {
        // 서버에서 받은 에러 메시지 출력
        console.log(e.response.data.message);
      }
      // console.log(
      //   JSON.stringify(
      //     values,
      //     ["email", "password", "repassword", "name", "birth", "phone"],
      //     2
      //   )
      // );
    },

    //   // alert(JSON.stringify(values, null, 2));
    //   // if (
    //   //   values.email === "ssafy@naver.com" &&
    //   //   values.password === "ssafy1234!"
    //   // ) {
    //   //   console.log("로그인 성공");
    //   //   return navigate("/");
    //   // }
  });

  return (
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
            <FormControl>
              <RadioGroup
                row
                aria-labelledby="demo-row-radio-buttons-group-label"
                name="row-radio-buttons-group"
              >
                <FormControlLabel
                  value="user"
                  control={<Radio />}
                  label="일반 회원"
                  name="usertype"
                />
                <FormControlLabel
                  value="ps"
                  control={<Radio />}
                  label="병원 회원"
                />
              </RadioGroup>
            </FormControl>
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
  );
}
