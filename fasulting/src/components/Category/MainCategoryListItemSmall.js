import propTypes from "prop-types"
import React from "react";
import styles from "./MainCategoryListItemSmall.module.css"

function MainCategoryListItemSmall({src, text}) {
  return (
      <div className={styles.mainctg}>
        <img className={`${styles.center} ${styles.imgsmall}`} src={src} alt={text}/>
        <span className={styles.font}>{text}</span>
      </div>
  )
}

MainCategoryListItemSmall.propTypes = {
  src: propTypes.string.isRequired,
  text: propTypes.string.isRequired
}

export default MainCategoryListItemSmall;