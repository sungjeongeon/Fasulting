import * as React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import FavoriteIcon from "@mui/icons-material/Favorite";
import { IconButton } from "@mui/material";
import StarIcon from "@mui/icons-material/Star";

function EstimateCard() {
  return (
    <Card sx={{ maxWidth: 275 }}>
      <CardContent>
        <Typography variant="h6" component="div">
          더성형외과의원
        </Typography>
        <Typography sx={{ mb: 0.1 }} color="text.secondary">
          상담부위
        </Typography>
        <Typography sx={{ mb: 0.1 }} color="text.secondary">
          상담부위
        </Typography>
        <Typography sx={{ mb: 0.1 }} color="text.secondary">
          상담부위
        </Typography>
        <Typography sx={{ mb: 0.1 }} color="text.secondary">
          상담부위
        </Typography>
      </CardContent>
    </Card>
  );
}

export default EstimateCard;
