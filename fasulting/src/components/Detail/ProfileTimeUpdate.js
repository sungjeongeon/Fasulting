import React, { useState } from "react";
import styles from "./ProfileTimeUpdate.module.css";
import Button from "@mui/material/Button";
import SetOperatingTime from "../Modal/SetOperatingTime";

function ProfileTimeUpdate() {
  const [modal, setModal] = useState(false);

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
  // 임시 병원 운영시간 데이터
  const weekSchedule = [
    { id: 1, timetable: [] },
    {
      id: 2,
      timetable: [1, 2, 3, 4, 5, 6, 10, 11, 12, 13, 14, 15],
    },
    { id: 3, timetable: [2, 3] },
    {
      id: 4,
      timetable: [1, 2, 3, 4, 5, 6, 10, 11, 12, 13, 14, 15],
    },
    {
      id: 5,
      timetable: [1, 2, 3, 4, 5, 6, 10, 11, 12, 13, 14, 15],
    },
    {
      id: 6,
      timetable: [4, 5, 6, 7, 8, 9, 10, 11, 12, 13],
    },
    { id: 7, timetable: [] },
  ];
  const onClick = () => {
    setModal((current) => !current);
  };

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
            <p className={styles.day}>{week[day.id]}</p>
            {getTimeString(day.timetable)}
          </div>
        );
      })}
      <hr className={styles.hr} />
    </div>
  );
}

export default ProfileTimeUpdate;
