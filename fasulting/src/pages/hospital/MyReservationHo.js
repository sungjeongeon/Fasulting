import { Box } from "@mui/system";
import React from "react";
import { useState } from "react";
import CalendarCard from "../../components/Card/CalendarCard";
import LastEstimateCard from "../../components/Card/LastEstimateCard";
import ScheduleCard from "../../components/Card/ScheduleCard";
import ReservationManage from "../../components/Tab/ReservationManage"
import WeekSchedule from "../../components/Table/WeekSchedule";

function MyReservationHo() {
  // 탭 전환에 따라 왼쪽 컴포넌트가 달라져야 함
  const [value, setValue] = React.useState(0);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  // 캘린더 선택 -> 날짜를 다른 컴포넌트에 전달해야 함 // year: month: date: 숫자로 필요
  // const [selectedDay, setSelectedDay] = useState();
  // const changeSelect = (obj) => {
  //   setSelectedDay(obj)
  // }

  // 지난 예약보기에서 예약 id별로 나오는 견적서가 달라야 함
  // const [estimateId, setEstimateId] = useState(-1)
  // const changeEstimate = (idVal) => {
  //   setEstimateId(idVal)
  // }
  return(
    <div style={{ display: "grid", gridTemplateColumns: "5fr 3fr", marginTop: '2.5rem'}}>
      {
      value === 0 ? 
      <div style={{display: "flex", flexDirection:"column"}}>
        <p style={{fontSize: "1rem", color:"#72A1A6", fontWeight:"bold", marginTop:0}}>자세한 예약 현황을 보시려면 예약자 이름을 클릭하세요.</p>
        {/* <ScheduleCard/> */}
        <WeekSchedule/>
      </div>:
      <div>
        <LastEstimateCard/>
      </div>
      }
      <ReservationManage handleChange={handleChange} value={value}/>
    </div>
  )
}

export default MyReservationHo;