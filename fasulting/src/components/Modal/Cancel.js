import React from "react";
import styles from "./Cancel.module.css";

function Modal({ ModalStateChange, reservationSeq }) {
  const canselReservation = () => {
    // 취소 요청 api
    // reservationSeq랑 userSeq 리퀘스트
    console.log(reservationSeq);
    // 취소 후 페이지 새로고침
    window.location.reload();
  };
  return (
    <div className={styles.background}>
      <div className={styles.modalbox}>
        <div className={styles.mt}>
          <div className={styles.center}>
            <p>예약을</p>
            <p className={styles.cancel}>취소</p>
            <p>하시겠습니까?</p>
          </div>
          <div className={styles.center}>
            <button
              className={`${styles.no} ${styles.font} ${styles.center}`}
              onClick={ModalStateChange}
              value="no"
            >
              아니오
            </button>
            <button
              className={`${styles.yes} ${styles.font} ${styles.center}`}
              onClick={canselReservation}
              value="yes"
            >
              예
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
export default Modal;
