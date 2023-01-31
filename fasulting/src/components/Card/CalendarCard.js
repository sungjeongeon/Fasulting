import React, { useState } from "react";
import '@hassanmojab/react-modern-calendar-datepicker/lib/DatePicker.css';
import { Calendar, utils } from "@hassanmojab/react-modern-calendar-datepicker";
import styles from "./CalendarCard.module.css"
import NewReservationCard from "./NewReservationCard";
import NotificationsActiveOutlinedIcon from '@mui/icons-material/NotificationsActiveOutlined';

const CalendarCard = () => {
  const dateNow = new Date();
  const today = dateNow.toISOString().slice(0, 10);
  // console.log(Number(today.split('-')[1]))
  const defaultValue = {
    year: Number(today.split('-')[0]),
    month: Number(today.split('-')[1]),
    day: Number(today.split('-')[2])
  }

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
        shouldHighlightWeekends
      />
      <p className={styles.p}>새로운 예약 알림</p>
      <NewReservationCard/>
    </div>
  );
};

export default CalendarCard;