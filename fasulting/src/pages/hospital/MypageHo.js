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

function MypageHo() {
  const hospital = {
    ps_id: 1,
    ps_name: "아이디병원",
    ps_intro: "최선을 다하자",
    ps_address: "서울 강남구 테헤란로 67, 강남 k빌딩 8층",
    ps_number: "02-547-0050",
    ps_homepage: "https://www.idhospital.com/",
    sub_category_id: [
      "쌍커풀",
      "트임시술",
      "지방흡입",
      "콧볼축소",
      "코끝",
      "입꼬리",
      "보톡스",
    ],
  };
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
          {/* 운영시간 */}
          <ProfileSubCategoryUpdate sub_category={hospital.sub_category_id} />
          <ReviewInfo />
        </Grid>
        <Grid xs={3}></Grid>
      </Grid>
    </Box>
  );
}

export default MypageHo;
