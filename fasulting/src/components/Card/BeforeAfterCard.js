import React from "react";
import styles from "./BeforeAfterCard.module.css"
import { useSelector } from "react-redux";

function BeforeAfterCard() {
  const client = useSelector(state => {
    return state.lastReservationHo
  })
  // const before = "/assets/images/before.png"
  const before = client.beforeImg
  // const after = "/assets/images/after.png"
  const after = client.afterImg

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