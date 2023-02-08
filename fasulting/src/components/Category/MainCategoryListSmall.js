import React, { useState } from "react";
import MainCategoryListItemSmall from "./MainCategoryListItemSmall";
import styles from "./MainCategoryListSmall.module.css";

function MainCategoryListSmall({mainCtg, setMainCtg}) {
  // const [select, setSelect] = useState(0);
  function onClick(text) {
    setMainCtg(text);
  }

  const categorylist = [
    {
      id: 1,
      src: "/assets/images/01_eye.png",
      text: "눈",
    },
    {
      id: 2,
      src: "/assets/images/02_nose.png",
      text: "코",
    },
    {
      id: 3,
      src: "/assets/images/03_petit.png",
      text: "쁘띠",
    },
    {
      id: 4,
      src: "/assets/images/04_facial.png",
      text: "안티에이징",
    },
    {
      id: 5,
      src: "/assets/images/05_antiaging.png",
      text: "안면윤곽",
    },
    {
      id: 6,
      src: "/assets/images/06_hair.png",
      text: "모발이식",
    },
    {
      id: 7,
      src: "/assets/images/07_man.png",
      text: "맨즈",
    },
    {
      id: 8,
      src: "/assets/images/08_revision.png",
      text: "재수술",
    },
  ];

  return (
    <div className={styles.flex}>
      {categorylist.map((c, index) => (
        <MainCategoryListItemSmall
          key={index}
          src={c.src}
          text={c.text}
          onClick={onClick}
          select={mainCtg}
        />
      ))}
    </div>
  );
}

export default MainCategoryListSmall;
