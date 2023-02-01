import React, { useState } from "react";
import '@hassanmojab/react-modern-calendar-datepicker/lib/DatePicker.css';
import { Calendar, utils } from "@hassanmojab/react-modern-calendar-datepicker";
import styles from "./CalendarCard.module.css"
import NewReservationCard from "./NewReservationCard";
import NotificationsActiveOutlinedIcon from '@mui/icons-material/NotificationsActiveOutlined';

const CalendarCard = () => {
  const dateNow = new Date();
  const today = dateNow.toISOString().slice(0, 10);  // yyyy-mm-dd 형태로 변환
  const calcDate = (str) => {
    return {
      year: Number(str.split('-')[0]),
      month: Number(str.split('-')[1]),
      day: Number(str.split('-')[2]),
    }
  }
  const defaultValue = calcDate(today)
  
  const endDate = new Date(dateNow.setDate(dateNow.getDate()+13))
  const maximumDate = endDate.toISOString().slice(0, 10);
  const maxValue = calcDate(maximumDate)

  const [selectedDay, setSelectedDay] = useState(defaultValue);
  // console.log(selectedDay) // {day: 27, month: 2, year: 2023} 형태
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