import React from "react";
import styles from "./DoctorCard.module.css";
import TagIcon from "@mui/icons-material/Tag";

function DoctorCard({ name, main_category }) {
  return (
    <div className={styles.card}>
      {/* 프로필이미지 임시 */}
      <div className={styles.profileImage}></div>
      <p className={styles.name}>{name}</p>
      <button className={styles.mainCategory}>
        <TagIcon sx={{ fontSize: 12 }} /> {main_category}
      </button>
    </div>
  );
}

export default DoctorCard;
