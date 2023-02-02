import React from "react";
import { useState } from "react";
import styles from "./SubCategoryListItem.module.css";
import { useEffect } from "react";
function SubCategoryListItem({ sub }) {
  // 버튼 selected / unselected
  const [selectedSub, setSelectedSub] = useState([]);

  // 해당 sub값이 이미 배열에 있으면 빼고, 없으면 더한다.

  const selectSub = (sub) => {
    if (selectedSub.includes(sub)) {
      // sub값 이미 배열에 있으면 (제거)
      setSelectedSub((current) => current.filter((resist) => resist !== sub));
    } else {
      setSelectedSub((current) => [...current, sub]);
    }
  };
  console.log(selectedSub);

  return (
    <button
      className={
        selectedSub.includes(sub)
          ? styles.subCategorySelected
          : styles.subCategoryUnSelected
      }
      onClick={() => selectSub(sub)}
      value={sub}
    >
      {sub}
    </button>
  );
}

export default SubCategoryListItem;
