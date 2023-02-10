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
import { useEffect, useState } from "react";
import axiosAPi from "../../api/axiosApi";

function MypageHo() {
  const [loading, setLoading] = useState(true)
  const [hospital, setHospital] = useState(null)
  // 병원 정보 axios 요청
  // /ps/info/{seq}
  const psSeq = 1
  const getInfo = async() => {
    try {
      const res = await axiosAPi.get(`/ps/info/${psSeq}`)
      setHospital(res.data.responseObj)
      setLoading(false)
    } catch(err) {
      console.log(err)
    }
  }

  useEffect(() => {
    getInfo()
  }, [])
  console.log(hospital)

  return (
    <div>
    { loading ? <div></div> :
    <Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={0}>
        {/* 프로필(배경) 이미지 */}
        <BackgroundImage hospital={hospital}/>
        <Grid xs={12} style={{ height: "15rem" }}></Grid>
        <Grid xs={9}>
          <ProfileImageUpdate name={hospital.psName} profileImg={hospital.psProfileImg}/>
          <hr />
          <ProfileUpdateForm title={"소개"} content={hospital.psIntro} />
          <ProfileUpdateForm title={"주소"} content={hospital.psAddress} />
          <ProfileUpdateForm title={"연락처"} content={hospital.psNumber} />
          <ProfileUpdateForm
            title={"홈페이지"}
            content={hospital.psHomepage}
          />
          <ProfileTimeUpdate />
          <ProfileSubCategoryUpdate ctg_list={hospital.subCategoryName} />
          <ProfileDoctorUpdate doctors={hospital.doctor} />
          {/* <ReviewInfo /> */}
        </Grid>
        <Grid xs={3}></Grid>
      </Grid>
    </Box>
}
    </div>
  );
}

export default MypageHo;
