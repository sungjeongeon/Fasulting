import React, { useEffect } from "react";
import propTypes from "prop-types";
import styles from "./ReviewListItem.module.css";
import StarIcon from "@mui/icons-material/Star";
import { useLocation } from "react-router-dom";

function ReviewListItem({ psName, review }) {
  // 현재 path가 detail이거나 (병원)mypage 이면 병원이름 보여주지 않음
  const displayHospital =
    useLocation().pathname.slice(1, 7) === "detail" || "mypage" ? true : false;

  console.log("review", review);
  return (
    <div>
      {/* 테스트이메일 문제<p className={styles.name}>{review.userEmail.charAt(0) + "**"}</p> */}
      <div className={styles.oneLine}>
        <StarIcon sx={{ fontSize: 18, color: "#EECC51" }} />
        <p className={styles.rating}>{review.point.toFixed(1)}</p>
        {displayHospital ? null : <p className={styles.gray}>{psName}</p>}
        {displayHospital ? null : <p className={styles.gray}>|</p>}
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

ReviewListItem.propTypes = {
  psName: propTypes.string.isRequired,
  //review: propTypes.arrayOf(propTypes.string.isRequired),
};

export default ReviewListItem;
