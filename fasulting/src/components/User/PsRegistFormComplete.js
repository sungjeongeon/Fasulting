import { Typography } from "@mui/material";
import React from "react";
import { Link } from "react-router-dom";

const PsRegistFormComplete = () => {
  return (
    <div>
      <Typography component="h2" variant="h4">
        회원가입이 완료되었습니다 😀
      </Typography>
      <Link to="/login">로그인하기</Link>
    </div>
  );
};

export default PsRegistFormComplete;
