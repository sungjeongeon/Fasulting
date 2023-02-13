import React from "react";
import styles from "./SetOperatingTime.module.css";
import SetOperatingTimeItem from "./SetOperatingTimeItem";
import Button from "@mui/material/Button";
import { useState } from "react";
import axiosAPi from "../../api/axiosApi";
import CloseIcon from "@mui/icons-material/Close";

function SetOperatingTime({ ModalStateChange, psSeq }) {
  const week = [
    {
      id: 1,
      name: "일",
    },
    {
      id: 2,
      name: "월",
    },
    {
      id: 3,
      name: "화",
    },
    {
      id: 4,
      name: "수",
    },
    {
      id: 5,
      name: "목",
    },
    {
      id: 6,
      name: "금",
    },
    {
      id: 7,
      name: "토",
    },
  ];
  const [weekSchedule, setWeekSchedule] = useState({});
  const getSchedule = (s) => {
    setWeekSchedule((currentObj) => {
      return { ...currentObj, ...s };
    });
  };
  const saveSchedule = () => {
    axiosAPi
      .put("/ps/operating", {
        psSeq: psSeq,
        defaultTime: weekSchedule,
      })
      .then((res) => console.log(res));
    console.log(weekSchedule);
  };
  return (
    <div className={styles.background}>
      <div className={styles.modalbox}>
        <p className={styles.title}>운영시간 등록</p>
        <CloseIcon
          fontSize="large"
          onClick={ModalStateChange}
          color="error"
          className={styles.back}
        />
        <div className={styles.selectDiv}>
          {week.map((day) => (
            <SetOperatingTimeItem
              key={day.id}
              day={day}
              getSchedule={getSchedule}
            />
          ))}
        </div>
        <div className={styles.btnDiv}>
          <Button
            variant="contained"
            className={styles.register}
            onClick={() => {
              ModalStateChange();
              saveSchedule();
            }}
          >
            저 장
          </Button>
        </div>
      </div>
    </div>
  );
}

export default SetOperatingTime;
