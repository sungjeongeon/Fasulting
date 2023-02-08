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
      user_id: 1,
      ps_id: 1,
      ps_name: "아이디 병원",
      sub_category_name: "쌍커풀",
      calender_id: "2023.12.30 10시 25분",
    },
    {
      user_id: 2,
      ps_id: 2,
      ps_name: "더페이스 병원",
      sub_category_name: "콧볼축소",
      calender_id: "2022.01.31 12시 30분",
    },
    {
      user_id: 2,
      ps_id: 2,
      ps_name: "더페이스 병원",
      sub_category_name: "콧볼축소",
      calender_id: "2022.01.31 12시 30분",
    },
    {
      user_id: 2,
      ps_id: 2,
      ps_name: "더페이스 병원",
      sub_category_name: "콧볼축소",
      calender_id: "2022.01.31 12시 30분",
    },
  ];
  return (
    <div className={styles.margin}>
      <h2>진행중인 예약</h2>
      <div className={styles.flex}>
        {consulting.map((consult) => (
          <ConsultingCard key={consult.ps_id.toString()} consult={consult} />
        ))}
      </div>
    </div>
  );
}
export default ConsultingCardList;
