import React, { useState } from "react";
import Button from "@mui/material/Button";
import styles from "./ProfileUpdateForm.module.css";
import TextField from "@mui/material/TextField";

function ProfileUpdateForm({ title, content }) {
  const [isEditing, setIsEditing] = useState(false);
  const [newContent, setNewContent] = useState(content);
  const onClick = (e) => {
    setIsEditing((current) => !current);
    console.log(newContent);
    // isEditing === true 일 때만 api 요청 (내용 update)
    //
  };
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
