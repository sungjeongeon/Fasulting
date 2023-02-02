import React from "react";
import MainCategoryListItem from "./MainCategoryListItem";
import styles from "./MainCategoryList.module.css";
import { Link } from "react-router-dom";
import { useParams } from "react-router-dom";
function MainCategoryList() {
  const categorylist = [
    {
      src: "/assets/images/1_eye.png",
      seq: 1,
      text: "눈",
    },
    {
      src: "/assets/images/2_nose.png",
      seq: 2,
      text: "코",
    },
    {
      src: "/assets/images/3_petit.png",
      seq: 3,
      text: "쁘띠",
    },
    {
      src: "/assets/images/4_facial.png",
      seq: 4,
      text: "안티에이징",
    },
    {
      src: "/assets/images/5_antiaging.png",
      seq: 5,
      text: "안면윤곽",
    },
    {
      src: "/assets/images/6_hair.png",
      seq: 6,
      text: "모발이식",
    },
    {
      src: "/assets/images/7_man.png",
      seq: 7,
      text: "맨즈",
    },
    {
      src: "/assets/images/8_revision.png",
      seq: 8,
      text: "재수술",
    },

    // "ResponseBody" : {
    //   "statusCode" : "상태 코드",
    //   "message" : "success or fail",

    //   // 리스트
    //   "ResponseObj" : [
    //     {
    //       "mainCategorySeq" : "메인 카테고리 시퀀스",
    //       "mainCategoryName" : "메인 카테고리 이름"
    //     },
    //     {
    //       "mainCategorySeq" : "메인 카테고리 시퀀스",
    //       "mainCategoryName" : "메인 카테고리 이름"
    //     }, ...
    //   ]
    // }
  ];

  const param = useParams();
  return (
    <div className={styles.list}>
      {categorylist.map((c, index) => (
        <Link to={`/pslist/${c.seq}`} key={index}>
          <MainCategoryListItem
            src={c.src}
            text={c.text}
            seq={c.seq}
            className={styles.listitem}
          />
        </Link>
      ))}
    </div>
  );
}

export default MainCategoryList;
