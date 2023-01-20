import React from "react";
import styles from "./HospitalInfo.module.css";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import LocalPhoneRoundedIcon from "@mui/icons-material/LocalPhoneRounded";
import MailOutlineRoundedIcon from "@mui/icons-material/MailOutlineRounded";
import LinkIcon from "@mui/icons-material/Link";
import AccessTimeIcon from "@mui/icons-material/AccessTime";

function HospitalInfo() {
  const hospital = {
    ps_id: 1,
    ps_address: "부산 강서구 녹산산단 335로 7, 송삼빌딩 1층",
    ps_number: "02-547-0050",
    ps_email: "id@idhospital.com",
    ps_homepage: "https://www.idhospital.com/",
    // 운영시간 데이터 형태 아직 ....
    ps_time: "임시",
    total_rating_result: 3.0,
    total_rating_count: 30,
  };
  return (
    <div>
      <p className={styles.title}>병원 정보</p>
      <div className={styles.iconTextDiv}>
        <LocationOnIcon color="primary" sx={{ fontSize: 28 }} />
        <p>{hospital.ps_address}</p>
      </div>
      <div className={styles.iconTextDiv}>
        <LocalPhoneRoundedIcon color="primary" sx={{ fontSize: 28 }} />
        <p>{hospital.ps_number}</p>
      </div>
      <div className={styles.iconTextDiv}>
        <MailOutlineRoundedIcon color="primary" sx={{ fontSize: 28 }} />
        <p>{hospital.ps_email}</p>
      </div>
      <div className={styles.iconTextDiv}>
        <LinkIcon color="primary" sx={{ fontSize: 28 }} />
        <p>{hospital.ps_homepage}</p>
      </div>
      <div className={styles.iconTextDiv}>
        <AccessTimeIcon color="primary" sx={{ fontSize: 28 }} />
        <p>운영 시간</p>
      </div>
      <p>{hospital.ps_time}</p>
    </div>
  );
}

export default HospitalInfo;
