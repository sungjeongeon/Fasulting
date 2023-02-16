import React from "react";
import styles from "./BackgroundImage.module.css";

function BackgroundImage({ detailhospital }) {
  const imgRoot = "https://hotsix.s3.ap-northeast-2.amazonaws.com/null";
  const basicSrc = "/assets/images/profile.jpg";
  return (
    <div className={styles.backgroundImg}>
      <img
        className={styles.Img}
        src={
          detailhospital.psProfileImg === imgRoot
            ? basicSrc
            : detailhospital.psProfileImg
        }
        alt="logo"
      />
    </div>
  );
}

export default BackgroundImage;
