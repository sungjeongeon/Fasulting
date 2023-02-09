import React, { useState, useEffect } from "react";
import ReviewListItem from "../List/ReviewListItem";
import styles from "./ReviewInfo.module.css";
import Rating from "@mui/material/Rating";
import Button from "@mui/material/Button";
import { useLocation } from "react-router-dom";
import ReviewReport from "../Modal/ReviewReport";

function ReviewInfo({ detailhospital }) {
  // 별점 평균
  const totalScore = detailhospital.reviewTotalCount
    ? 0.0
    : detailhospital.reviewTotalCount;

  console.log("totalScore", totalScore);
  // 총 리뷰 개수
  const totalCount = 100;
  const tempdata = [
    {
      id: 1,
      name: "김싸피",
      content:
        "너무 친절하시고, 구체적으로 설명해주셔서 좋아요너무 친절하시고, 구체적으로 설명해주셔서 좋아요너무 친절하시고, 구체적으로 설명해주셔서 좋아요너무 친절하시고, 구체적으로 설명해주셔서 좋아요너무 친절하시고, 구체적으로 설명해주셔서 좋아요",
      date: "2023.01.06",
      hospital: "아이디병원",
      subcategory: ["안검하수", "눈매교정", "트임"],
      rating: 3.5,
    },
    {
      id: 2,
      name: "이싸피",
      content: "설명 잘해주시네요",
      date: "2023.01.17",
      hospital: "더성형병원",
      subcategory: ["콧등", "콧볼축소"],
      rating: 4.0,
    },
  ];
  // 신고 버튼 누르면 해당 review id 넘버 받아옴 (e.target.value)
  const reviewClaim = (e) => {
    console.log(e.target.value);
  };

  const isHospitalPage =
    useLocation().pathname.slice(1, 9) === "mypageho" ? true : false;

  // 모달
  const [ModalOpen, setModalOpen] = useState(false);
  const ModalStateChange = () => setModalOpen((current) => !current);

  return (
    <div>
      <p className={styles.title}>리뷰</p>
      <div className={styles.body}>
        {/* 리뷰 총점 box */}
        <div className={styles.oneLine}>
          {/* 평균 별점 (왼쪽) */}
          <p className={styles.score}>
            {detailhospital.totalRatingResult == null
              ? "0.0"
              : detailhospital.totalRatingResult.toFixed(1)}
          </p>
          {/* 별아이콘 + 리뷰 개수 (오른쪽) */}
          <div>
            {/* 별 5개 */}
            <div className={styles.star}>
              <Rating
                name="half-rating"
                defaultValue={totalScore}
                precision={0.1}
                size="large"
                readOnly
              />
            </div>
            <p className={styles.reviewCount}>
              {detailhospital.reviewTotalCount} 개의 리뷰
            </p>
          </div>
        </div>
        <hr className={styles.hr} />
        {!detailhospital.review
          ? "리뷰가 존재하지 않습니다."
          : detailhospital.review.map((review) => (
              <div key={review.id} className={styles.reviewList}>
                {isHospitalPage ? (
                  <div className={styles.claimBtn} onClick={reviewClaim}>
                    <Button
                      variant="text"
                      className={styles.btn}
                      color="error"
                      value={review.id}
                      onClick={ModalStateChange}
                    >
                      <p className={styles.btnTextRed}>신고</p>
                    </Button>
                    {ModalOpen && (
                      <ReviewReport ModalStateChange={ModalStateChange} />
                    )}
                  </div>
                ) : null}

                <ReviewListItem
                  key={detailhospital.review.reviewSeq}
                  psName={detailhospital.psName}
                  review={review}
                />
              </div>
            ))}
      </div>
    </div>
  );
}

export default ReviewInfo;
