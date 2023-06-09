import React from "react";
import styles from "./HospitalInfo.module.css";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import LocalPhoneRoundedIcon from "@mui/icons-material/LocalPhoneRounded";
import MailOutlineRoundedIcon from "@mui/icons-material/MailOutlineRounded";
import LinkIcon from "@mui/icons-material/Link";
import AccessTimeIcon from "@mui/icons-material/AccessTime";
import { useEffect } from "react";

function HospitalInfo({ detailhospital }) {
  console.log("test", detailhospital.defaultTime);

  const week = ["", "일", "월", "화", "수", "목", "금", "토"];
  const timeTable = [
    "9:00",
    "9:30",
    "10:00",
    "10:30",
    "11:00",
    "11:30",
    "12:00",
    "12:30",
    "13:00",
    "13:30",
    "14:00",
    "14:30",
    "15:00",
    "15:30",
    "16:00",
    "16:30",
    "17:00",
    "17:30",
    "18:00",
    "18:30",
    "19:00",
    "19:30",
    "20:00",
  ];
  const getTimeString = (table) => {
    const len = table.length;
    if (len === 0) {
      // 휴무일
      return <p className={styles.redText}>휴진</p>;
    } else if (table[len - 1] - table[0] === len - 1) {
      // 휴게시간 없음
      return (
        <p>
          {timeTable[table[0]]} - {timeTable[table[len - 1] + 1]}
        </p>
      );
    } else {
      // 휴게시간 있음
      // 점심시간 계산
      const lunch = [];
      for (let i = table[0]; i <= table[len - 1]; i++) {
        if (table.includes(i) === false) {
          lunch.push(i);
        }
      }
      return (
        <p>
          {timeTable[table[0]]} - {timeTable[table[len - 1] + 1]} / 점심시간{" "}
          {timeTable[lunch[0]]} - {timeTable[lunch[lunch.length - 1] + 1]}
        </p>
      );
    }
  };
  return (
    <div>
      <p className={styles.title}>병원 정보</p>
      <div className={styles.bodyMargin}>
        <div className={styles.iconTextDiv}>
          <LocationOnIcon color="primary" sx={{ fontSize: 28 }} />
          <p>
            {detailhospital.psAddress
              ? detailhospital.psAddress
              : "주소 정보가 존재하지 않습니다."}
          </p>
        </div>
        <div className={styles.iconTextDiv}>
          <LocalPhoneRoundedIcon color="primary" sx={{ fontSize: 28 }} />
          <p>
            {detailhospital.psNumber
              ? detailhospital.psNumber
              : "전화번호 정보가 존재하지 않습니다."}
          </p>
        </div>
        <div className={styles.iconTextDiv}>
          <MailOutlineRoundedIcon color="primary" sx={{ fontSize: 28 }} />
          <p>
            {detailhospital.psEmail
              ? detailhospital.psEmail
              : "이메일 정보가 존재하지 않습니다."}
          </p>
        </div>
        <div className={styles.iconTextDiv}>
          <LinkIcon color="primary" sx={{ fontSize: 28 }} />
          <p>
            {detailhospital.psHomepage
              ? detailhospital.psHomepage
              : "홈페이지 주소가 존재하지 않습니다."}
          </p>
        </div>
        <div className={styles.iconTextDiv}>
          <AccessTimeIcon color="primary" sx={{ fontSize: 28 }} />
          <p>운영 시간</p>
        </div>
        <p>
          {detailhospital.defaultTime &&
            Object.values(detailhospital.defaultTime).map((time, index) => {
              return (
                <div className={styles.dayDiv} key={index}>
                  <p className={styles.day}>{week[index + 1]}</p>
                  {getTimeString(time)}
                </div>
              );
            })}
        </p>
      </div>
    </div>
  );
}

export default HospitalInfo;
