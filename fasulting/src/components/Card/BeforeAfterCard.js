import React from "react";
import styles from "./BeforeAfterCard.module.css"

function BeforeAfterCard() {
  const before = "/assets/images/before.png"
  const after = "/assets/images/after.png"
  return (
    <div className={styles.flex}>
      <div className={styles.mx}>
        <img src={before} alt="before" className={styles.imgcard}/>
        <p className={styles.text}>before</p>
      </div>
      <div>
        <img src={after} alt="after" className={styles.imgcard}/>
        <p className={styles.text}>after</p>
      </div>
    </div>
  )
}

export default BeforeAfterCard