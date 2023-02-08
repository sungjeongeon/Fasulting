import React, { useState } from "react";
import ClientInfo from "../Detail/ClientInfo";
import BeforeAfterCard from "./BeforeAfterCard";
import EstimateHocard from "./EstimateHoCard";
import { useSelector } from "react-redux";

function LastEstimateCard() {
  // const [loading, setLoading] = useState(true)
  const estimateInfo = useSelector(state => {
    return state.lastReservationHo
  })

  return (
    estimateInfo.loading ? <div></div> :
    <div style={{marginLeft: '3rem', marginTop: '1rem'}}>
      <BeforeAfterCard/>
      <div style={{display: 'flex', marginBottom: '3rem'}}>
        <ClientInfo/>
        <EstimateHocard/>
      </div>
    </div>
  )
}
export default LastEstimateCard