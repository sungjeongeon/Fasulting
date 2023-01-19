import React from "react";
import styles from "./SubCategoryList.module.css";
import SubCategoryListItem from "./SubCategoryListItem";

function SubCategoryList() {
  // 임시 data
  const subCategoryList = [
    "쌍커풀",
    "트임시술",
    "눈밑지방",
    "안검하수",
    "눈매교정",
  ];
  return (
    <div className={styles.subCategoryList}>
      {subCategoryList.map((sub, index) => {
        return <SubCategoryListItem sub={sub} key={index} />;
      })}
    </div>
  );
}

export default SubCategoryList;
