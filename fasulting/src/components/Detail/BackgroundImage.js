import React from "react";
import { useState } from "react";
import styles from "./BackgroundImage.module.css";

function BackgroundImage({ detailhospital }) {
  const basicSrc = "/assets/images/profile.jpg";
  return (
    <div className={styles.backgroundImg}>
      <img
        className={styles.Img}
        src={detailhospital.psProfileImg}
        alt="logo"
      />
    </div>
  );
}

export default BackgroundImage;
