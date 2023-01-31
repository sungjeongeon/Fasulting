import * as React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import styles from "./NewReservationCard.module.css"
import CloseIcon from '@mui/icons-material/Close';

// 새로운 예약 알림인듯?? (체크 말고 휴지통 아이콘)
function NewReservationCard() {
  return (
    <Card sx={{ width: '90%', marginTop: '0.5rem' }}>
      <CardContent sx={{ width: '92%', marginY: 0}}>
        <div style={{position: 'relative'}}>
          <div style={{position: 'absolute', left: '100%'}}><CloseIcon/></div>
          <Typography color="text.secondary" fontWeight={"bold"} fontSize={"1rem"} marginBottom={"0.5rem"}>
            상담 날짜 관련
          </Typography>
          <Typography color="text.secondary" fontWeight={"bold"} fontSize={"1rem"} marginBottom={"0.5rem"}>
            상담자 이름 
          </Typography>
          <Typography color="#72a1a6" fontWeight={"bold"} fontSize={"1rem"}>
            상담 부위
          </Typography>
        </div>
      </CardContent>
    </Card>
  )
}

export default NewReservationCard;