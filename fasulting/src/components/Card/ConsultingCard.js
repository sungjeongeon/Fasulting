import * as React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import LinearProgress, {
  linearProgressClasses
} from "@mui/material/LinearProgress";
import { styled } from "@mui/material/styles";

const BorderLinearProgress = styled(LinearProgress)(({ theme }) => ({
  height: 10,
  borderRadius: 5,
  [`&.${linearProgressClasses.colorPrimary}`]: {
    backgroundColor:
      theme.palette.grey[theme.palette.mode === "light" ? 200 : 800]
  },
  [`& .${linearProgressClasses.bar}`]: {
    borderRadius: 5,
    backgroundColor: theme.palette.mode === "light" ? "primary" : "#308fe8"
  }
}));

export default function ConsultingCard({consult}) {
  return (
    <Card sx={{ maxWidth: 275 }}>
      <CardContent>
        <Typography variant="h5" component="div">
            아이디병원
        </Typography>
        <Typography sx={{ mb: 1.5 }} color="text.secondary">
            쌍커풀
        </Typography>
        <Typography variant="body2" color='primary'>2023. 01. 20 (금) 15시 30분</Typography>
        
        <BorderLinearProgress variant="determinate" value={50} />
      </CardContent>
      <CardActions >
        <Button variant="outlined" color="error">예약취소</Button>
        <Button variant="contained" style={{color: 'white'}}>상담입장</Button>
      </CardActions>
    </Card>
  );
}
