import React from "react";
import { useState } from "react";
import styles from "./BackgroundImage.module.css";

function BackgroundImage() {
  return (
    <div className={styles.backgroundImg}>
      <img className={styles.Img} src="/assets/images/profile.jpg" alt="logo" />
    </div>
  );
}

export default BackgroundImage;
