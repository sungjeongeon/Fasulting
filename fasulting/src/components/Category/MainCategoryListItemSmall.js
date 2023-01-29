import propTypes from "prop-types"
import React, { useState } from "react";
import styles from "./MainCategoryListItemSmall.module.css"

function MainCategoryListItemSmall({id, src, text, onClick, select}) {
  function clickItem() {
    return (
      onClick(id)
    )
  }
  
  return (
      <div className={ select === id ? styles.mainSelected : styles.mainctg}>
        <img className={styles.imgsmall} src={src} alt={text} onClick={clickItem}/>
        <span className={styles.font}>{text}</span>
      </div>
  )
}

MainCategoryListItemSmall.propTypes = {
  id: propTypes.number.isRequired,
  src: propTypes.string.isRequired,
  text: propTypes.string.isRequired,
  onClick: propTypes.func.isRequired,
  select: propTypes.number.isRequired
}

export default MainCategoryListItemSmall;