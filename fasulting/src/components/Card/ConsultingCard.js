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
import "react-toastify/dist/ReactToastify.css";
import { toast, ToastContainer } from "react-toastify";
import { styled } from "@mui/material/styles";
import Cancel from "../Modal/Cancel";
import styles from "./FavResCard.module.css";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

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
  const navigate = useNavigate();
  // 모달창
  const [ModalOpen, setModalOpen] = useState(false);
  const [loading, setLoading] = useState(0);

  const ModalStateChange = () => {
    const today = new Date();
    // 취소 못하는 경우 (당일)
    if (consult.day === today.getDate()) {
      toast.error(<h5>예약 취소는 1일 전까지만 가능합니다.</h5>, {
        position: toast.POSITION.TOP_CENTER,
        autoClose: 2000,
      });
      setTimeout(() => {}, 2000);
    } else {
      setModalOpen((current) => !current);
    }
  };

  const enterConsultingRoom = () => {
    navigate("/consult", {
      state: { userSeq: consult.userSeq, psSeq: consult.psSeq, who: "client" },
    });
  };
  useEffect(() => {
    // 렌더링 시 상담일자 로딩바 % 구하기
    const consultDate = new Date(
      consult.year,
      consult.month - 1,
      consult.day,
      consult.hour,
      consult.minute
    );
    const today = new Date();
    const diff = (consultDate.getTime() - today.getTime()) / (60 * 60 * 1000);
    const loading = (1 - diff / (7 * 24)) * 100;
    setLoading(loading);
  }, []);

  return (
    <div className={styles.column}>
      <ToastContainer />
      <div className={styles.card}>
        <div>
          <Typography variant="h6" component="div">
            {consult.psName}
          </Typography>
          <Typography sx={{ mb: 1.5 }} color="text.secondary">
            {consult.subCategoryName.join(" / ")}
          </Typography>
          <Typography variant="body2">
            {consult.year}년 {consult.month}월 {consult.day}일 {consult.hour}시{" "}
            {consult.minute}분
          </Typography>

          <BorderLinearProgress variant="determinate" value={loading} />
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
          {ModalOpen && (
            <Cancel
              ModalStateChange={ModalStateChange}
              reservationSeq={consult.reservationSeq}
            />
          )}
          <Button
            variant="contained"
            style={{ color: "white" }}
            className={styles.width}
            onClick={enterConsultingRoom}
          >
            상담입장
          </Button>
        </div>
      </div>
    </div>
  );
}

export default ConsultingCard;
