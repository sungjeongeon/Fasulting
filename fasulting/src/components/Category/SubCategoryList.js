import React from "react";
import styles from "./SubCategoryList.module.css";
import SubCategoryListItem from "./SubCategoryListItem";
import { useParams } from "react-router-dom";
import { useState } from "react";
function SubCategoryList() {
  // 임시 data
  const subCategoryList = [
    {
      seq: 1, //useParam
      name: "쌍커풀",
    },
    {
      seq: 1,
      name: "트임시술",
    },
    {
      seq: 1,
      name: "눈밑지방",
    },
  ];

  const seq = useParams();
  console.log(seq);
  // {/main/sub/{mainCategorySeq}
  //   "ResponseBody" : {
  //     "statusCode" : "상태 코드",
  //     "message" : "success or fail",
  //     "ResponseObj" : [
  //       {
  //         "subSeq" : "서브 카테고리 시퀀스",
  //         "subName" : "서브 카테고리 이름"
  //       },
  //       {
  //         "subSeq" : "서브 카테고리 시퀀스",
  //         "subName" : "서브 카테고리 이름"
  //       }, ...
  //     ]
  //   }
  // }

  // 버튼 selected / unselected
  console.log("render");
  const [selectedSub, setSelectedSub] = useState([]);

  // 해당 sub값이 이미 배열에 있으면 빼고, 없으면 더한다.

  const selectSub = (sub) => {
    console.log(selectedSub);
    if (selectedSub.includes(sub)) {
      // sub값 이미 배열에 있으면 (제거){}
      setSelectedSub((current) => current.filter((resist) => resist !== sub));
      console.log("if");
    } else {
      setSelectedSub((current) => [...current, sub]);
      console.log("else");
    }
  };

  return (
    <div className={styles.subCategoryList}>
      {subCategoryList.map((sub, index) => {
        return (
          <SubCategoryListItem
            sub={sub.name}
            key={index}
            onClick={() => selectSub(sub.name)}
          />
        );
      })}
    </div>
  );
}

export default SubCategoryList;
