import React from "react";
import ReviewListItem from "./ReviewListItem";
import styles from "./ReviewList.module.css";
function ReviewList() {
  // 임시 데이터 (아이디, 리뷰 내용, 날짜, 병원, 세부 카테고리, 별점)
  const tempdata = [
    {
      id: 1,
      name: "김싸피",
      content:
        "너무 친절하시고, 구체적으로 설명해주셔서 좋아요너무 친절하시고, 구체적으로 설명해주셔서 좋아요너무 친절하시고, 구체적으로 설명해주셔서 좋아요너무 친절하시고, 구체적으로 설명해주셔서 좋아요너무 친절하시고, 구체적으로 설명해주셔서 좋아요",
      date: "2023.01.06",
      hospital: "아이디병원",
      subcategory: ["안검하수", "눈매교정", "트임"],
      rating: 5.0,
    },
    {
      id: 2,
      name: "이싸피",
      content: "설명 잘해주시네요",
      date: "2023.01.17",
      hospital: "더성형병원",
      subcategory: ["콧등", "콧볼축소"],
      rating: 5.0,
    },
  ];
  return (
    <div>
      <div>
        <h2 className={styles.title}>최신 리뷰</h2>
        {/* <a href="#">전체 보기 ⇁</a> */}
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
  );
}

export default ReviewList;
