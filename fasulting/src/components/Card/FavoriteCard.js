import * as React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import FavoriteIcon from "@mui/icons-material/Favorite";
import { Grid, IconButton } from "@mui/material";
import Chip from "@mui/material/Chip";
import StarIcon from "@mui/icons-material/Star";
import styles from "./FavResCard.module.css";
import { useNavigate } from "react-router-dom";
import axiosAPi from "../../api/axiosApi";
import { useSelector } from "react-redux";
import Link from "@mui/material/Link";

function FavoriateCard({ fav }) {
  const navigate = useNavigate();
  const userSeq = useSelector((state) => state.user.userSeq);
  const [subVisible, setSubVisible] = React.useState(
    fav.subCategoryName.length > 2
  );
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

  const subVisibleChanged = (e) => {
    e.stopPropagation();
    setSubVisible((current) => !current);
  };
  return (
    <div className={styles.column}>
      <div className={styles.card} onClick={moveDetail}>
        <div>
          <Typography variant="h6" component="div" sx={{ mt: 1, mb: 1 }}>
            {fav.psName}
          </Typography>
          {subVisible ? (
            <>
              {fav.subCategoryName.slice(0, 2).map((s) => (
                <Chip
                  label={s}
                  sx={{ mx: 0.2, mt: 0.2 }}
                  size="small"
                  color="primary"
                />
              ))}
              <Chip
                label="...더보기"
                variant="outlined"
                sx={{ mx: 0.2, mt: 0.2 }}
                size="small"
                color="primary"
                onClick={subVisibleChanged}
              />
            </>
          ) : (
            <>
              {fav.subCategoryName.map((s) => (
                <Chip
                  label={s}
                  sx={{ mx: 0.2, mt: 0.2 }}
                  size="small"
                  color="primary"
                />
              ))}
              {fav.subCategoryName.length > 2 ? (
                <Chip
                  label="접기"
                  variant="outlined"
                  color="primary"
                  size="small"
                  sx={{ mx: 0.2, mt: 0.2 }}
                  onClick={subVisibleChanged}
                />
              ) : null}
            </>
          )}
        </div>
        <div>
          <Link href={`/detail/${fav.psSeq}#review`}>
            <Typography>
              <IconButton color="yellow" aria-label="favorite">
                <StarIcon />
                <Typography color="text.secondary">
                  {fav.totalRatingResult} | 관련후기 {fav.reviewTotalCount}개
                </Typography>
              </IconButton>
            </Typography>
          </Link>
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
