import React, { useEffect } from "react";
import propTypes from "prop-types";
import styles from "./ReviewListItem.module.css";
import StarIcon from "@mui/icons-material/Star";
import { useLocation } from "react-router-dom";
import { useState } from "react";

function ReviewListItem({ review }) {
  const [displayHospital, setDisplayHospital] = useState();
  const path = useLocation().pathname.slice(1, 7);
  // 현재 path가 detail이거나 (병원)mypage 이면 병원이름 보여주지 않음
  useEffect(() => {
    const temp = (path === "detail") | (path === "mypage") ? true : false;

    setDisplayHospital(temp);
  }, []);

  return (
    <div>
      {/* 테스트이메일 문제 */}
      {displayHospital && displayHospital ? (
        <p className={styles.name}>
          {review.userEmail.charAt(0) + review.userEmail.charAt(1) + "***"}
        </p>
      ) : null}

      <div className={styles.oneLine}>
        <StarIcon sx={{ fontSize: 18, color: "#EECC51" }} />
        <p className={styles.rating}>{review.point.toFixed(1)}</p>
        {displayHospital && displayHospital ? null : (
          <p className={styles.gray}>{review.psName}</p>
        )}
        {displayHospital && displayHospital ? null : (
          <p className={styles.gray}>|</p>
        )}
        {review.subCategoryName.map((sub, index) => (
          <p key={index} className={styles.gray}>
            {sub}
          </p>
        ))}
      </div>
      <p className={styles.content}>{review.content}</p>
      <p className={styles.date}>{review.regDate}</p>
      <hr />
    </div>
  );
}

export default ReviewListItem;
