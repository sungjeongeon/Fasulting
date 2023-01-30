// import { useParams } from "react-router-dom";
import ReserveCard from "../../components/Card/ReserveCard";
// import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
// import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Unstable_Grid2";
import BackgroundImage from "../../components/Detail/BackgroundImage";
import SimpleInfo from "../../components/Detail/SimpleInfo";
import HospitalInfo from "../../components/Detail/HospitalInfo";
import DoctorCardList from "../../components/Card/DoctorCardList";
import ReviewInfo from "../../components/Detail/ReviewInfo";
import ProfileImageUpdate from "../../components/Detail/ProfileImageUpdate";
import ProfileUpdateForm from "../../components/Detail/ProfileUpdateForm";
import ProfileSubCategoryUpdate from "../../components/Detail/ProfileSubCategoryUpdate";
import ProfileDoctorUpdate from "../../components/Detail/ProfileDoctorUpdate";
import ProfileTimeUpdate from "../../components/Detail/ProfileTimeUpdate";
import { useState } from "react";

function MypageHo() {
  const hospital = {
    ps_id: 1,
    ps_name: "아이디병원",
    ps_intro: "최선을 다하자",
    ps_address: "서울 강남구 테헤란로 67, 강남 k빌딩 8층",
    ps_number: "02-547-0050",
    ps_homepage: "https://www.idhospital.com/",
    ctg_list: [
      {
        id: 1,
        name: "눈 성형",
        sub_ctg: [
          {
            id: 1,
            name: "쌍커풀"
          },
          {
            id: 2,
            name: "눈매교정"
          },
          {
            id: 3,
            name: "안검하수"
          },
        ]
      },
      {
        id: 2,
        name: "코 성형",
        sub_ctg: [
          {
            id: 1,
            name: "콧대 성형"
          },
        ]
      },
      {
        id: 7,
        name: "남자 성형",
        sub_ctg: [
          {
            id: 3,
            name: "남자 안면윤곽"
          },
        ]
      },
    ],
    // sub_category_id: [
    //   "쌍커풀",
    //   "트임시술",
    //   "지방흡입",
    //   "콧볼축소",
    //   "코끝",
    //   "입꼬리",
    //   "보톡스",
    // ],
    ps_doctors: [
      {
        id: 1,
        name: "김의사",
        main_category: "안면윤곽",
      },
      {
        id: 2,
        name: "이의사",
        main_category: "쁘띠",
      },
      {
        id: 3,
        name: "권의사",
        main_category: "안티에이징",
      },
    ],
  };

  // prop 보낼 제공 가능 수술 리스트
  // const [possibleCtg, setPossibleCtg] = useState(hospital.ctg_list)

  // 추가 핸들러
  // const addPossible = () => {

  // }

  // 삭제 핸들러


  return (
    <Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={0}>
        {/* 프로필(배경) 이미지 */}
        <BackgroundImage />
        <Grid xs={12} style={{ height: "15rem" }}></Grid>
        <Grid xs={9}>
          <ProfileImageUpdate name={hospital.ps_name} />
          <hr />
          <ProfileUpdateForm title={"소개"} content={hospital.ps_intro} />
          <ProfileUpdateForm title={"주소"} content={hospital.ps_address} />
          <ProfileUpdateForm title={"연락처"} content={hospital.ps_number} />
          <ProfileUpdateForm
            title={"홈페이지"}
            content={hospital.ps_homepage}
          />
          <ProfileTimeUpdate />
          <ProfileSubCategoryUpdate ctg_list={hospital.ctg_list} />
          <ProfileDoctorUpdate doctors={hospital.ps_doctors} />
          <ReviewInfo />
        </Grid>
        <Grid xs={3}></Grid>
      </Grid>
    </Box>
  );
}

export default MypageHo;
