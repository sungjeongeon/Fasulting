import React from "react";
import styles from "./Form.module.css";
import InputField from "./InputField";
import { Button } from "@mui/material";
import { useState } from "react";
import AddDoctor from "../Modal/AddDoctor";
import AddCategory from "../Modal/AddCategory";
import { Formik } from "formik";
import TagIcon from "@mui/icons-material/Tag";

export default function PsRegistForm03(props) {
  // 의사 등록 모달창
  // const [doctorModalOpen, setdoctorModalOpen] = useState(false);
  // const doctorModalStateChange = () =>
  //   setdoctorModalOpen((current) => !current);

  const {
    formField: {
      psdirector,
      psregistration,
      psregistrationimg,

      // doctor,
      // category,
    },
  } = props;

  //console.log(resimg);
  const ctg_list = []; //ctg_list 빈 배열
  return (
    <>
      <div className={styles.inputItem}>
        <div className={styles.labelFirst}>병원장</div>
        <InputField
          fullWidth
          placeholder="병원장 이름을 입력해주세요"
          name={psdirector.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 사업자 등록번호</div>
        <InputField
          fullWidth
          placeholder="병원 사업자 등록 번호를 입력해주세요"
          name={psregistration.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 사업자 등록증</div>
        <input
          fullWidth
          placeholder="병원 사업자 등록증을 업로드 해주세요"
          // onChange={(event) => {
          //   props.setFieldValue(
          //     props.psregistrationimg.name,
          //     event.currentTarget.files[0]
          //   );
          //   //props.setFieldValue(event.currentTarget.files[0]);
          // }}
          type="file"
          onChange={(event) => {
            props.setResimg(event.currentTarget.files[0]);
            //props.setFieldValue(event.currentTarget.files[0]);
          }}
          name={psregistrationimg.name}
        />
      </div>
      {/* <div className={styles.inputItem}>
        <div className={styles.label}>병원 의사</div>
        <p style={{ color: "gray" }}>병원 의사 정보를 추가해주세요.</p>
        <Button variant="text" onClick={doctorModalStateChange}>
          의사 정보 등록
        </Button>
        {doctorModalOpen && (
          <AddDoctor doctorModalStateChange={doctorModalStateChange} />
        )}
        <div></div>
      </div> */}
    </>
  );
}
