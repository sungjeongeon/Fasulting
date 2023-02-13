import React from "react";
import { Box } from "@mui/material";
import { CardActionArea, CardMedia, Typography } from "@mui/material";
import { Card } from "@mui/material";
import { CardContent } from "@mui/material";
import styles from "./Footer.module.css";
const Footer = () => {
  return (
    <div className={styles.footer}>
      <h2>페이설팅 이용 전 안내사항</h2>
      <p className={styles.color}>아래 사항들을 유의해주세요.</p>
      <div className={styles.flex}>
        <Box my={4} className={styles.box}>
          <Card className={styles.card}>
            <div className={styles.hidden}>
              <CardMedia
                className={styles.media}
                image="/assets/images/footer01.jpg"
                title="Contemplative Reptile"
              />
            </div>
            <CardContent>
              <Typography gutterBottom variant="h5" component="h2">
                🧑 상담 예약 전
              </Typography>
              <Typography variant="body2" color="textSecondary" component="p">
                정확한 상담을 위해 <br />
                이목구비가 모두 나온 <strong>정면사진</strong>을 첨부해주세요.
                <br />
                보다 더 <strong>정확한</strong> 상담을 받을 수 있습니다.
              </Typography>
            </CardContent>
          </Card>
        </Box>
        <Box my={4} className={styles.box}>
          <Card className={styles.card}>
            <div className={styles.hidden}>
              <CardMedia
                className={styles.media}
                image="/assets/images/footer02.jpg"
                title="Contemplative Reptile"
              />
            </div>
            <CardContent>
              <Typography gutterBottom variant="h5" component="h2">
                💉 성형 관련
              </Typography>
              <Typography variant="body2" color="textSecondary" component="p">
                과도한 욕심은 <strong>성형 중독</strong>으로 가는 지름길입니다.
                <br />
                전문가와 충분한 상담 후 <br />
                <strong>신중한 결정</strong>을 내려주세요.
              </Typography>
            </CardContent>
          </Card>
        </Box>
        <Box my={4} className={styles.box}>
          <Card className={styles.card}>
            <div className={styles.hidden}>
              <CardMedia
                className={styles.media}
                image="/assets/images/footer03.jpg"
                title="Contemplative Reptile"
              />
            </div>
            <CardContent>
              <Typography gutterBottom variant="h5" component="h2">
                ⏰ 상담 시간
              </Typography>
              <Typography variant="body2" color="textSecondary" component="p">
                상담 예약은 환자와 병원 간의 약속입니다.
                <br />꼭 <strong>시간 약속</strong>을 잘 지켜주세요.
                <br />
                노쇼 발생 시 <strong>이용제한</strong>이 있을 수 있습니다.
              </Typography>
            </CardContent>
          </Card>
        </Box>
      </div>
    </div>
  );
};

export default Footer;
