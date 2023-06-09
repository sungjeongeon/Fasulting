import ConsultingCard from "./ConsultingCard";
import styles from "./FavResCard.module.css";
import NotFoundCard2 from "./NotFoundCard2";
function ConsultingCardList({ consulting }) {
  return (
    <div className={styles.margin}>
      <h2>진행중인 예약</h2>
      <div className={styles.flex}>
        {consulting.length !== 0 ? (
          consulting.map((consult) => (
            <ConsultingCard key={consult.psSeq.toString()} consult={consult} />
          ))
        ) : (
          <NotFoundCard2 title="진행중인 예약 내역이" />
        )}
      </div>
    </div>
  );
}
export default ConsultingCardList;
