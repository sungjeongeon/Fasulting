import * as React from "react";
import { useState } from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import LinearProgress, {
  linearProgressClasses,
} from "@mui/material/LinearProgress";
import { styled } from "@mui/material/styles";
import Cancel from "../Modal/Cancel";
import styles from "./FavResCard.module.css";

const BorderLinearProgress = styled(LinearProgress)(({ theme }) => ({
  height: 10,
  borderRadius: 5,
  [`&.${linearProgressClasses.colorPrimary}`]: {
    backgroundColor:
      theme.palette.grey[theme.palette.mode === "light" ? 200 : 800],
  },
  [`& .${linearProgressClasses.bar}`]: {
    borderRadius: 5,
    backgroundColor: theme.palette.mode === "light" ? "primary" : "#308fe8",
  },
}));

function ConsultingCard({ consult }) {
  // 모달창
  const [ModalOpen, setModalOpen] = useState(false);
  const ModalStateChange = () => setModalOpen((current) => !current);

  return (
    <div className={styles.column}>
      <div className={styles.card}>
        <div>
          <Typography variant="h6" component="div">
            {consult.ps_name}
          </Typography>
          <Typography sx={{ mb: 1.5 }} color="text.secondary">
            {consult.sub_category_name}
          </Typography>
          <Typography variant="body2">{consult.calender_id}</Typography>

          <BorderLinearProgress variant="determinate" value={60} />
        </div>
        <div className={styles.button}>
          <Button
            variant="outlined"
            color="error"
            onClick={ModalStateChange}
            className={styles.width}
          >
            예약취소
          </Button>
          {ModalOpen && <Cancel ModalStateChange={ModalStateChange} />}
          <Button
            variant="contained"
            style={{ color: "white" }}
            className={styles.width}
          >
            상담입장
          </Button>
        </div>
      </div>
    </div>
  );
}

export default ConsultingCard;
