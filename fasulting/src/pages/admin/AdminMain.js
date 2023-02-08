import React from "react";
import AdminSignupAcceptList from "../../components/List/AdminSignupAcceptList";
import AdminReviewReport from "../../components/List/AdminReviewReport";
// import Typography from "@mui/material/Typography";
import styles from "./AdminMain.module.css";
import { useEffect } from "react";
import axiosAPi from "../../api/axiosApi";
import { useState } from "react";

function AdminMain() {
  const [signUpList, setSignUpList] = useState([]);
  const [reviewList, setReviewList] = useState([]);
  useEffect(() => {
    axiosAPi.get("/admin/account/ps").then((res) => {
      setSignUpList(res.data.responseObj);
    });
    axiosAPi.get("/admin/review").then((res) => {
      setReviewList(res.data.responseObj);
      console.log(res);
    });
  }, []);
  return (
    <>
      {/* <Typography variant="h4" gutterBottom sx={{ mt: 5, ml: 5 }}>
        💡 관리자 님, 안녕하세요.
      </Typography> */}

      <div className={styles.flex}>
        <AdminSignupAcceptList signUpList={signUpList} />
        <AdminReviewReport reviewList={reviewList} />
      </div>
    </>
  );
}

export default AdminMain;
