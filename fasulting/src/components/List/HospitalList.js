import React from "react";
import HospitalListItem from "./HospitalListItem";
import styles from "./HospitalList.module.css";
import { useParams } from "react-router-dom";

function HospitalList() {
  // 임시 data
  const HospitalList = [
    {
      ps_id: 1,
      ps_intro: "원하는 니즈를 최대한 충족 시키는 부산 1위 성형외과",
      ps_name: "더성형외과의원",
      ps_address: "부산 강서구 녹산산단 335로 7, 송삼빌딩 1층",
      sub_category_id: [
        "쌍커풀",
        "트임시술",
        "지방흡입",
        "콧볼축소",
        "코끝",
        "입꼬리",
        "보톡스",
      ],
      ps_profile_img: "src",
      total_rating_result: 3.0,
      total_rating_count: 30,
    },
    {
      ps_id: 2,
      ps_intro: "원하는 니즈를 최대한 충족 시키는 부산 1위 성형외과",
      ps_name: "더성형외과의원",
      ps_address: "부산 강서구 녹산산단 335로 7, 송삼빌딩 1층",
      sub_category_id: ["쌍커풀", "콧볼축소", "코끝", "입꼬리", "보톡스"],
      ps_profile_img: "src",
      total_rating_result: 3.0,
      total_rating_count: 30,
    },
  ];

  const seq = useParams();

  return (
    <div>
      {HospitalList.map((hospital) => (
        <HospitalListItem key={hospital.ps_id.toString()} hospital={hospital} />
      ))}
    </div>
  );
}

export default HospitalList;
