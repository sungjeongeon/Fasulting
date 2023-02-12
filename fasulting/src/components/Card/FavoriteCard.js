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
import axiosAPi from "../../api/axiosApi";
import { useSelector } from "react-redux";

function FavoriateCard({ fav }) {
  const navigate = useNavigate();
  const userSeq = useSelector((state) => state.user.userSeq);
  const [liked, setLiked] = React.useState(true);

  const moveDetail = () => {
    navigate(`/detail/${fav.psSeq}`);
  };

  const disLike = (e) => {
    e.stopPropagation();
    if (!liked) {
      axiosAPi
        .post("/favorite", {
          userSeq: userSeq,
          psSeq: fav.psSeq,
        })
        .then((res) => console.log("좋아요", res))
        .catch((e) => console.log("좋아요", e));
    } else {
      axiosAPi
        .delete("/favorite", {
          data: {
            userSeq: userSeq,
            psSeq: fav.psSeq,
          },
        })
        .then((res) => console.log("좋아요취소", res))
        .catch((e) => console.log("좋아요취소", e));
    }
    setLiked((current) => !current);
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
            {liked ? (
              <FavoriteIcon
                fontSize="large"
                sx={{ color: "#e64c3c" }}
                onClick={disLike}
              />
            ) : (
              <FavoriteIcon
                fontSize="large"
                sx={{ color: "#d9d4cf" }}
                onClick={disLike}
              />
            )}
          </IconButton>
        </div>
      </div>
    </div>
  );
}

export default FavoriateCard;
