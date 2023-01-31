import FavoriteCard from "./FavoriteCard";
import { Grid, Typography } from "@mui/material";
// import Box from "@mui/material/Box";
import { Stack } from "@mui/system";
import styles from "./FavResCard.module.css";
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
      <h2>즐겨찾기 목록</h2>
      <Grid container className={styles.flexSpace}>
        {favorite.map((fav) => (
          <FavoriteCard key={fav.ps_id.toString()} fav={fav} />
        ))}
      </Grid>
    </>
  );
}
export default FavoriateCardList;
