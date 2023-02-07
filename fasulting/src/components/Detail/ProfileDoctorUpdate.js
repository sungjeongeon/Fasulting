import React, { useState } from "react";
import styles from "./ProfileDoctorUpdate.module.css";
import Button from "@mui/material/Button";
import DoctorCard from "../Card/DoctorCard";
import CloseIcon from "@mui/icons-material/Close";
import AddDoctor from "../Modal/AddDoctor"
import DeleteDialog from "../Dialog/DeleteDialog";

function ProfileDoctorUpdate({ doctors }) {
  // 의사 등록 모달창
  const [ModalOpen, setModalOpen] = useState(false)
  const ModalStateChange = () => setModalOpen((current) => !current)

  // 의사 id값
  const [doctorId, setDoctorId] = useState(0)

  // 삭제 dialog 창
  const [dialogOpen, setDialogOpen] = useState(false)
  const DialogStateChange = ((e) => {
    setDialogOpen((current) => !current)
    dialogOpen ? setDoctorId(0) : setDoctorId(e.target.value) 
  })

  return (
    <div>
      <div className={styles.titleDiv}>
        <p className={styles.title}>의사 현황</p>
        <Button variant="text" className={styles.btn} onClick={ModalStateChange}>
          <p className={styles.btnTextGreen}>등록</p>
        </Button>
        {ModalOpen && 
        <AddDoctor 
          ModalStateChange={ModalStateChange}
        />}
      </div>
      {/* 의사 카드 리스트 */}
      <div className={styles.cardList}>
        {doctors.map((doc) => (
          <div key={doc.id}>
            <DoctorCard name={doc.name} main_category={doc.main_category} />
            <button
              value={doc.id}
              className={styles.deleteBtn}
              onClick={DialogStateChange}
            >
              <CloseIcon color="error" />
            </button>
          </div>
        ))}
        {dialogOpen && <DeleteDialog DialogStateChange={DialogStateChange} doctorId={doctorId}/>}
      </div>
      <hr />
    </div>
  );
}

export default ProfileDoctorUpdate;
