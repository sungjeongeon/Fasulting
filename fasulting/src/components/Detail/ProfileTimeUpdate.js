import React, { useState } from "react";
import styles from "./ProfileTimeUpdate.module.css";
import Button from "@mui/material/Button";
import SetOperatingTime from "../Modal/SetOperatingTime";

function ProfileTimeUpdate() {
  const [modal, setModal] = useState(false);
  const onClick = (e) => {
    setModal((current) => !current);
  };
  return (
    <div>
      <div className={styles.titleDiv}>
        <p className={styles.title}>운영 시간</p>
        <Button variant="text" className={styles.btn} onClick={onClick}>
          <p className={styles.btnTextGreen}>수정</p>
        </Button>
        {modal && <SetOperatingTime ModalStateChange={onClick} />}
      </div>
      <hr className={styles.hr} />
    </div>
  );
}

export default ProfileTimeUpdate;
