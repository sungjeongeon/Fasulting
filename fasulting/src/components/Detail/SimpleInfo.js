import React, { useEffect, useState } from "react";

import styles from "./SimpleInfo.module.css";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import FavoriteIcon from "@mui/icons-material/Favorite";
import TagIcon from "@mui/icons-material/Tag";
import { IconButton } from "@mui/material";
import axiosAPi from "../../api/axiosApi";
import { useSelector } from "react-redux";

function SimpleInfo({ detailhospital }) {
  console.log("디테일병원", detailhospital);

  // liked(좋아요 상태) t/f
  const [liked, setLiked] = useState(detailhospital.favorite);
  const userData = useSelector((state) => state.user);

  const onClick = () => {
    if (!liked) {
      console.log(liked, userData.userSeq, detailhospital.psSeq);
      axiosAPi
        .post("/favorite", {
          userSeq: userData.userSeq,
          psSeq: detailhospital.psSeq,
        })
        .then((res) => {
          console.log("1번", res);
          setLiked((current) => !current);
        });
    } else {
      console.log("시러오", liked, userData.userSeq, detailhospital.psSeq);
      axiosAPi
        .delete("/favorite", {
          data: {
            userSeq: userData.userSeq,
            psSeq: detailhospital.psSeq,
          },
        })
        .then((res) => setLiked((current) => !current))
        .catch((e) => console.log(e));
    }
  };

  useEffect(() => {
    setLiked(detailhospital.favorite);
  }, [detailhospital]);
  return (
    <div>
      {/* 프로필 사진 */}
      <div className={styles.profileImg}>
        <img
          className={styles.Img}
          src={detailhospital.psProfileImg}
          alt="profile"
        />
      </div>
      <div className={styles.nameAndLikeDiv}>
        {/* 이름 */}
        <p className={styles.name}>{detailhospital.psName}</p>
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
        <p>{detailhospital.psAddress}</p>
      </div>
      {/* 수술 분야 (세부) */}
      {/* {detailhospital &&
        detailhospital.subCategoryName.map((sub, index) => {
          return (
            <button key={index} className={styles.subCategory}>
              <TagIcon sx={{ fontSize: 12 }} /> {sub}
            </button>
          );
        })} */}
    </div>
  );
}

export default SimpleInfo;
