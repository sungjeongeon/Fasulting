import React, { useEffect } from "react";
import propTypes from "prop-types";
import styles from "./ReviewListItem.module.css";
import StarIcon from "@mui/icons-material/Star";
import { useLocation } from "react-router-dom";

function ReviewListItem({
  username,
  content,
  date,
  hospital,
  subcategory,
  rating,
}) {
  // 현재 path가 detail이면 병원이름 보여주지 않음
  const isDetail =
    useLocation().pathname.slice(1, 7) === "detail" ? true : false;

  return (
    <div>
      <p className={styles.name}>{username.charAt(0) + "**"}</p>
      <div className={styles.oneLine}>
        <StarIcon sx={{ fontSize: 18, color: "#EECC51" }} />
        <p className={styles.rating}>{rating.toFixed(1)}</p>
        {isDetail ? null : <p className={styles.gray}>{hospital}</p>}
        {isDetail ? null : <p className={styles.gray}>|</p>}
        {subcategory.map((sub, index) => (
          <p key={index} className={styles.gray}>
            {sub}
          </p>
        ))}
      </div>
      <p className={styles.content}>{content}</p>
      <p className={styles.date}>{date}</p>
      <hr />
    </div>
  );
}

ReviewListItem.propTypes = {
  username: propTypes.string.isRequired,
  content: propTypes.string.isRequired,
  date: propTypes.string.isRequired,
  // date: propTypes.instanceOf(Date).isRequired,
  hospital: propTypes.string.isRequired,
  subcategory: propTypes.arrayOf(propTypes.string.isRequired),
  rating: propTypes.number.isRequired,
};

export default ReviewListItem;
