import FavoriteCard from "./FavoriteCard";
import { Grid } from "@mui/material";
// import Box from "@mui/material/Box";
import styles from "./FavResCard.module.css";
function FavoriateCardList({ favorite }) {
  return (
    <div className={styles.margin}>
      <h2>즐겨찾기 목록</h2>
      <div className={styles.flex}>
        {favorite.map((fav) => (
          <FavoriteCard key={fav.psSeq.toString()} fav={fav} />
        ))}
      </div>
    </div>
  );
}
export default FavoriateCardList;
