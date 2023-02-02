import React from "react";
import MainCategoryListItem from "./MainCategoryListItem";
import styles from "./MainCategoryList.module.css";

function MainCategoryList() {
  const categorylist = [
    {
      src: "assets/images/1_eye.png",
      text: "눈",
    },
    {
      src: "assets/images/2_nose.png",
      text: "코",
    },
    {
      src: "assets/images/3_petit.png",
      text: "쁘띠",
    },
    {
      src: "assets/images/4_facial.png",
      text: "안티에이징",
    },
    {
      src: "assets/images/5_antiaging.png",
      text: "안면윤곽",
    },
    {
      src: "assets/images/6_hair.png",
      text: "모발이식",
    },
    {
      src: "assets/images/7_man.png",
      text: "맨즈",
    },
    {
      src: "assets/images/8_revision.png",
      text: "재수술",
    },
  ];
  return (
    <div className={styles.list}>
      {categorylist.map((c, index) => (
        <MainCategoryListItem key={index} src={c.src} text={c.text} />
      ))}
    </div>
  );
}

export default MainCategoryList;
