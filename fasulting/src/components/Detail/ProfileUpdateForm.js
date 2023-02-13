import React, { useState } from "react";
import Button from "@mui/material/Button";
import styles from "./ProfileUpdateForm.module.css";
import TextField from "@mui/material/TextField";
import axiosAPi from "../../api/axiosApi";

function ProfileUpdateForm({ title, content, psSeq }) {
  const [isEditing, setIsEditing] = useState(false);
  const [newContent, setNewContent] = useState(content === null ? "" : content);
  

  const onClick = (e) => {
    setIsEditing((current) => !current);
    // isEditing === true 일 때만 api 요청 (내용 update)
    if (isEditing === true) {
      if (title === "주소") {
        axiosAPi.put('/ps/address', {
            "seq": psSeq,
            "address": newContent,
        }, {
          headers: { "Content-Type": "application/json" }
        }
        )
        .then(res => console.log(res.data.message))
        .catch(err => console.log(err))

      } else if (title === "소개") {
        axiosAPi.put('/ps/intro', {
          "seq": psSeq,
          "intro": newContent,
        }, {
          headers: { "Content-Type": "application/json" }
        }
        )
        .then(res => console.log(res.data.message))
        .catch(err => console.log(err))
      } else if (title === "연락처") {
          axiosAPi.put('/ps/number', {
            "seq": psSeq,
            "number": newContent
          }, {
            headers: { "Content-Type": "application/json" }
          }
          )
          .then(res => console.log(res.data.message))
          .catch(err => console.log(err))
        } else if (title === "홈페이지") {
            axiosAPi.put('/ps/homepage', {
              "seq": psSeq,
              "homepage": newContent
            }, {
              headers: { "Content-Type": "application/json" }
            }
            )
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
          content ? <p>{newContent}</p> : <p>{title} 정보가 등록되지 않았습니다.</p>
        )}
      </div>
      <hr className={styles.hr} />
    </div>
  );
}

export default ProfileUpdateForm;
