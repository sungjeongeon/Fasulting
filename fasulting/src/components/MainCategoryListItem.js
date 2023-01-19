import propTypes from "prop-types"
import React from "react";
import styles from "./MainCategoryListItem.module.css"

function MainCategoryListItem({src, text}) {
  return (
      <div className={styles.mainctg}>
        <img className={styles.center} src={src} alt={text}/>
        <span className={styles.center}>{text}</span>
      </div>
  )
}

MainCategoryListItem.propTypes = {
  src: propTypes.string.isRequired,
  text: propTypes.string.isRequired
}

export default MainCategoryListItem;