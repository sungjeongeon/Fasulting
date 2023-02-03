import { Box } from "@mui/system";
import React from "react";
import { useState } from "react";
import CalendarCard from "../../components/Card/CalendarCard";
import LastEstimateCard from "../../components/Card/LastEstimateCard";
import ScheduleCard from "../../components/Card/ScheduleCard";
import ReservationManage from "../../components/Tab/ReservationManage"
import WeekSchedule from "../../components/Table/WeekSchedule";
import { useSelector } from "react-redux";


function MyReservationHo() {
  // 탭 전환에 따라 왼쪽 컴포넌트가 달라져야 함
  const [value, setValue] = React.useState(0);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  // state 가져오기
  const reservationId = useSelector(state => {
    return state.lastReservationHo.reservation_id
  })
  
  return(
    <div style={{ display: "grid", gridTemplateColumns: "5fr 3fr", marginTop: '2.5rem'}}>
      {
      value === 0 ? 
      <div style={{display: "flex", flexDirection:"column"}}>
        <p style={{fontSize: "1rem", color:"#72A1A6", fontWeight:"bold", marginTop:0}}>자세한 예약 현황을 보시려면 예약자 이름을 클릭하세요.</p>
        {/* <ScheduleCard/> */}
        <WeekSchedule/>
      </div>:
        reservationId === undefined ? 
        <div></div> :
        <div>
          <LastEstimateCard/>
        </div>
      }
      <ReservationManage handleChange={handleChange} value={value}/>
    </div>
  )
}

export default MyReservationHo;