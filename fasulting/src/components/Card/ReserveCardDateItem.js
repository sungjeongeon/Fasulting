import React, { useEffect, useState } from "react";
import styles from "./ReserveCardDateItem.module.css";

function ReserveCardDateItem({ getTimeTable }) {
  const [thisWeek, setThisWeek] = useState([]);
  // 오늘 ~ 7 일 data 생성 (렌더링 시 최초 1회만 실행) -> thisWeek state에 저장
  useEffect(() => {
    const getThisWeek = (d) => {
      const today = new Date();
      today.setDate(today.getDate() + d);
      const newDay = today;
      const year = newDay.getFullYear();
      const month = newDay.getMonth() + 1;
      const day = newDay.getDate();
      const week = ["일", "월", "화", "수", "목", "금", "토"];
      const dayOfWeek = week[today.getDay()];
      const newDayObj = {
        year,
        month,
        day,
        dayOfWeek,
      };
      return newDayObj;
    };

    const thisWeek = [
      getThisWeek(0),
      getThisWeek(1),
      getThisWeek(2),
      getThisWeek(3),
      getThisWeek(4),
      getThisWeek(5),
      getThisWeek(6),
    ];

    setThisWeek(thisWeek);
  }, []);

  // 날짜에 선택 (동그라미) 표시하기 위함
  const [selected, setSelected] = useState(-1);
  const selectDate = (date) => {
    // 선택한 날짜의 idx를 selected에 저장 -> 해당 idx만 선택됨 표시
    const idx = thisWeek.indexOf(date);
    setSelected(idx);
    // 부모 component로 선택한 date 정보 보내줌
    getTimeTable(date);
  };

  return (
    <div className={styles.outerDiv}>
      {thisWeek.map((date, idx) => (
        <div
          key={date.day}
          className={styles.box}
          onClick={() => selectDate(date)}
        >
          <p>{date.dayOfWeek}</p>
          <p className={idx === selected ? styles.selected : null}>
            {date.day}
          </p>
        </div>
      ))}
    </div>
  );
}

export default ReserveCardDateItem;
