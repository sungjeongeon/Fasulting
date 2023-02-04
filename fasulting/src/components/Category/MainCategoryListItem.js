// import propTypes from "prop-types";
// import React from "react";
// import styles from "./MainCategoryListItem.module.css";
// import { useParams } from "react-router-dom";

// function MainCategoryListItem({ src, text, seq }) {
//   const param = useParams();
//   console.log("param", param.seq);
//   return (
//     <div
//       className={`${styles.mainctg} ${param.seq == seq ? styles.select : ""}`}
//     >
//       <img className={`${styles.center} ${styles.img}`} src={src} alt={text} />
//       <span className={styles.center}>{text}</span>
//     </div>
//   );
// }

// MainCategoryListItem.propTypes = {
//   src: propTypes.string.isRequired,
//   text: propTypes.string.isRequired,
// };

// export default MainCategoryListItem;
