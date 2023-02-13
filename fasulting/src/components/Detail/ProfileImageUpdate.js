import React from "react";
import styles from "./ProfileImageUpdate.module.css";
import axiosAPi from "../../api/axiosApi";
import { useState } from "react";
import { useEffect } from "react";
import { useLocation } from "react-router-dom";

function ProfileImageUpdate({ name, profileImg, psSeq }) {
  const [nowProfile, setNowProfile] = useState(profileImg)
  const imgRoot = 'https://hotsix.s3.ap-northeast-2.amazonaws.com/null'
  const basicSrc = "/assets/images/psBasicProfile.png";

  const hospitalProfile = async (e) => {
    e.preventDefault()
    console.log(e.target.files[0])
    
    if (e.target.files) {
      const imgFile = e.target.files[0]
      const formData = new FormData();
      formData.append("seq", psSeq)
      formData.append("profileImg", imgFile);
      
      try {
        await axiosAPi.put("/ps/profile", formData, {
          headers: {
            // access_token 토큰 필요
            "Content-Type": "multipart/form-data"
          },
        })
      } catch (e) {
        console.log(e)
      }
      setNowProfile(URL.createObjectURL(imgFile))
      URL.revokeObjectURL(nowProfile)
    }
  }


  return (
    <div className={styles.outerDiv}>
      <div className={styles.profileDiv}>
        <img src={profileImg === imgRoot ? basicSrc : nowProfile} alt="프로필" className={styles.profileImg}/>
        <label htmlFor="hospital-profile" className={styles.label}>
          +
        </label>
        <input
          accept="image/*"
          type="file"
          id="hospital-profile"
          className={styles.input}
          onChange={hospitalProfile}
        />
      </div>
      <p className={styles.name}>{name}</p>
    </div>
  );
}

export default ProfileImageUpdate;
