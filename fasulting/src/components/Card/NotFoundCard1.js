import React from "react";
import Typography from "@mui/material/Typography";

import styles from "./NotFoundCard.module.css";
import { useNavigate } from "react-router-dom";

function NotFoundCard1({ title }) {
  const navigate = useNavigate();
  const movePslist = () => {
    navigate("/pslist/1");
  };
  return (
    <div className={styles.outer}>
      <img src="/assets/images/notFoundCard1.png" className={styles.img1} alt="NOT FOUND"></img>
      <div className={styles.content}>
        <Typography variant="h5" gutterBottom>
          {title} 없어요 :(
        </Typography>
        <Typography
          variant="subtitle2"
          color="primary"
          align="center"
          onClick={movePslist}
          gutterBottom
        >
          <span style={{ cursor: "pointer" }}>둘러보기</span>
        </Typography>
      </div>
    </div>
  );
}

export default NotFoundCard1;
