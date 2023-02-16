import Box from "@mui/material/Box";
import Grid from "@mui/material/Unstable_Grid2";
import BackgroundImage from "../../components/Detail/BackgroundImage";
import ReviewInfo from "../../components/Detail/ReviewInfo";
import ProfileImageUpdate from "../../components/Detail/ProfileImageUpdate";
import ProfileUpdateForm from "../../components/Detail/ProfileUpdateForm";
import ProfileSubCategoryUpdate from "../../components/Detail/ProfileSubCategoryUpdate";
import ProfileDoctorUpdate from "../../components/Detail/ProfileDoctorUpdate";
import ProfileTimeUpdate from "../../components/Detail/ProfileTimeUpdate";
import { useEffect, useState } from "react";
import axiosAPi from "../../api/axiosApi";
import { useSelector } from "react-redux";

function MypageHo() {
  const [loading, setLoading] = useState(true);
  const [hospital, setHospital] = useState(null);

  const psSeq = useSelector((state) => state.ps.psSeq);
  const getInfo = async () => {
    try {
      const res = await axiosAPi.get(`/ps/info/${psSeq}`);
      setHospital(res.data.responseObj);
      setLoading(false);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getInfo();
  }, []);
  console.log(hospital);

  return (
    <div>
      {loading ? (
        <div></div>
      ) : (
        <Box sx={{ flexGrow: 1 }}>
          <Grid container spacing={0}>
            {/* 프로필(배경) 이미지 */}
            <BackgroundImage detailhospital={hospital} />
            <Grid xs={12} style={{ height: "15rem" }}></Grid>
            <Grid xs={9}>
              <ProfileImageUpdate
                name={hospital.psName}
                profileImg={hospital.psProfileImg}
                psSeq={psSeq}
              />
              <hr />
              <ProfileUpdateForm
                title={"소개"}
                content={hospital.psIntro}
                psSeq={psSeq}
              />
              <ProfileUpdateForm
                title={"주소"}
                content={hospital.psAddress}
                psSeq={psSeq}
              />
              <ProfileUpdateForm
                title={"연락처"}
                content={hospital.psNumber}
                psSeq={psSeq}
              />
              <ProfileUpdateForm
                title={"홈페이지"}
                content={hospital.psHomepage}
                psSeq={psSeq}
              />
              <ProfileTimeUpdate
                psSeq={psSeq}
                defaultTime={hospital.defaultTime}
              />
              <ProfileSubCategoryUpdate
                ctg_list={hospital.subCategoryName}
                psSeq={psSeq}
              />
              <ProfileDoctorUpdate doctors={hospital.doctor} psSeq={psSeq} />
              <ReviewInfo detailhospital={hospital} />
            </Grid>
            <Grid xs={3}></Grid>
          </Grid>
        </Box>
      )}
    </div>
  );
}

export default MypageHo;
