import React, { useEffect, useState } from "react";
import styles from "./MainCategoryList.module.css";
import { Link } from "react-router-dom";
import { useParams } from "react-router-dom";
import axios from "axios";
import axiosAPi from "../../api/axiosApi";
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
    axiosAPi.get("/main").then((res) => {
      setMaincategory(res.data.responseObj);
    });
  }, []);
  const param = useParams();
  console.log(param.seq)

  return (
    <div className={styles.list}>
      {maincategory.map((main, index) => (
        <Link to={`/pslist/${main.mainSeq}`} key={index}>
          <div
            className={`${styles.mainctg} ${
              Number(param.seq) === main.mainSeq ? styles.select : ""
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
