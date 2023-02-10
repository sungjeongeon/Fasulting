import { Container } from "@mui/system";
import EstimateCard from "../../components/Card/EstimateCard";
import HospitalInfo from "../../components/Detail/HospitalInfo";
import BeforeAfterCard from "../../components/Card/BeforeAfterCard";
import { useParams } from "react-router";
import { useEffect, useState } from "react";
import axiosAPi from "../../api/axiosApi";
import { useSelector } from "react-redux";
import { Typography } from "@mui/material";

function MyEstimate() {
  // 견적서 id 받아오기
  const consultingSeq = useParams().consultSeq;
  const userSeq = useSelector((store) => store.user.userSeq);

  const [before, setBefore] = useState("");
  const [after, setAfter] = useState("");
  const [report, setReport] = useState({});
  const [psInfo, setPsInfo] = useState({});
  useEffect(() => {
    axiosAPi
      .get(`/reservation/report/${userSeq}/${consultingSeq}`)
      .then((res) => {
        // 비포 / 애프터 사진
        setBefore(res.data.responseObj.beforeImgPath);
        setAfter(res.data.responseObj.afterImgPath);
        // 병원 정보
        const temp = {
          psName: res.data.responseObj.psName,
          subCategoryName: res.data.responseObj.subCategoryName,
          content: res.data.responseObj.content,
          estimate: res.data.responseObj.estimate,
        };
        setReport(temp);
        console.log(res.data.responseObj);
        const temp2 = {
          psSeq: res.data.responseObj.psSeq,
          psAddress: res.data.responseObj.psAddress,
          psNumber: res.data.responseObj.psNumber,
          psEmail: res.data.responseObj.psEmail,
          psHomepage: res.data.responseObj.psHomepage,
          defaultTime: res.data.responseObj.defaultTime,
          total_rating_result: 3.0,
          total_rating_count: 30,
        };
        setPsInfo(temp2);
      })
      .catch((e) => console.log(e));
  }, []);

  return (
    <Container maxidth="lg">
      {/* before after 사진 div */}
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          marginTop: "3rem",
        }}
      >
        <Typography component="h1" variant="h4" align="center">
          🧾 소견서 🧾
        </Typography>
        <BeforeAfterCard before={before} after={after} />
        <p
          style={{
            color: "#E64C3C",
            fontSize: "1rem",
            fontWeight: "bold",
            textAlign: "center",
          }}
        >
          상기 이미지는 가상 시뮬레이션 결과로,
          <br />
          실제 수술 결과와 다를 수 있습니다.
        </p>
      </div>
      <div style={{ display: "flex", justifyContent: "space-around" }}>
        <div style={{ width: "60%", marginTop: "2.5rem" }}>
          <EstimateCard report={report} />
        </div>
        {/* 병원 정보 div */}
        <div style={{ width: "30%", marginTop: "2.5rem" }}>
          <HospitalInfo detailhospital={psInfo} />
        </div>
      </div>
    </Container>
  );
}

export default MyEstimate;
