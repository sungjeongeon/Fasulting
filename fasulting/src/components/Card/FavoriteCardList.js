import FavoriteCard from "./FavoriteCard";
import styles from "./FavResCard.module.css";
import NotFoundCard1 from "./NotFoundCard1";
function FavoriateCardList({ favorite }) {
  return (
    <div className={styles.margin}>
      <h2>즐겨찾기 목록</h2>
      <div className={styles.flex}>
        {favorite ? (
          favorite.map((fav) => (
            <FavoriteCard key={fav.psSeq.toString()} fav={fav} />
          ))
        ) : (
          <NotFoundCard1 title="즐겨찾기 한 병원이" />
        )}
      </div>
    </div>
  );
}
export default FavoriateCardList;
