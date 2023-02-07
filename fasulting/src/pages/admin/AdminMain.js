import React from "react";
import AdminSignupAcceptList from "../../components/List/AdminSignupAcceptList";
import AdminReviewReport from "../../components/List/AdminReviewReport";
import styles from "./AdminMain.module.css";

function AdminMain() {
  return (
    <div className={styles.flex}>
      <AdminSignupAcceptList />
      <AdminReviewReport />
    </div>
  );
}

export default AdminMain;
