import * as React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import FavoriteIcon from '@mui/icons-material/Favorite';
import { IconButton } from "@mui/material";
import StarIcon from '@mui/icons-material/Star';

function FavoriateCard({fav}) {
  return (
    <Card sx={{ maxWidth: 275 }}>
      <CardContent>
        <Typography variant="h6" component="div">
            {fav.ps_name} 
        </Typography>
        <Typography sx={{ mb: 0.1 }} color="text.secondary">
            {fav.sub_category_name} 
        </Typography>
      </CardContent>
      <CardActions>
        <IconButton color="yellow" aria-label="favorite">
            <StarIcon />
            <Typography color="text.secondary">
                {fav.total_rating_result} | 관련후기 {fav.review_total_count}개
            </Typography>
        </IconButton>
        <IconButton color="error" aria-label="favorite">
            <FavoriteIcon />
        </IconButton>
      </CardActions>
    </Card>
  );
}

export default FavoriateCard;