import React from "react";
import LastEstimateCard from "../../components/Card/LastEstimateCard";
import ScheduleCard from "../../components/Card/ScheduleCard";
import ReservationManage from "../../components/Tab/ReservationManage"
import { useSelector } from "react-redux";
import HospitalReservation from "../../components/Modal/HospitalReservation";
import { useState } from "react";
import { useEffect } from "react";


function MyReservationHo() {
  // 현재 활성화된 예약 강조
  const [nowShow, setNowShow] = useState(0)
  useEffect(() => {
    setNowShow(0)
  },[])

  // 탭 전환에 따라 왼쪽 컴포넌트가 달라져야 함
  const [value, setValue] = React.useState(0);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  // // state 가져오기
  // const reservationId = useSelector(state => {
  //   return state.lastReservationHo.reservation_id
  // })
  
  // 모달창 state 가져오기
  const modalOpen = useSelector(state => {
    return state.modalInfo.modalstate
  })

  return(
    <div style={{ display: "grid", gridTemplateColumns: "11fr 6fr", marginTop: '2.5rem'}}>
      {
      value === 0 ? 
      <div style={{display: "flex", flexDirection:"column"}}>
        <p style={{fontSize: "1rem", color:"#72A1A6", fontWeight:"bold", marginTop:0}}>자세한 예약 현황을 보시려면 예약자 이름을 클릭하세요.</p>
        <ScheduleCard/>
      </div>:
        nowShow === 0 ? 
        <div></div> :
        <div>
          <LastEstimateCard/>
        </div>
      }
      <ReservationManage handleChange={handleChange} value={value} nowShow={nowShow} setNowShow={setNowShow}/>
      {modalOpen && <HospitalReservation/>}
    </div>
  )
}

export default MyReservationHo;