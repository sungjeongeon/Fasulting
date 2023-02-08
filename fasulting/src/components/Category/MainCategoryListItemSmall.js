import propTypes from "prop-types"
import React, { useState } from "react";
import styles from "./MainCategoryListItemSmall.module.css"

function MainCategoryListItemSmall({src, text, onClick, select}) {

  return (
      <div className={ select === text ? styles.mainSelected : styles.mainctg}>
        <img className={styles.imgsmall} src={src} alt={text} onClick={() => onClick(text)}/>
        <span className={styles.font}>{text}</span>
      </div>
  )
}

MainCategoryListItemSmall.propTypes = {
  src: propTypes.string.isRequired,
  text: propTypes.string.isRequired,
  onClick: propTypes.func.isRequired,
  select: propTypes.string.isRequired
}

export default MainCategoryListItemSmall;