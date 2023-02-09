import React from "react";
import styles from "./ProfileImageUpdate.module.css";
import axiosAPi from "../../api/axiosApi";
import { useState } from "react";
import { useEffect } from "react";

function ProfileImageUpdate({ name, profileImg }) {
  // img 파일 객체 (서버에 보내줄 것)
  const [imgFile, setImgFile] = useState([])
  const setImg = (e) => {
    setImgFile(e.target.files[0])
  }

  // 병원 임시 id
  const psSeq = 1
  const hospitalProfile = async () => {
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
  }


  // 파일 업로드 요청 -> 서버
  // <label>에 onClick={fileUpload} 클릭이벤트
  // const fileUpload = (e) => {
  //   let info = new FormData();
  //   info.append("img-file", e.target.files[0]);
  // axios
  //   .post("http://127.0.0.1:8000/accounts/api/uploadimg/", info, {
  //     headers: {
  //       Authorization: `Token ${this.$store.state.token}`,
  //       "Content-Type": "multipart/form-data",
  //     },
  //   })
  //   .then((res) => {
  //     const src = res.data.src;
  //     // console.log(src);
  //     this.user.profile_img_src = "/media/" + src;
  //   });
  // };
  return (
    <div className={styles.outerDiv}>
      <div>
        <img src={profileImg === null ? null : profileImg} alt="프로필" className={styles.profileImg}/>
        <label htmlFor="hospital-profile" className={styles.label}>
          +
        </label>
        <input
          accept="image/*"
          type="file"
          id="hospital-profile"
          className={styles.input}
          onChange={() => {
            setImg();
            hospitalProfile();
          }}
        />
      </div>
      <p className={styles.name}>{name}</p>
    </div>
  );
}

export default ProfileImageUpdate;
