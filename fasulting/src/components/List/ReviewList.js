import React from "react";
import ReviewListItem from "./ReviewListItem";
import styles from "./ReviewList.module.css";
import NotFoundCard2 from "../Card/NotFoundCard2";
function ReviewList({ reviews }) {
  return (
    <div>
      <div>
        <h2 className={styles.title}>나의 리뷰</h2>
      </div>
      {reviews ? (
        reviews.map((review) => (
          <ReviewListItem key={review.reviewSeq} review={review} />
        ))
      ) : (
        <NotFoundCard2 title="아직 작성한 리뷰가" />
      )}
    </div>
  );
}

export default ReviewList;
