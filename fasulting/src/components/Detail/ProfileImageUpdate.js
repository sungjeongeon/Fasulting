import React from "react";
import styles from "./ProfileImageUpdate.module.css";

function ProfileImageUpdate({ name }) {
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
      <div className={styles.profileImg}>
        <label htmlFor="file" className={styles.label}>
          +
        </label>
        <input
          accept="image/*"
          type="file"
          id="file"
          className={styles.input}
        />
      </div>
      <p className={styles.name}>{name}</p>
    </div>
  );
}

export default ProfileImageUpdate;
