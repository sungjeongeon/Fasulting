import React, { useState } from "react";
import styles from "./ProfileDoctorUpdate.module.css";
import Button from "@mui/material/Button";
import DoctorCard from "../Card/DoctorCard";
import CloseIcon from "@mui/icons-material/Close";

function ProfileDoctorUpdate({ doctors }) {
  const [modal, setModal] = useState(false);
  const onClick = (e) => {
    setModal((current) => !current);
  };
  const deleteDoc = (e) => {
    // X 클릭하면 해당 의사 id 값 출력
    console.log(e.target.value);
  };
  return (
    <div>
      <div className={styles.titleDiv}>
        <p className={styles.title}>의사 현황</p>
        <Button variant="text" className={styles.btn} onClick={onClick}>
          <p className={styles.btnTextGreen}>등록</p>
        </Button>
      </div>
      {/* 의사 카드 리스트 */}
      <div className={styles.cardList}>
        {doctors.map((doc) => (
          <div key={doc.id}>
            <DoctorCard name={doc.name} main_category={doc.main_category} />
            <button
              value={doc.id}
              className={styles.deleteBtn}
              onClick={deleteDoc}
            >
              <CloseIcon color="error" />
            </button>
          </div>
        ))}
      </div>
      <hr />
    </div>
  );
}

export default ProfileDoctorUpdate;
