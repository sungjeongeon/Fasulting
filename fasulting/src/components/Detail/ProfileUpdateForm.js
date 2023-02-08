import React, { useState } from "react";
import Button from "@mui/material/Button";
import styles from "./ProfileUpdateForm.module.css";
import TextField from "@mui/material/TextField";
import axiosAPi from "../../api/axiosApi";

function ProfileUpdateForm({ title, content }) {
  const [isEditing, setIsEditing] = useState(false);
  const [newContent, setNewContent] = useState(content === null ? "" : content);
  
  // 임시 id
  const psSeq = 1

  const onClick = (e) => {
    setIsEditing((current) => !current);
    // isEditing === true 일 때만 api 요청 (내용 update)
    if (isEditing === true) {
      if (title === "주소") {
        axiosAPi.put('/ps/address', JSON.stringify({
            "seq": psSeq,
            "address": newContent,
        }))
        .then(res => console.log(res.data.message))
        .catch(err => console.log(err))

      } else if (title === "소개") {
        axiosAPi.put('/ps/intro', {
          "seq": psSeq,
          "intro": newContent,
        }, {
          headers: { "Content-Type": "application/json;charset=UTF-8" }
        }
        )
        .then(res => console.log(res.data.message))
        .catch(err => console.log(err))
      } else if (title === "연락처") {
          axiosAPi.put('/ps/number', {
            "seq": psSeq,
            "number": newContent
          })
          .then(res => console.log(res.data.message))
          .catch(err => console.log(err))
        } else if (title === "홈페이지") {
            axiosAPi.put('/ps/homepage', {
              "seq": psSeq,
              "homepage": newContent
            })
            .then(res => console.log(res.data.message))
            .catch(err => console.log(err))
          }
     }
  };

  // input 변경
  const changeInput = (e) => {
    setNewContent(e.target.value);
  };

  return (
    <div>
      <div className={styles.titleDiv}>
        <p className={styles.title}>{title}</p>
        <Button
          variant="text"
          className={styles.btn}
          color={isEditing ? "error" : "primary"}
          onClick={onClick}
        >
          <p className={isEditing ? styles.btnTextRed : styles.btnTextGreen}>
            {isEditing ? "저장" : "수정"}
          </p>
        </Button>
      </div>
      <div className={styles.content}>
        {isEditing ? (
          <TextField
            fullWidth
            onChange={changeInput}
            id="fullWidth"
            label=""
            value={newContent}
            variant="outlined"
            size="small"
            helperText={`변경된 ${title}를 입력해 주세요.`}
          />
        ) : (
          <p>{newContent}</p>
        )}
      </div>
      <hr className={styles.hr} />
    </div>
  );
}

export default ProfileUpdateForm;
