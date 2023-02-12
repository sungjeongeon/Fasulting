import React, { useCallback, useEffect, useState } from "react";
import styles from "./ReserveCardCategoryItem.module.css";

function ReserveCardCategoryItem({ getConsultItem, categoryList }) {
  const imgList = [
    { id: 0 },
    {
      id: 1,
      src: "/assets/images/01_eye.png",
    },
    {
      id: 2,
      src: "/assets/images/02_nose.png",
    },
    {
      id: 3,
      src: "/assets/images/03_petit.png",
    },
    {
      id: 4,
      src: "/assets/images/04_facial.png",
    },
    {
      id: 5,
      src: "/assets/images/05_antiaging.png",
    },
    {
      id: 6,
      src: "/assets/images/06_hair.png",
    },
    {
      id: 7,
      src: "/assets/images/07_man.png",
    },
    {
      id: 8,
      src: "/assets/images/08_revision.png",
    },
  ];

  const [selectedMain, setSelectedMain] = useState(0);
  const [subCategory, setSubCategory] = useState([]);
  const [selectedSub, setSelectedSub] = useState([]);
  const [selectedSubName, setSelectedSubName] = useState([]);
  const [showWarning, setShowWarning] = useState(false);
  const selectMain = (id) => {
    setSelectedMain(id);
    setSubCategory(categoryList[id - 1].subCategoryList);
    setSelectedSub([]);
    setSelectedSubName([]);
  };

  // 해당 id 값이 이미 배열에 있으면 빼고, 없으면 더한다.
  const selectSub = (id, name) => {
    // 선택 3개 이상 경고문구 리셋(showWarning = false)
    setShowWarning(false);
    if (selectedSub.includes(id) && selectedSubName.includes(name)) {
      // id값 이미 배열에 있으면 (제거)
      setSelectedSub((current) => current.filter((resist) => resist !== id));
      setSelectedSubName((current) =>
        current.filter((resist) => resist !== name)
      );
    } else if (selectedSub.length === 3) {
      // 더했을 떄, 3개가 넘으면 경고문구 띄운다.
      setShowWarning(true);
    } else {
      setSelectedSub((current) => [...current, id]);
      setSelectedSubName((current) => [...current, name]);
    }
  };
  useEffect(() => {
    if (selectedSub.length === 0) {
    } else {
      const consultItem = {
        sub: selectedSub,
        subName: selectedSubName,
      };
      getConsultItem(consultItem);
    }
  }, [selectedSub]);

  return (
    <div>
      <div className={styles.outerDiv}>
        {categoryList &&
          categoryList.map((c) => (
            <div
              key={c.mainSeq}
              className={
                selectedMain === c.mainSeq ? styles.mainSelected : styles.main
              }
              onClick={() => selectMain(c.mainSeq)}
            >
              <img
                className={styles.img}
                src={imgList[c.mainSeq].src}
                alt={imgList[c.mainSeq].id}
              />
              <p className={styles.mainTitle}>{c.mainName}</p>
            </div>
          ))}
      </div>
      <hr />
      {subCategory.length > 0 ? (
        <>
          <p className={styles.subTitle}>상세항목 (최대 3개)</p>
          <div className={styles.outerDiv}>
            {subCategory.map((s) => (
              <div
                key={s.subSeq}
                className={
                  selectedSub.includes(s.subSeq)
                    ? styles.subSelected
                    : styles.sub
                }
                onClick={() => selectSub(s.subSeq, s.subName)}
              >
                <p>{s.subName}</p>
              </div>
            ))}
          </div>
        </>
      ) : (
        <p className={styles.subTitle}>가능한 수술항목이 없습니다.</p>
      )}
      {showWarning ? (
        <p className={styles.warning}>※ 최대 3개까지 선택해주세요.</p>
      ) : null}
    </div>
  );
}

export default ReserveCardCategoryItem;
