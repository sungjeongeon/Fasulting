import React from "react";
import styles from "./SubCategoryList.module.css";
import SubCategoryListItem from "./SubCategoryListItem";
import { useParams } from "react-router-dom";
import { useState } from "react";
import { useEffect } from "react";
import axiosApi from "../../api/axiosApi";
function SubCategoryList() {
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

  const param = useParams();
  const [subcategory, setSubcategory] = useState([]);
  const [selectedSub, setSelectedSub] = useState([]);
  useEffect(() => {
    axiosApi.get(`/main/sub/${param.seq}`).then((res) => {
      //console.log(res.data);
      setSubcategory(res.data.responseObj);
      setSelectedSub([]); //선택된 배열 초기화
    });
  }, [param]);

  // 해당 sub값이 이미 배열에 있으면 빼고, 없으면 더한다.
  const selectSub = (sub) => {
    if (selectedSub.includes(sub)) {
      // sub값 이미 배열에 있으면 (제거){}
      setSelectedSub((current) => current.filter((resist) => resist !== sub));
    } else {
      setSelectedSub((current) => [...current, sub]);
    }
  };
  //zzconsole.log(selectedSub);

  return (
    <div className={styles.subCategoryList}>
      {subcategory.map((sub, index) => {
        return (
          // <SubCategoryListItem
          //   sub={sub.subName}
          //   key={index}
          //   onClick={() => selectSub(sub.subSeq)}
          // />
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
