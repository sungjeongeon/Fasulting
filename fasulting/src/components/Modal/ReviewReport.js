import React from 'react'
import styles from "./ReviewReport.module.css"

function ReviewReport({ModalStateChange5}) {
  return (
    <div className={styles.background}>
      <div className={styles.modalbox}>
        <div className={styles.mt}>
          <div className={styles.center}>
            <p>리뷰를</p>
            <p className={styles.cancel}>신고</p>
            <p>하시겠습니까?</p>
          </div>
          <div className={styles.center}>
            <button className={`${styles.yes} ${styles.font} ${styles.center}`} onClick={ModalStateChange5} value="yes">신고</button>
            <button className={`${styles.no} ${styles.font} ${styles.center}`} onClick={ModalStateChange5} value="no">취소</button>
          </div>
        </div>
      </div>
    </div>
  )
}
export default ReviewReport;