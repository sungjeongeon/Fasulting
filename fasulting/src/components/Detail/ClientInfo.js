import React from "react";
import styles from "./HospitalInfo.module.css";
import LocalPhoneRoundedIcon from "@mui/icons-material/LocalPhoneRounded";
import PersonIcon from '@mui/icons-material/Person';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';

function ClientInfo({client}) {
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
            <p>{client.user_birth}</p>
          </div>
        </div>
      </div>    
  );
}

export default ClientInfo;
