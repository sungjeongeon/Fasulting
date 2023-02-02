import React from "react";
import ClientInfo from "../Detail/ClientInfo";
import BeforeAfterCard from "./BeforeAfterCard";
import EstimateHocard from "./EstimateHoCard";

function LastEstimateCard() {
  return (
    <div style={{marginLeft: '1rem', marginTop: '1rem'}}>
      <BeforeAfterCard/>
      <div style={{display: 'flex', marginBottom: '3rem'}}>
        <ClientInfo/>
        <EstimateHocard/>
      </div>
    </div>
  )
}
export default LastEstimateCard