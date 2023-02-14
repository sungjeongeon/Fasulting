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
        <Typography
          variant="h5"
          align="right"
          sx={{ fontWeight: 600 }}
          gutterBottom
        >
          원활한 상담을 위하여
        </Typography>
        <Typography
          variant="h5"
          align="right"
          sx={{ fontWeight: 600 }}
          gutterBottom
        >
          사전에 다운로드 해주세요
        </Typography>
        <Typography variant="h6" align="right" color="disabled2" gutterBottom>
          SC-FEGAN 다운로드
        </Typography>
        <Button
          variant="contained"
          href="https://drive.google.com/u/0/uc?id=1MTmfeQysjKt1dE9Dp_9kpT_5X4opx0Xz&export=download"
          target="_blank"
          sx={{ width: "100%", height: "50px" }}
          endIcon={<DownloadIcon size="large" />}
        >
          Download
        </Button>
      </div>
    </div>
  );
}

export default DownloadCard;
