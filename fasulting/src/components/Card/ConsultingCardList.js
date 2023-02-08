import ConsultingCard from "./ConsultingCard";
import { Grid } from "@mui/material";
import styles from "./FavResCard.module.css";
import axios from "axios";
import { useEffect } from "react";
import axiosAPi from "../../api/axiosApi";
function ConsultingCardList() {
  useEffect(() => {
    axiosAPi.get("/reservation/post/1").then((res) => {
      console.log(res);
    });
  }, []);
  const consulting = [
    {
      reservationSeq: 1,
      userSeq: 1,
      psSeq: 1,
      psName: "아이디 병원",
      subCategoryName: ["쌍커풀", "눈매교정"],
      year: 2023,
      month: 2,
      day: 8,
      hour: 21, // 24시간
      minute: 12,
    },
    {
      reservationSeq: 2,
      userSeq: 2,
      psSeq: 2,
      psName: "더페이스 병원",
      subCategoryName: ["콧볼축소", "콧대성형", "안면윤곽"],
      year: 2023,
      month: 2,
      day: 8,
      hour: 21, // 24시간
      minute: 12,
    },
    {
      reservationSeq: 3,
      userSeq: 3,
      psSeq: 3,
      psName: "더페이스 병원",
      subCategoryName: ["콧볼축소", "콧대성형"],
      year: 2023,
      month: 2,
      day: 10,
      hour: 21, // 24시간
      minute: 12,
    },
    {
      reservationSeq: 4,
      userSeq: 4,
      psSeq: 4,
      psName: "더페이스 병원",
      subCategoryName: ["콧볼축소", "콧대성형"],
      year: 2023,
      month: 2,
      day: 10,
      hour: 21, // 24시간
      minute: 12,
    },
  ];
  return (
    <div className={styles.margin}>
      <h2>진행중인 예약</h2>
      <div className={styles.flex}>
        {consulting.map((consult) => (
          <ConsultingCard key={consult.psSeq.toString()} consult={consult} />
        ))}
      </div>
    </div>
  );
}
export default ConsultingCardList;
