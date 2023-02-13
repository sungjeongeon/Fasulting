import React, { useState, useEffect } from "react";
import ReviewListItem from "../List/ReviewListItem";
import styles from "./ReviewInfo.module.css";
import Rating from "@mui/material/Rating";
import Button from "@mui/material/Button";
import Divider from "@mui/material/Divider";

import { useLocation } from "react-router-dom";
import ReviewReport from "../Modal/ReviewReport";

function ReviewInfo({ detailhospital }) {
  // 별점 평균
  const totalScore = detailhospital.totalRatingResult;

  // 신고 버튼 누르면 해당 review id 넘버 받아옴 (e.target.value)
  const [reviewId, setReviewId] = useState(0);
  const reviewClaim = (e) => {
    setReviewId(e.target.value);
  };

  const isHospitalPage =
    useLocation().pathname.slice(1, 9) === "mypageho" ? true : false;

  // 모달
  const [ModalOpen, setModalOpen] = useState(false);
  const ModalStateChange = (e) => {
    setModalOpen((current) => !current);
  };

  console.log(detailhospital);
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
                value={totalScore || ""}
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
        <Divider />
        <div>
          {!detailhospital.review
            ? "리뷰가 존재하지 않습니다."
            : detailhospital.review.map((review) => (
                <div key={review.reviewSeq} className={styles.reviewList}>
                  {isHospitalPage ? (
                    <div className={styles.claimBtn}>
                      <Button
                        variant="text"
                        className={styles.btn}
                        color="error"
                        value={review.reviewSeq}
                        onClick={(e) => {
                          ModalStateChange();
                          reviewClaim(e);
                        }}
                      >
                        <p className={styles.btnTextRed}>신고</p>
                      </Button>
                    </div>
                  ) : null}

                  <ReviewListItem
                    key={detailhospital.review.reviewSeq}
                    review={review}
                  />
                </div>
              ))}
          {ModalOpen && (
            <ReviewReport
              ModalStateChange={ModalStateChange}
              reviewId={reviewId}
              psSeq={detailhospital.psSeq}
            />
          )}
        </div>
      </div>
    </div>
  );
}

export default ReviewInfo;
