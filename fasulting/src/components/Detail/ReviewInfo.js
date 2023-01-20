import React from "react";
import ReviewListItem from "../List/ReviewListItem";
import styles from "./ReviewInfo.module.css";
import StarIcon from "@mui/icons-material/Star";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import StarHalfIcon from "@mui/icons-material/StarHalf";

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

  const displayStar = (idx) => {
    const value = totalScore - idx;
    if (value <= 0) {
      return <StarBorderIcon sx={{ fontSize: "30px", color: "#EECC51" }} />;
    } else if (value >= 1) {
      return <StarIcon sx={{ fontSize: "30px", color: "#EECC51" }} />;
    } else {
      return <StarHalfIcon sx={{ fontSize: "30px", color: "#EECC51" }} />;
    }
  };
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
              {displayStar(0)}
              {displayStar(1)}
              {displayStar(2)}
              {displayStar(3)}
              {displayStar(4)}
            </div>
            <p className={styles.reviewCount}>{totalCount} 개의 리뷰</p>
          </div>
        </div>
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
