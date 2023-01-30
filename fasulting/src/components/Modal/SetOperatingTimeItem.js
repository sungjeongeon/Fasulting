import React, { useState } from "react";
import styles from "./SetOperatingTimeItem.module.css";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import NativeSelect from "@mui/material/NativeSelect";
import Select from "@mui/material/Select";
import { useEffect } from "react";

function SetOperatingTimeItem({ day, getSchedule }) {
  const timeTable = [
    {
      id: 0,
      time: "9:00",
    },
    {
      id: 1,
      time: "9:30",
    },
    {
      id: 2,
      time: "10:00",
    },
    {
      id: 3,
      time: "10:30",
    },
    {
      id: 4,
      time: "11:00",
    },
    {
      id: 5,
      time: "11:30",
    },
    {
      id: 6,
      time: "12:00",
    },
    {
      id: 7,
      time: "12:30",
    },
    {
      id: 8,
      time: "13:00",
    },
    {
      id: 9,
      time: "13:30",
    },
    {
      id: 10,
      time: "14:00",
    },
    {
      id: 11,
      time: "14:30",
    },
    {
      id: 12,
      time: "15:00",
    },
    {
      id: 13,
      time: "15:30",
    },
    {
      id: 14,
      time: "16:00",
    },
    {
      id: 15,
      time: "16:30",
    },
    {
      id: 16,
      time: "17:00",
    },
    {
      id: 17,
      time: "17:30",
    },
    {
      id: 18,
      time: "18:00",
    },
    {
      id: 19,
      time: "18:30",
    },
    {
      id: 20,
      time: "19:00",
    },
    {
      id: 21,
      time: "19:30",
    },
  ];
  const [rest, setRest] = useState("휴게있음");
  const [time1, setTime1] = useState(-1);
  const [time2, setTime2] = useState(-1);
  const [time3, setTime3] = useState(-1);
  const [time4, setTime4] = useState(-1);

  const handleChange = (e) => {
    setRest(e.target.value);
    setTime1(-1);
    setTime2(-1);
    setTime3(-1);
    setTime4(-1);
  };
  const time1Change = (e) => {
    setTime1(e.target.value);
    setTime2(Number(e.target.value) + 1);
    setTime3(-1);
    setTime4(-1);
  };
  const time2Change = (e) => {
    setTime2(e.target.value);
    setTime3(Number(e.target.value) + 1);
    setTime4(-1);
  };
  const time3Change = (e) => {
    setTime3(e.target.value);
    setTime4(Number(e.target.value) + 1);
  };
  const time4Change = (e) => {
    setTime4(e.target.value);
  };

  useEffect(() => {
    function range(ts, te) {
      return [...Array(te - ts).keys()].map((key) => key + ts);
    }
    const t1 = Number(time1);
    const t2 = Number(time2);
    const t3 = Number(time3);
    const t4 = Number(time4);
    const dayId = day.id.toString();
    const schedule = {};
    // schedule[dayId] = [2, 3, 4];
    // 휴무일 선택하면
    if (rest === "휴무일") {
      schedule[dayId] = [];
    }
    if (t1 < t2) {
      schedule[dayId] = range(t1, t2);
    }
    if (time3 < time4) {
      schedule[dayId] = [...range(t1, t2), ...range(t3, t4)];
    }
    // console.log(schedule);
    getSchedule(schedule);
  }, [rest, time1, time2, time3, time4]);
  return (
    <div className={styles.outerDiv}>
      <p className={styles.day}>{day.name}</p>
      {/* 휴게없음 / 휴게있음 / 휴무일 선택 */}
      <FormControl size="small" className={styles.rest}>
        <Select
          labelId="demo-select-small"
          id="demo-select-small"
          value={rest}
          onChange={handleChange}
        >
          <MenuItem value={"휴게있음"}>휴게있음</MenuItem>
          <MenuItem value={"휴게없음"}>휴게없음</MenuItem>
          <MenuItem value={"휴무일"}>휴무일</MenuItem>
        </Select>
      </FormControl>
      <p className={styles.text}></p>
      {/* time1 */}
      <FormControl disabled={rest === "휴무일" ? true : false}>
        <NativeSelect value={time1} onChange={time1Change}>
          <option hidden value={-1}>
            __:__
          </option>
          {timeTable.map((time) => {
            return (
              <option key={time.id} value={time.id}>
                {time.time}
              </option>
            );
          })}
        </NativeSelect>
      </FormControl>
      <p className={styles.text}> ~ </p>
      {/* time2 */}
      <FormControl disabled={rest === "휴무일" ? true : false}>
        <NativeSelect value={time2} onChange={time2Change}>
          <option hidden value={-1}>
            __:__
          </option>
          {timeTable.map((time) => {
            if (time.id > time1) {
              return (
                <option key={time.id} value={time.id}>
                  {time.time}
                </option>
              );
            } else {
              return null;
            }
          })}
          <option value={22}>20:00</option>
        </NativeSelect>
      </FormControl>

      {/* time3 */}
      {rest === "휴게있음" ? (
        <div className={styles.isRest}>
          <p className={styles.text}> / </p>
          <FormControl>
            <NativeSelect value={time3} onChange={time3Change}>
              <option hidden value={-1}>
                __:__
              </option>
              {timeTable.map((time) => {
                if (time.id > time2) {
                  return (
                    <option key={time.id} value={time.id}>
                      {time.time}
                    </option>
                  );
                } else {
                  return null;
                }
              })}
            </NativeSelect>
          </FormControl>
          <p className={styles.text}> ~ </p>
          {/* time4 */}
          <FormControl>
            <NativeSelect value={time4} onChange={time4Change}>
              <option hidden value={-1}>
                __:__
              </option>
              {timeTable.map((time) => {
                if (time.id > time3) {
                  return (
                    <option key={time.id} value={time.id}>
                      {time.time}
                    </option>
                  );
                } else {
                  return null;
                }
              })}
              <option value={22}>20:00</option>
            </NativeSelect>
          </FormControl>
        </div>
      ) : null}
    </div>
  );
}

export default SetOperatingTimeItem;
