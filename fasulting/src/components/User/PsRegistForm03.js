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
  psdirector: yup,
  psregistration: yup,
  psregistrationimg: yup,
  doctor: yup,
  category: yup,
});

export default function PsRegistForm03() {
  const formik = useFormik({
    initialValues: {
      psdirector: "",
      psregistration: "",
      psregistrationimg: "",
      doctor: "",
      category: "",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      alert(JSON.stringify(values, null, 2));
    },
  });

  return (
    <form onSubmit={formik.handleSubmit}>
      <Box noValidate ml={5} mr={5}>
        <Typography marginTop={2}>병원장 이름</Typography>
        <TextField
          fullWidth
          id="psdirector"
          name="psdirector"
          label="병원장 이름을 검색해주세요."
          value={formik.values.psdirector}
          onChange={formik.handleChange}
          margin="normal"
          error={formik.touched.psdirector && Boolean(formik.errors.psdirector)}
          helperText={formik.touched.psdirector ? formik.errors.psdirector : ""}
        />
        <Typography marginTop={2}>병원 사업자 등록번호</Typography>
        <TextField
          fullWidth
          id="psregistration"
          name="psregistration"
          label="병원 사업자 등록번호."
          type="psregistration"
          value={formik.values.psregistration}
          onChange={formik.handleChange}
          margin="normal"
          error={
            formik.touched.psregistration &&
            Boolean(formik.errors.psregistration)
          }
          helperText={
            formik.touched.psregistration ? formik.errors.psregistration : ""
          }
        />
        <Typography marginTop={2}>사업자 등록증 등록</Typography>
        <TextField
          fullWidth
          id="psregistrationimg"
          name="psregistrationimg"
          label="사업자 등록증을 첨부해주세요."
          type="password"
          value={formik.values.psregistrationimg}
          onChange={formik.handleChange}
          margin="normal"
          error={
            formik.touched.psregistrationimg &&
            Boolean(formik.errors.psregistrationimg)
          }
          helperText={
            formik.touched.psregistrationimg
              ? formik.errors.psregistrationimg
              : ""
          }
        />
        <Typography marginTop={2}>가능한 수술 카테고리</Typography>
        <TextField
          fullWidth
          id="category"
          name="category"
          label="가능한 수술 카테고리를 모두 선택해주세요."
          type="text"
          value={formik.values.category}
          onChange={formik.handleChange}
          margin="normal"
          error={formik.touched.category && Boolean(formik.errors.category)}
          helperText={formik.touched.category ? formik.errors.category : ""}
        />
        <Typography marginTop={2}>병원 전문의 등록</Typography>
        <TextField
          fullWidth
          id="doctor"
          name="doctor"
          label="병원 전문의를 등록해주세요."
          type="password"
          value={formik.values.doctor}
          onChange={formik.handleChange}
          margin="normal"
          error={formik.touched.doctor && Boolean(formik.errors.doctor)}
          helperText={formik.touched.doctor ? formik.errors.doctor : ""}
        />
        <Link href="#" variant="body2">
          일반 회원으로 가입하시나요?
        </Link>
      </Box>
    </form>
  );
}
