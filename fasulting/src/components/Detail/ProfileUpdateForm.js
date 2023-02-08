import React, { useState } from "react";
import Button from "@mui/material/Button";
import styles from "./ProfileUpdateForm.module.css";
import TextField from "@mui/material/TextField";
import axios from "axios";

function ProfileUpdateForm({ title, content }) {
  const [isEditing, setIsEditing] = useState(false);
  const [newContent, setNewContent] = useState();
  const onClick = (e) => {
    setIsEditing((current) => !current);
    console.log(newContent);
    // isEditing === true 일 때만 api 요청 (내용 update)
    // if (isEditing === true) {
    //   if (title === "주소") {
    //     axios.put('/ps/address', {
    //       // headers:{
    //       //   'Authorization': `Bearer ${token}`,
                  // "Content-Type": 'application/json'
    //       // },
    //       body: {
    //         "seq": psSeq,
    //         "address": newContent
    //       }
    //     }).then(res => {
    //       res.message === 200 ? console.log("success") : console.log("failed")
    //     })
    //   } else if (title === "소개말") {
    //       axios.put('/ps/intro', {
    //         // headers:{
    //         //   'Authorization': `Bearer ${token}`
    //         // },
    //         body: {
    //           "seq": psSeq,
    //           "intro": newContent
    //         }
    //       }).then(res => {
    //         res.message === 200 ? console.log("success") : console.log("failed")
    //       })
    //     } else if (title === "연락처") {
    //         axios.put('/ps/number', {
    //           // headers:{
    //           //   'Authorization': `Bearer ${token}`
    //           // },
    //           body: {
    //             "seq": psSeq,
    //             "number": newContent
    //           }
    //         }).then(res => {
    //           res.message === 200 ? console.log("success") : console.log("failed")
    //         })
    //       } else if (title === "홈페이지") {
    //           axios.put('/ps/homepage', {
    //             // headers:{
    //             //   'Authorization': `Bearer ${token}`
    //             // },
    //             body: {
    //               "seq": psSeq,
    //               "homepage": newContent
    //             }
    //           }).then(res => {
    //             res.message === 200 ? console.log("success") : console.log("failed")
    //           })
    //         }
    //  }
    
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
