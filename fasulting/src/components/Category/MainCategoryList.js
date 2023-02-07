import React, { useEffect, useState } from "react";
import MainCategoryListItem from "./MainCategoryListItem";
import styles from "./MainCategoryList.module.css";
import { Link } from "react-router-dom";
import { useParams } from "react-router-dom";
import axios from "axios";
function MainCategoryList() {
  const img = [
    {
      src: "/assets/images/01_eye.png",
    },
    {
      src: "/assets/images/02_nose.png",
    },
    {
      src: "/assets/images/03_petit.png",
    },
    {
      src: "/assets/images/04_facial.png",
    },
    {
      src: "/assets/images/05_antiaging.png",
    },
    {
      src: "/assets/images/06_hair.png",
    },
    {
      src: "/assets/images/07_man.png",
    },
    {
      src: "/assets/images/08_revision.png",
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

  const [maincategory, setMaincategory] = useState([]);
  useEffect(() => {
    axios.get("/main").then((res) => {
      setMaincategory(res.data.responseObj);
    });
  }, []);
  const param = useParams();

  return (
    <div className={styles.list}>
      {maincategory.map((main, index) => (
        <Link to={`/pslist/${main.mainSeq}`} key={index}>
          {/* <MainCategoryListItem
            text={main.mainCategoryName}
            seq={main.mainCategorySeq}
            className={styles.listitem}
          />
          <div
      className={`${styles.mainctg} ${param.seq == seq ? styles.select : ""}`}
    > */}
          <div
            className={`${styles.mainctg} ${
              param.seq === main.mainSeq ? styles.select : ""
            }`}
          >
            <img
              className={`${styles.center} ${styles.img}`}
              alt={main.mainName}
              src={img[index].src}
            />
            <span className={styles.center}>{main.mainName}</span>
          </div>
        </Link>
      ))}
    </div>
  );
}

export default MainCategoryList;
