import React, { useState } from "react";
import styles from "./ProfileSubCategoryUpdate.module.css";
import TagIcon from "@mui/icons-material/Tag";
import Button from "@mui/material/Button";
import AddCategory from "../Modal/AddCategory";

function ProfileSubCategoryUpdate({ ctg_list, psSeq }) {
  // useState로 재렌더링 수정해보기
  const [updateCtg, setUpdateCtg] = useState(ctg_list)


  const [modal, setModal] = useState(false);
  const onClick = (e) => {
    setModal((current) => !current);
  };
  // modal 작업 끝내고 modal 내 완료 버튼 누르면 현재 component에서 setModel -> false 처리
  // modal에서 영역 밖 눌렀을 때 빠져나오려면 setModal -> false ?? 생각해보기

  
  return (
    <div>
      <div className={styles.titleDiv}>
        <p className={styles.title}>제공 수술</p>
        <Button variant="text" className={styles.btn} onClick={onClick}>
          <p className={styles.btnTextGreen}>수정</p>
        </Button>
        {modal && 
        <AddCategory 
          ModalStateChange={onClick}
          ctg_list={updateCtg}
          setUpdateCtg={setUpdateCtg}
          psSeq={psSeq}
        />}
      </div>
      { updateCtg.length !== 0 ? 
        <div className={styles.subDiv}>
          {updateCtg.map((sub, index) => {
            return (
              <button key={index} className={styles.subCategory}>
                <TagIcon sx={{ fontSize: 12 }} /> {sub}
              </button>
            )
          })}
        </div> :
        <div className={styles.content}>
          <p>제공 수술을 등록해주시길 바랍니다.</p>
        </div>
      }
      <hr className={styles.hr} />
    </div>
  );
}

export default ProfileSubCategoryUpdate;
