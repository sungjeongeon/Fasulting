import React from "react";
import { useSelector, useDispatch } from "react-redux";

function WeekSchedule() {
  // const dispatch = useDispatch()
  const propdate = useSelector(state => {
    return state.calendar
  })
  // console.log(propdate) // {year: 2023, month: 2, day: 6}
  return(
    <div>
      주간 스케줄표
    </div>
  )
}

export default WeekSchedule;