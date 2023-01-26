import React, { useEffect, useState } from "react";
import styles from "./ReserveCardCategoryItem.module.css";

function ReserveCardCategoryItem({ getConsultItem }) {
  const categoryList = [
    { id: 0, sub: [] },
    {
      id: 1,
      src: "/assets/images/1_eye.png",
      text: "눈",
      sub: [
        { id: 1, name: "쌍커풀" },
        { id: 2, name: "눈매교정" },
        { id: 3, name: "안검하수" },
        { id: 4, name: "트임성형" },
        { id: 5, name: "눈썹거상" },
        { id: 6, name: "지방제거" },
        { id: 7, name: "지방재배치" },
      ],
    },
    {
      id: 2,
      src: "/assets/images/2_nose.png",
      text: "코",
      sub: [
        { id: 8, name: "콧대성형" },
        { id: 9, name: "콧등성형" },
        { id: 10, name: "코끝성형" },
        { id: 11, name: "비중격수술" },
        { id: 12, name: "콧볼축소" },
        { id: 13, name: "이물질제거" },
      ],
    },
    {
      id: 3,
      src: "/assets/images/3_petit.png",
      text: "쁘띠",
    },
    {
      id: 4,
      src: "/assets/images/4_facial.png",
      text: "안티에이징",
    },
    {
      id: 5,
      src: "/assets/images/5_antiaging.png",
      text: "안면윤곽",
    },
    {
      id: 6,
      src: "/assets/images/6_hair.png",
      text: "모발이식",
    },
    {
      id: 7,
      src: "/assets/images/7_man.png",
      text: "맨즈",
    },
    {
      id: 8,
      src: "/assets/images/8_revision.png",
      text: "재수술",
    },
  ];

  const [selectedMain, setSelectedMain] = useState(0);
  const [subCategory, setSubCategory] = useState([]);
  const [selectedSub, setSelectedSub] = useState([]);
  const [showWarning, setShowWarning] = useState(false);

  const selectMain = (id) => {
    setSelectedMain(id);
    setSubCategory(categoryList[id].sub);
    setSelectedSub([]);
  };

  // 해당 id 값이 이미 배열에 있으면 빼고, 없으면 더한다.
  const selectSub = (id) => {
    // 선택 3개 이상 경고문구 리셋(showWarning = false)
    setShowWarning(false);
    if (selectedSub.includes(id)) {
      // id값 이미 배열에 있으면 (제거)
      setSelectedSub((current) => current.filter((resist) => resist !== id));
    } else if (selectedSub.length === 3) {
      // 더했을 떄, 3개가 넘으면 경고문구 띄운다.
      setShowWarning(true);
    } else {
      setSelectedSub((current) => [...current, id]);
    }
  };

  useEffect(() => {
    const consultItem = {
      main: selectedMain,
      sub: selectedSub,
    };
    getConsultItem(consultItem);
  }, [selectedMain, selectedSub]);

  return (
    <div>
      <div className={styles.outerDiv}>
        {categoryList.slice(1).map((c) => (
          <div
            key={c.id}
            className={
              selectedMain === c.id ? styles.mainSelected : styles.main
            }
            onClick={() => selectMain(c.id)}
          >
            <img className={styles.img} src={c.src} alt={c.text} />
            <p className={styles.mainTitle}>{c.text}</p>
          </div>
        ))}
      </div>
      {subCategory.length > 0 ? <hr /> : null}
      {subCategory.length > 0 ? (
        <p className={styles.subTitle}>상세항목 (최대 3개)</p>
      ) : null}
      {showWarning ? (
        <p className={styles.warning}>※ 최대 3개까지 선택해주세요.</p>
      ) : null}
      <div className={styles.outerDiv}>
        {subCategory.map((s) => (
          <div
            key={s.id}
            className={
              selectedSub.includes(s.id) ? styles.subSelected : styles.sub
            }
            onClick={() => selectSub(s.id)}
          >
            <p>{s.name}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ReserveCardCategoryItem;
