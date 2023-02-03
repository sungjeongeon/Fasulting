import React, { useEffect, useState } from "react";
import '@hassanmojab/react-modern-calendar-datepicker/lib/DatePicker.css';
import { Calendar, utils } from "@hassanmojab/react-modern-calendar-datepicker";
import styles from "./CalendarCard.module.css"
import NewReservationCard from "./NewReservationCard";
import { useDispatch } from "react-redux";
import { changeDate } from "../../redux/calendar"

const CalendarCard = () => {
  const dateNow = new Date();
  // yyyy-mm-dd 형태로 변환
  const calcDate = (str) => {
    const strDate = str.toISOString().slice(0, 10).split('-')
    return {
      year: Number(strDate[0]),
      month: Number(strDate[1]),
      day: Number(strDate[2]),
    }
  }
  // 기존 value
  const defaultValue = calcDate(dateNow)
  // 2주 뒤의 날짜 계산
  const endDate = new Date(dateNow.setDate(dateNow.getDate()+13))
  const maxValue = calcDate(endDate)

  const [selectedDay, setSelectedDay] = useState(defaultValue);
  // console.log(selectedDay) // {day: 27, month: 2, year: 2023} 형태
  const dispatch = useDispatch()
  useEffect(() => {
    dispatch(changeDate(selectedDay))
  }, [dispatch, selectedDay])
  return (
    <div>
      <Calendar
        value={selectedDay}
        onChange={setSelectedDay}
        colorPrimary="#72a1a6"
        calendarTodayClassName={styles.today}
        minimumDate={utils().getToday()}
        maximumDate={maxValue}
        shouldHighlightWeekends
      />
      <p className={styles.p}>새로운 예약 알림</p>
      <NewReservationCard/>
    </div>

  );
};

export default CalendarCard;