import FavoriteCard from "./FavoriteCard";
import { Typography } from "@mui/material";
import { Box } from "@material-ui/core";
import { Stack } from "@mui/system";
function FavoriateCardList() {
  const favorite = [
    {
      ps_id: 1,
      ps_name: "아이디 병원",
      sub_category_name: "쌍커풀",
      total_rating_result: "3.5",
      review_total_count: 95,
    },
    {
      ps_id: 2,
      ps_name: "더페이스 병원",
      sub_category_name: "콧볼축소",
      total_rating_result: "4.0",
      review_total_count: 102,
    },
    {
      ps_id: 1,
      ps_name: "아이디 병원",
      sub_category_name: "쌍커풀",
      total_rating_result: "3.5",
      review_total_count: 95,
    },
    {
      ps_id: 2,
      ps_name: "더페이스 병원",
      sub_category_name: "콧볼축소",
      total_rating_result: "4.0",
      review_total_count: 102,
    },
  ];
  return (
    <>
      <Typography variant="h5">즐겨찾기 목록</Typography>
      <Stack direction="row" spacing={5}>
        {favorite.map((fav) => (
          <FavoriteCard key={fav.ps_id.toString()} fav={fav} />
        ))}
      </Stack>
    </>
  );
}
export default FavoriateCardList;
