import { Box } from "@mui/system";
import React from "react";
import CalendarCard from "../../components/Card/CalendarCard";
import ReservationManage from "../../components/Tab/ReservationManage"

function MyReservationHo() {
  return(
    <div style={{ display: "grid", gridTemplateColumns: "2fr 1fr", marginTop: '2.5rem'}}>
      <Box sx={{border: '1px dashed grey'}}>
      </Box>
      <ReservationManage/>
    </div>
  )
}

export default MyReservationHo;