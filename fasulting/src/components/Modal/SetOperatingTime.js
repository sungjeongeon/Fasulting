import React from "react";
import styles from "./SetOperatingTime.module.css";
import SetOperatingTimeItem from "./SetOperatingTimeItem";

function SetOperatingTime({ ModalStateChange }) {
  const week = [
    {
      id: 0,
      name: "일",
    },
    {
      id: 1,
      name: "월",
    },
    {
      id: 2,
      name: "화",
    },
    {
      id: 3,
      name: "수",
    },
    {
      id: 4,
      name: "목",
    },
    {
      id: 5,
      name: "금",
    },
    {
      id: 6,
      name: "토",
    },
  ];
  return (
    <div className={styles.background}>
      <div className={styles.modalbox}>
        <p>운영시간 등록</p>
        {week.map((day) => (
          <SetOperatingTimeItem key={day.id} day={day} />
        ))}
        <button className={styles.register} onClick={ModalStateChange}>
          등록
        </button>
      </div>
    </div>
  );
}

export default SetOperatingTime;
