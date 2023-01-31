import React, { Component } from "react";
// import Slider from "react-slick"
import { useState } from "react";
import styles from "./Banner.module.css";
function Banner() {
  return (
    <div className={styles.margin}>
      <img
        className={styles.img}
        src="/assets/images/banner01.png"
        alt="배너1"
      />
    </div>
  );
}
export default Banner;
