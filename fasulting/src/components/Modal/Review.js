import React, { useState } from "react";
import Rating from "@mui/material/Rating";
import Typography from "@mui/material/Typography";
import styles from "./Review.module.css";
import { useSelector } from "react-redux";
import axiosAPi from "../../api/axiosApi";

function Review({ ModalStateChange, consultingSeq }) {
  const userSeq = useSelector((store) => store.user.userSeq);

  const [value, setValue] = React.useState(0);
  const [content, setContent] = useState("");

  // 리뷰 내용 onChange
  const contentChanged = (e) => {
    setContent(e.target.value);
  };
  // 리뷰 제출
  const submitReview = () => {
    console.log(value);
    console.log(content);
    // axios요청
    axiosAPi
      .post("/review", {
        consultingSeq: consultingSeq,
        userSeq,
        reviewContent: content,
        point: value,
      })
      .then((res) => console.log(res))
      .catch((e) => console.log(e));
    // 모달 창 닫기 -> 페이지 새로고침
    window.location.reload();
  };
  return (
    <div className={styles.background}>
      <div className={styles.modalbox}>
        <div className={styles.mt}>
          <div className={`${styles.center} ${styles.twoline}`}>
            <Typography
              component="legend"
              sx={{ fontWeight: "bold", color: "#7C7877" }}
            >
              이 상담에 대한 리뷰를 남겨주세요.
            </Typography>
            <Rating
              name="no-value"
              value={value}
              precision={0.5}
              sx={{ marginTop: "0.5rem" }}
              onChange={(event, newValue) => {
                setValue(newValue);
              }}
            />
          </div>
          <div>
            <form className={styles.center}>
              <textarea
                placeholder="전문의의 태도 등 상담 내용에 대한 솔직한 리뷰를 남겨주세요.&#13;&#10;견적 비용을 공개하거나 근거 없는 악성 비방을 남길 경우 삭제될 수 있습니다.&#13;&#10;제출 후 리뷰는 수정 / 삭제할 수 없으니 신중하게 작성해주세요."
                className={`${styles.inputbox} ${styles.mt}`}
                style={{ padding: "0.5rem 1rem" }}
                value={content}
                onChange={contentChanged}
              ></textarea>
            </form>
            <div className={styles.center}>
              <button
                className={`${styles.no} ${styles.font} ${styles.center} ${styles.mt}`}
                onClick={ModalStateChange}
                value="no"
              >
                취소
              </button>
              <button
                className={`${styles.yes} ${styles.font} ${styles.center} ${styles.mt}`}
                onClick={submitReview}
                value="yes"
              >
                등록
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default Review;
