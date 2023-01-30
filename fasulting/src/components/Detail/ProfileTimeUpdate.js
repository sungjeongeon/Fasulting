import React, { useState } from "react";
import styles from "./ProfileTimeUpdate.module.css";
import Button from "@mui/material/Button";
import SetOperatingTime from "../Modal/SetOperatingTime";

function ProfileTimeUpdate() {
  const [modal, setModal] = useState(false);

  const week = ["일", "월", "화", "수", "목", "금", "토"];
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
  // 임시 병원 운영시간 데이터
  const weekSchedule = [
    { id: 0, timetable: [] },
    {
      id: 1,
      timetable: [3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15, 16, 17, 18, 19],
    },
    { id: 2, timetable: [3, 4, 5, 6, 7, 8, 9, 10] },
    {
      id: 3,
      timetable: [3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15, 16, 17, 18, 19],
    },
    {
      id: 4,
      timetable: [3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15, 16, 17, 18, 19],
    },
    {
      id: 5,
      timetable: [3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15, 16, 17, 18, 19],
    },
    { id: 6, timetable: [] },
  ];
  const onClick = (e) => {
    setModal((current) => !current);
  };
  return (
    <div>
      <div className={styles.titleDiv}>
        <p className={styles.title}>운영 시간</p>
        <Button variant="text" className={styles.btn} onClick={onClick}>
          <p className={styles.btnTextGreen}>수정</p>
        </Button>
        {modal && <SetOperatingTime ModalStateChange={onClick} />}
      </div>
      {weekSchedule.map((day) => {
        return (
          <div className={styles.dayDiv} key={day.id}>
            <p>{week[day.id]}</p>
            {day.timetable.length === 0 ? <p>휴무</p> : null}
          </div>
        );
      })}
      <hr className={styles.hr} />
    </div>
  );
}

export default ProfileTimeUpdate;
