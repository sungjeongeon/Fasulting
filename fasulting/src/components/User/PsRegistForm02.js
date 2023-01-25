import React from "react";
import ReactDOM from "react-dom";
import { useFormik } from "formik";
import * as yup from "yup";
import {
  Container,
  Box,
  Typography,
  Card,
  FormControl,
  FormControlLabel,
  Radio,
  RadioGroup,
  TextField,
  Button,
  Link,
} from "@mui/material";
import { CssBaseline } from "@mui/material";

const validationSchema = yup.object({
  psname: yup,
  psprofile: yup,
  psintro: yup,
  psaddress: yup,
  psnumber: yup,
  pshomepage: yup,
});

export default function PsRegistForm02() {
  const formik = useFormik({
    initialValues: {
      psname: "",
      psprofile: "",
      psintro: "",
      psaddress: "",
      psnumber: "",
      pshomepage: "",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      alert(JSON.stringify(values, null, 2));
    },
  });

  return (
    <form onSubmit={formik.handleSubmit}>
      <Box noValidate ml={5} mr={5}>
        <Typography marginTop={2}>병원명</Typography>
        <TextField
          fullWidth
          id="psname"
          name="psname"
          label="병원명을 검색해주세요."
          value={formik.values.psname}
          onChange={formik.handleChange}
          margin="normal"
          error={formik.touched.psname && Boolean(formik.errors.psname)}
          helperText={formik.touched.psname ? formik.errors.psname : ""}
        />
        <Typography marginTop={2}>병원 프로필</Typography>
        <TextField
          fullWidth
          id="psprofile"
          name="psprofile"
          label="프로필 사진을 업로드해주세요."
          type="psprofile"
          value={formik.values.psprofile}
          onChange={formik.handleChange}
          margin="normal"
          error={formik.touched.psprofile && Boolean(formik.errors.psprofile)}
          helperText={formik.touched.psprofile ? formik.errors.psprofile : ""}
        />
        <Typography marginTop={2}>병원 소개</Typography>
        <TextField
          fullWidth
          id="psintro"
          name="psintro"
          label="병원을 한줄로 소개해주세요."
          type="password"
          value={formik.values.psintro}
          onChange={formik.handleChange}
          margin="normal"
          error={formik.touched.psintro && Boolean(formik.errors.psintro)}
          helperText={formik.touched.psintro ? formik.errors.psintro : ""}
        />
        <Typography marginTop={2}>병원 주소</Typography>
        <TextField
          fullWidth
          id="psaddress"
          name="psaddress"
          label="병원 주소를 입력해주세요."
          type="text"
          value={formik.values.psaddress}
          onChange={formik.handleChange}
          margin="normal"
          error={formik.touched.psaddress && Boolean(formik.errors.psaddress)}
          helperText={formik.touched.psaddress ? formik.errors.psaddress : ""}
        />
        <Typography marginTop={2}>병원 전화번호</Typography>
        <TextField
          fullWidth
          id="psnumber"
          name="psnumber"
          label="병원 전화번호"
          type="password"
          value={formik.values.psnumber}
          onChange={formik.handleChange}
          margin="normal"
          error={formik.touched.psnumber && Boolean(formik.errors.psnumber)}
          helperText={formik.touched.psnumber ? formik.errors.psnumber : ""}
        />
        <Typography marginTop={2}>병원 홈페이지 주소</Typography>
        <TextField
          fullWidth
          id="pshomepage"
          name="pshomepage"
          label="병원 홈페이지 주소"
          type="password"
          value={formik.values.pshomepage}
          onChange={formik.handleChange}
          margin="normal"
          error={formik.touched.pshomepage && Boolean(formik.errors.pshomepage)}
          helperText={formik.touched.pshomepage ? formik.errors.pshomepage : ""}
        />
        <Link href="#" variant="body2">
          일반 회원으로 가입하시나요?
        </Link>
      </Box>
    </form>
  );
}
