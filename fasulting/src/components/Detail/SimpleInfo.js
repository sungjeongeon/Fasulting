import React, { useState } from "react";

import styles from "./SimpleInfo.module.css";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import FavoriteIcon from "@mui/icons-material/Favorite";
import TagIcon from "@mui/icons-material/Tag";
import { IconButton } from "@mui/material";

function SimpleInfo() {
  const ps_name = "더성형외과의원";
  const ps_address = "부산 강서구 녹산산단 335로 7, 송삼빌딩 1층";
  const sub_category = [
    "쌍커풀",
    "트임시술",
    "지방흡입",
    "콧볼축소",
    "코끝",
    "입꼬리",
    "보톡스",
    "보톡스",
    "보톡스",
    "보톡스",
    "보톡스",
    "보톡스",
  ];
  // liked(좋아요 상태) t/f
  const [liked, setLiked] = useState(false);
  const onClick = () => {
    setLiked((current) => !current);
  };
  return (
    <div>
      {/* 프로필 사진 */}
      <div className={styles.profileImg}></div>
      <div className={styles.nameAndLikeDiv}>
        {/* 이름 */}
        <p className={styles.name}>{ps_name}</p>
        {/* 하트(좋아요/취소) */}
        {/* [liked] t/f 에 따라 빨강/회색 */}
        <IconButton color="error" aria-label="favorite" onClick={onClick}>
          {liked ? (
            <FavoriteIcon fontSize="large" sx={{ color: "#e64c3c" }} />
          ) : (
            <FavoriteIcon fontSize="large" sx={{ color: "#d9d4cf" }} />
          )}
        </IconButton>
      </div>
      {/* 주소 */}
      <div className={styles.address}>
        <LocationOnIcon />
        <p>{ps_address}</p>
      </div>
      {/* 수술 분야 (세부) */}
      <p>
        {sub_category.map((sub, index) => {
          return (
            <button key={index} className={styles.subCategory}>
              <TagIcon sx={{ fontSize: 12 }} /> {sub}
            </button>
          );
        })}
      </p>
    </div>
  );
}

export default SimpleInfo;
