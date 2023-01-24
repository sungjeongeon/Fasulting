import React from "react";
import ReviewListItem from "../List/ReviewListItem";
import styles from "./ReviewInfo.module.css";
import StarIcon from "@mui/icons-material/Star";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import StarHalfIcon from "@mui/icons-material/StarHalf";
import Rating from "@mui/material/Rating";

function ReviewInfo() {
  // 별점 평균
  const totalScore = 3.5;
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

  return (
    <div>
      <p className={styles.title}>리뷰</p>
      <div className={styles.body}>
        {/* 리뷰 총점 box */}
        <div className={styles.oneLine}>
          {/* 평균 별점 (왼쪽) */}
          <p className={styles.score}>{totalScore}</p>
          {/* 별아이콘 + 리뷰 개수 (오른쪽) */}
          <div>
            {/* 별 5개 */}
            <div className={styles.star}>
              <Rating
                name="half-rating"
                defaultValue={totalScore}
                precision={0.5}
                size="large"
                readOnly
              />
            </div>
            <p className={styles.reviewCount}>{totalCount} 개의 리뷰</p>
          </div>
        </div>
        <hr />
        {tempdata.map((review) => (
          <ReviewListItem
            key={review.id}
            username={review.name}
            content={review.content}
            date={review.date}
            hospital={review.hospital}
            subcategory={review.subcategory}
            rating={review.rating}
          />
        ))}
      </div>
    </div>
  );
}

export default ReviewInfo;
