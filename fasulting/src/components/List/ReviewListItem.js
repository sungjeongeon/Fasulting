import React from "react";
import propTypes from "prop-types"
import styles from "./ReviewListItem.module.css"

function ReviewListItem({userid, content, date, hospital, subcategory, rating}) {
  return (
    <div className={styles.pl}>
      <div className={`${styles.pl} ${styles.whitespace_h}`}>
        <div className={styles.bold}>
          {userid.substr(0,4)+'****'}
        </div>
        <div className={styles.flex}>
          <span className={styles.mr2}>★{rating.toFixed(1)}</span>
          <div className={`${styles.color} ${styles.bold}`}>
            <span className={styles.mr}>{hospital}</span>
            <span className={styles.mr}>|</span>
            {subcategory.map((sub, index) => (
              <span className={styles.mr} key={index}>{sub} </span>
              ))}
          </div>
        </div>
        <div className={styles.contentbox}>
          {content}
        </div>
        <div>
          {date}
        </div>
      </div>
      <div><hr/></div>
    </div>
  )
}

ReviewListItem.propTypes = {
  userid: propTypes.string.isRequired,
  content: propTypes.string.isRequired,
  date: propTypes.string.isRequired,
  // date: propTypes.instanceOf(Date).isRequired,
  hospital: propTypes.string.isRequired,
  subcategory: propTypes.arrayOf(propTypes.string.isRequired),
  rating: propTypes.number.isRequired
}

export default ReviewListItem