import ConsultingCard from "./ConsultingCard";
import { Grid } from "@mui/material";
import styles from "./FavResCard.module.css";
import axios from "axios";
import { useEffect } from "react";
import axiosAPi from "../../api/axiosApi";
import { useState } from "react";
function ConsultingCardList({ consulting }) {
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
