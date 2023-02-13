import React from "react";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import DownloadIcon from "@mui/icons-material/Download";
import styles from "./DownloadCard.module.css";

function DownloadCard() {
  return (
    <div className={styles.outer}>
      <img src="/assets/images/fegan.gif" className={styles.gif} />
      <div className={styles.center}>
        <Typography variant="h5" align="center" gutterBottom>
          SC-FEGAN 다운로드
        </Typography>
        <Button
          variant="contained"
          href="https://drive.google.com/u/0/uc?id=1MTmfeQysjKt1dE9Dp_9kpT_5X4opx0Xz&export=download"
          target="_blank"
          sx={{ width: "200px", height: "50px" }}
          endIcon={<DownloadIcon size="large" />}
        >
          Download
        </Button>
      </div>
    </div>
  );
}

export default DownloadCard;
