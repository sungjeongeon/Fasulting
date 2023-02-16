import * as React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import styles from "./EstimateCard.module.css";

function EstimateCard({ report }) {
  return (
    <Card
      sx={{ marginLeft: "4rem", marginTop: "2.5rem", marginBottom: "3rem" }}
    >
      <CardContent sx={{ width: "92%", margin: "auto" }}>
        <Typography
          variant="h5"
          component="div"
          fontWeight={"bold"}
          sx={{ marginTop: "0.7rem" }}
        >
          {report.psName}
        </Typography>
        <Typography
          color="text.secondary"
          fontWeight={"bold"}
          sx={{ marginTop: "2rem" }}
          fontSize={"1.1rem"}
        >
          상담 부위
        </Typography>
        <p>{report.subCategoryName.join(", ")}</p>
        <Typography
          color="text.secondary"
          fontWeight={"bold"}
          sx={{ marginTop: "2rem" }}
          fontSize={"1.1rem"}
        >
          상담 소견
        </Typography>
        <p className={`${styles.opinion} ${styles.my}`}>{report.content}</p>
        <Typography
          color="text.secondary"
          fontWeight={"bold"}
          sx={{ marginTop: "2rem" }}
          fontSize={"1.1rem"}
        >
          예상 견적
        </Typography>
        <p className={`${styles.price} ${styles.my}`}>{report.estimate}만원</p>
      </CardContent>
    </Card>
  );
}

export default EstimateCard;
