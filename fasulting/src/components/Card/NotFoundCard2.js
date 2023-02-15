import React from "react";
import Typography from "@mui/material/Typography";
import styles from "./NotFoundCard.module.css";

function NotFoundCard2({ title }) {
  return (
    <div className={styles.outer}>
      <img src="/assets/images/notFoundCard2.png" className={styles.img2} alt="Not Found"></img>
      <div className={styles.content}>
        <Typography variant="h5" gutterBottom>
          {title} 없어요!
        </Typography>
      </div>
    </div>
  );
}

export default NotFoundCard2;
