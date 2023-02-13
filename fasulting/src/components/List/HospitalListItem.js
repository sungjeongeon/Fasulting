import React from "react";
import styles from "./HospitalListItem.module.css";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import TagIcon from "@mui/icons-material/Tag";
import { useNavigate } from "react-router-dom";

function HospitalListItem({ hospital }) {
  const navigate = useNavigate();

  const onClick = () => {
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
              src={hospital.psProfileImg}
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

// 임시 data 예시
// {
//   ps_id: 1,
//   ps_intro: "원하는 니즈를 최대한 충족 시키는 부산 1위 성형외과",
//   ps_name: "더성형외과의원",
//   ps_address: "부산 강서구 녹산산단 335로 7, 송삼빌딩 1층",
//   sub_category_id: [
//     "쌍커풀",
//     "트임시술",
//     "지방흡입",
//     "콧볼축소",
//     "코끝",
//     "입꼬리",
//     "보톡스",
//   ],
//   ps_profile_img: "src",
//   total_rating_result: 3.0,
//   totla_rating_count: 30,
// },
