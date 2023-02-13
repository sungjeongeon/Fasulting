import React from "react";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import DownloadIcon from "@mui/icons-material/Download";
import styles from "./DownloadCard.module.css";

function DownloadCard() {
  return (
    <div className={styles.outer}>
      <div className={styles.bg}>
        <img src="/assets/images/fegan.gif" className={styles.gif} />
      </div>
      <div className={styles.center}>
        <Typography variant="h4" align="center" gutterBottom>
          다운로드
        </Typography>
        <Button
          variant="contained"
          href="https://bit.ly/facesulting"
          target="_blank"
          sx={{ width: "300px", height: "50px" }}
          endIcon={<DownloadIcon size="large" />}
        >
          Download FE-GAN
        </Button>
      </div>
    </div>
  );
}

export default DownloadCard;
