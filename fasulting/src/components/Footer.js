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
      <div className={styles.flex}>
        <Box my={4} className={styles.box}>
          <Card className={styles.card}>
            <CardActionArea>
              <CardMedia
                className={styles.media}
                image="/assets/images/footer01.jpg"
                title="Contemplative Reptile"
              />
              <CardContent>
                <Typography gutterBottom variant="h5" component="h2">
                  상담 예약 전
                </Typography>
                <Typography variant="body2" color="textSecondary" component="p">
                  정확한 상담을 위해 <br />
                  이목구비가 다 나온 정면 사진을 첨부해주세요.
                  <br />
                  보다 더 정확한 상담을 받을 수 있습니다.
                </Typography>
              </CardContent>
            </CardActionArea>
          </Card>
        </Box>
        <Box my={4} className={styles.box}>
          <Card className={styles.card}>
            <CardActionArea>
              <CardMedia
                className={styles.media}
                image="/assets/images/footer02.jpg"
                title="Contemplative Reptile"
              />
              <CardContent>
                <Typography gutterBottom variant="h5" component="h2">
                  성형 관련
                </Typography>
                <Typography variant="body2" color="textSecondary" component="p">
                  과도한 욕심은 성형 중독이 됩니다. <br />
                  전문가와 충분한 상담 후 <br />
                  신중한 결정을 내려주세요.
                </Typography>
              </CardContent>
            </CardActionArea>
          </Card>
        </Box>
        <Box my={4} className={styles.box}>
          <Card className={styles.card}>
            <CardActionArea>
              <CardMedia
                className={styles.media}
                image="/assets/images/footer03.jpg"
                title="Contemplative Reptile"
              />
              <CardContent>
                <Typography gutterBottom variant="h5" component="h2">
                  상담 시간
                </Typography>
                <Typography variant="body2" color="textSecondary" component="p">
                  상담 예약은 환자와 병원 간의 <br />
                  약속입니다. <br />꼭 시간 약속을 잘 지켜주세요.
                </Typography>
              </CardContent>
            </CardActionArea>
          </Card>
        </Box>
      </div>
    </div>
  );
};

export default Footer;
