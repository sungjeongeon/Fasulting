import React from "react";
import styles from "./HospitalInfo.module.css";
import LocalPhoneRoundedIcon from "@mui/icons-material/LocalPhoneRounded";
import MailOutlineRoundedIcon from "@mui/icons-material/MailOutlineRounded";
import PersonIcon from '@mui/icons-material/Person';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';

function ClientInfo() {
  const client = {
    user_id: 1,
    user_name: "김싸피",
    user_email: 'ssafy@naver.com',
    user_number: "010-1111-2222",
    user_birth: '1990-01-01'
  };
  return (
    <div style={{width: '35%'}}>
      <p className={styles.title}>회원 정보</p>
      <div className={styles.bodyMargin}>
        <div className={styles.iconTextDiv}>
          <PersonIcon color="primary" sx={{ fontSize: 28 }} />
          <p>{client.user_name}</p>
        </div>
        <div className={styles.iconTextDiv}>
          <LocalPhoneRoundedIcon color="primary" sx={{ fontSize: 28 }} />
          <p>{client.user_number}</p>
        </div>
        <div className={styles.iconTextDiv}>
          <CalendarMonthIcon color="primary" sx={{ fontSize: 28 }} />
          <p>{client.user_birth.split('-').join('.')}</p>
        </div>
        <div className={styles.iconTextDiv}>
          <MailOutlineRoundedIcon color="primary" sx={{ fontSize: 28 }} />
          <p>{client.user_email}</p>
        </div>
      </div>
    </div>
  );
}

export default ClientInfo;
