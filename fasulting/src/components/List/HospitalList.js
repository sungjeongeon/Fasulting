import React from "react";
import HospitalListItem from "./HospitalListItem";
import styles from "./HospitalList.module.css";
import { useParams } from "react-router-dom";
import { useState } from "react";
import { useEffect } from "react";
import axios from "axios";
import { Category } from "@mui/icons-material";
import axiosAPi from "../../api/axiosApi";

function HospitalList({ selectedSub }) {
  const param = useParams();
  const [hospitalList, setHospitalList] = useState([]);
  useEffect(() => {
    //메인 선택시 병원
    axiosAPi.get(`/main/ps-list/${param.seq}`).then((res) => {
      //console.log(res.data);
      setHospitalList(res.data.responseObj);
    });
  }, [param]);
  return (
    <div>
      {hospitalList &&
        (!selectedSub.length
          ? hospitalList.map((hospital) => (
              <HospitalListItem key={hospital.psSeq} hospital={hospital} />
            ))
          : hospitalList.map(
              (hospital) =>
                hospital.subCategoryName.filter((sub) =>
                  selectedSub.includes(sub)
                ).length === selectedSub.length && (
                  <HospitalListItem key={hospital.psSeq} hospital={hospital} />
                )
            ))}
      {/* {hospitalList &&
        hospitalList
          .filter((hospital) =>
            hospital.subCategoryName.includes(["쌍꺼풀", "눈매교정"])
          )
          .map((hospital) => (
            <HospitalListItem key={hospital.psSeq} hospital={hospital} />
          ))} */}
    </div>
  );
}

export default HospitalList;
