import React from "react";
import styles from "./ReviewReport.module.css";
import axiosAPi from "../../api/axiosApi";
import { toast, ToastContainer } from "react-toastify";

function ReviewReport({ ModalStateChange, reviewId, psSeq }) {
  // console.log(reviewId)
  const reviewReport = () => {
    axiosAPi
      .put("/ps-review/accuse", {
        reviewSeq: reviewId,
        psSeq: psSeq,
      })
      .then(() => {
        toast.success(<h3>신고가 완료되었습니다</h3>, {
          position: toast.POSITION.TOP_CENTER,
          autoClose: 500,
        });
        setTimeout(() => {
          ModalStateChange();
        }, 2000);
      })
      .catch((err) => console.log(err));
  };

  return (
    <div className={styles.background}>
      <ToastContainer />
      <div className={styles.modalbox}>
        <div className={styles.mt}>
          <div className={styles.center}>
            <p>리뷰를</p>
            <p className={styles.cancel}>신고</p>
            <p>하시겠습니까?</p>
          </div>
          <div className={styles.center}>
            <button
              className={`${styles.yes} ${styles.font} ${styles.center}`}
              onClick={reviewReport}
              value="yes"
            >
              신고
            </button>
            <button
              className={`${styles.no} ${styles.font} ${styles.center}`}
              onClick={ModalStateChange}
              value="no"
            >
              취소
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
export default ReviewReport;
