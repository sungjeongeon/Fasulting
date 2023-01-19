import React from "react";
import { useState } from "react";
import styles from "./SubCategoryListItem.module.css";

function SubCategoryListItem({ sub }) {
  // 버튼 selected / unselected
  const [selected, setSelected] = useState(false);
  const onClick = (e) => {
    setSelected((current) => !current);
    console.log(e.target.value);
  };
  return (
    <button
      className={
        selected ? styles.subCategorySelected : styles.subCategoryUnSelected
      }
      onClick={onClick}
      value={sub}
    >
      {sub}
    </button>
  );
}

export default SubCategoryListItem;
