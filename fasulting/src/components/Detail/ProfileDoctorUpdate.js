import React, { useState } from "react";
import styles from "./ProfileDoctorUpdate.module.css";
import Button from "@mui/material/Button";
import DoctorCard from "../Card/DoctorCard";
import CloseIcon from "@mui/icons-material/Close";
import AddDoctor from "../Modal/AddDoctor"
import DeleteDialog from "../Dialog/DeleteDialog";

function ProfileDoctorUpdate({ doctors, psSeq }) {
  const [doctorList, setDoctorList] = useState(doctors)

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
          doctorList={doctorList}
          setDoctorList={setDoctorList}
          psSeq={psSeq}
        />}
      </div>
      { doctorList.length !== 0 ?
        <div className={styles.cardList}>
          {/* 의사 카드 리스트 */}
          {doctorList.map((doc, index) => (
            <div key={index}>
              <DoctorCard name={doc.name} main_category={doc.mainCategoryName} profileImg={doc.profileImg}/>
              <button
                value={doc.doctorSeq}
                className={styles.deleteBtn}
                onClick={DialogStateChange}
              >
                <CloseIcon color="error" />
              </button>
            </div>
          ))}
          { dialogOpen && 
            <DeleteDialog
              DialogStateChange={DialogStateChange}
              doctorId={doctorId}
              doctorList={doctorList}
              setDoctorList={setDoctorList}
              psSeq={psSeq}
            />}
        </div> :
        <div className={styles.content}>
          <p>의사 현황이 존재하지 않습니다.</p>
        </div>
      }
      <hr />
    </div>
  );
}

export default ProfileDoctorUpdate;
