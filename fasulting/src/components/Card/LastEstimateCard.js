import React from "react";
import ClientInfo from "../Detail/ClientInfo";
import BeforeAfterCard from "./BeforeAfterCard";
import EstimateHocard from "./EstimateHoCard";
import { useSelector } from "react-redux";

function LastEstimateCard() {
  const estimateInfo = useSelector(state => {
    return state.lastReservationHo
  })

  return (
    estimateInfo.loading ? <div></div> :
    <div style={{marginLeft: '3rem', marginTop: '1rem'}}>
      <BeforeAfterCard before={estimateInfo.beforeImg} after={estimateInfo.afterImg}/>
      <div style={{display: 'flex', marginBottom: '3rem'}}>
        <ClientInfo client={estimateInfo}/>
        <EstimateHocard estimate={estimateInfo}/>
      </div>
    </div>
  )
}
export default LastEstimateCard