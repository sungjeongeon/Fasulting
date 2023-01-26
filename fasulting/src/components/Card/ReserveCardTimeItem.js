import React, { useEffect, useState } from "react";
import styles from "./ReserveCardTimeItem.module.css";

function ReserveCardTimeItem({ times, getReserveTime }) {
  const [timeTable, setTimeTable] = useState([
    {
      id: 0,
      time: "9:00",
      isPossible: false,
    },
    {
      id: 1,
      time: "9:30",
      isPossible: false,
    },
    {
      id: 2,
      time: "10:00",
      isPossible: false,
    },
    {
      id: 3,
      time: "10:30",
      isPossible: false,
    },
    {
      id: 4,
      time: "11:00",
      isPossible: false,
    },
    {
      id: 5,
      time: "11:30",
      isPossible: false,
    },
    {
      id: 6,
      time: "12:00",
      isPossible: false,
    },
    {
      id: 7,
      time: "12:30",
      isPossible: false,
    },
    {
      id: 8,
      time: "13:00",
      isPossible: false,
    },
    {
      id: 9,
      time: "13:30",
      isPossible: false,
    },
    {
      id: 10,
      time: "14:00",
      isPossible: false,
    },
    {
      id: 11,
      time: "14:30",
      isPossible: false,
    },
    {
      id: 12,
      time: "15:00",
      isPossible: false,
    },
    {
      id: 13,
      time: "15:30",
      isPossible: false,
    },
    {
      id: 14,
      time: "16:00",
      isPossible: false,
    },
    {
      id: 15,
      time: "16:30",
      isPossible: false,
    },
    {
      id: 16,
      time: "17:00",
      isPossible: false,
    },
    {
      id: 17,
      time: "17:30",
      isPossible: false,
    },
    {
      id: 18,
      time: "18:00",
      isPossible: false,
    },
    {
      id: 19,
      time: "18:30",
      isPossible: false,
    },
    {
      id: 20,
      time: "19:00",
      isPossible: false,
    },
    {
      id: 21,
      time: "19:30",
      isPossible: false,
    },
  ]);
  const [selectedIdx, setSelectedIdx] = useState("");
  // props (times) 가 변경되면 timeTable의 선택 가능/불가능 여부 리셋
  useEffect(() => {
    const tempo = timeTable.map((t) => {
      return { id: t.id, time: t.time, isPossible: false };
    });
    for (const t of times) {
      tempo[t].isPossible = true;
    }
    setTimeTable(tempo);
    // props 바뀌면 선택됐던 시간 idx 리셋
    setSelectedIdx("");
  }, [times]);

  return (
    <div>
      <p>오전</p>
      {timeTable.slice(0, 6).map((t) => {
        return (
          <button
            key={t.id}
            onClick={() => {
              setSelectedIdx(t.id);
              getReserveTime(t.id);
            }}
            className={
              t.isPossible
                ? selectedIdx === t.id
                  ? styles.isSelected
                  : styles.isPossible
                : styles.isImpossible
            }
          >
            {t.time}
          </button>
        );
      })}
      <p>오후</p>
      {timeTable.slice(6, 22).map((t) => {
        return (
          <button
            key={t.id}
            onClick={() => {
              setSelectedIdx(t.id);
              getReserveTime(t.id);
            }}
            className={
              t.isPossible
                ? selectedIdx === t.id
                  ? styles.isSelected
                  : styles.isPossible
                : styles.isImpossible
            }
          >
            {t.time}
          </button>
        );
      })}
    </div>
  );
}

export default ReserveCardTimeItem;
