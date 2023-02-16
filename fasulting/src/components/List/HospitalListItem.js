import React from "react";
import styles from "./HospitalListItem.module.css";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import TagIcon from "@mui/icons-material/Tag";
import { Link, useNavigate } from "react-router-dom";

function HospitalListItem({ hospital }) {
  const imgRoot = "https://hotsix.s3.ap-northeast-2.amazonaws.com/null";
  const basicSrc = "/assets/images/psBasicProfile.png";

  const navigate = useNavigate();

  const onClick = () => {
    //<Link to={`/detail/${hospital.psSeq}`} />;
    navigate(`/detail/${hospital.psSeq}`);
  };
  return (
    <div className={styles.outerDiv} onClick={onClick}>
      <div className={styles.hr}></div>
      <div className={styles.innerDiv}>
        {/* 왼쪽 */}
        <div className={styles.leftDiv}>
          <p className={styles.address}>
            <LocationOnIcon />
            {hospital.psAddress}
            {/* 현재 데이터 null */}
          </p>
          <p className={styles.name}>{hospital.psName}</p>
          <p className={styles.intro}>{hospital.psIntro}</p>
          {/* 현재 데이터 null */}
          {hospital.subCategoryName.map((sub, index) => {
            return (
              <button key={index} className={styles.subCategory}>
                <TagIcon sx={{ fontSize: 12 }} /> {sub}
              </button>
            );
          })}
        </div>
        {/* 오른쪽 */}
        <div className={styles.rightDiv}>
          {/* 이미지 (임시) */}
          <div className={styles.divImg}>
            <img
              className={styles.img}
              src={
                hospital.psProfileImg === imgRoot
                  ? basicSrc
                  : hospital.psProfileImg
              }
              alt="profileImg"
            />
          </div>
          <p className={styles.rating}>
            <span>★</span>
            {/* {hospital.totalRatingResult.toFixed(1)} | 관련 후기{" "} */}
            {hospital.totalRatingResult} | 관련 후기 {hospital.reviewTotalCount}
            개
          </p>
        </div>
      </div>
    </div>
  );
}

export default HospitalListItem;
