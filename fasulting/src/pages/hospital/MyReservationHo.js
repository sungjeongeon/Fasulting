import { Box } from "@mui/system";
import React from "react";
import CalendarCard from "../../components/Card/CalendarCard";
import ScheduleCard from "../../components/Card/ScheduleCard";
import ReservationManage from "../../components/Tab/ReservationManage"

function MyReservationHo() {
  return(
    <div style={{ display: "grid", gridTemplateColumns: "5fr 3fr", marginTop: '2.5rem'}}>
      <div style={{display: "flex", flexDirection:"column"}}>
        <p style={{fontSize: "1rem", color:"#72A1A6", fontWeight:"bold", marginTop:0}}>자세한 예약 현황을 보시려면 예약자 이름을 클릭하세요.</p>
        <ScheduleCard/>
      </div>
      <ReservationManage/>
    </div>
  )
}

export default MyReservationHo;