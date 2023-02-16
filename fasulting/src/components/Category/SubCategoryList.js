import React from "react";
import styles from "./SubCategoryList.module.css";
function SubCategoryList({ subcategory, selectedSub, selectSub }) {
  return (
    <div className={styles.subCategoryList}>
      <button className={styles.subCategory}>세부항목</button>
      {subcategory.map((sub, index) => {
        return (
          <button
            onClick={() => selectSub(sub.subName)}
            key={index}
            className={
              selectedSub.includes(sub.subName)
                ? styles.subCategorySelected
                : styles.subCategoryUnSelected
            }
            value={sub.subName}
          >
            {sub.subName}
          </button>
        );
      })}
    </div>
  );
}

export default SubCategoryList;
