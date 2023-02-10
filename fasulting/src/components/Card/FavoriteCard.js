import * as React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import FavoriteIcon from "@mui/icons-material/Favorite";
import { Grid, IconButton } from "@mui/material";
import StarIcon from "@mui/icons-material/Star";
import styles from "./FavResCard.module.css";
import { useNavigate } from "react-router-dom";

function FavoriateCard({ fav }) {
  const navigate = useNavigate();
  const moveDetail = () => {
    navigate(`/detail/${fav.psSeq}`);
  };
  return (
    <div className={styles.column}>
      <div className={styles.card} onClick={moveDetail}>
        <div>
          <Typography variant="h6" component="div">
            {fav.psName}
          </Typography>
          <Typography sx={{ mb: 0.1 }} color="text.secondary">
            {fav.subCategoryName.join(" / ")}
          </Typography>
        </div>
        <div>
          <IconButton color="yellow" aria-label="favorite">
            <StarIcon />
            <Typography color="text.secondary">
              {fav.totalRatingResult} | 관련후기 {fav.reviewTotalCount}개
            </Typography>
          </IconButton>
          <IconButton color="error" aria-label="favorite">
            <FavoriteIcon />
          </IconButton>
        </div>
      </div>
    </div>
  );
}

export default FavoriateCard;
