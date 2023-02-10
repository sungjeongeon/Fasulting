import { useState } from "react";
import { useEffect } from "react";
import { useSelector } from "react-redux";
import axiosAPi from "../../api/axiosApi";
import FavoriteCardList from "../../components/Card/FavoriteCardList";
import ReviewList from "../../components/List/ReviewList";
function MyActivity() {
  const userSeq = useSelector((store) => store.user.userSeq);
  const [myLiked, setMyLiked] = useState([]);
  const [myReview, setMyReview] = useState([]);

  useEffect(() => {
    axiosAPi.get(`/favorite/${userSeq}`).then((res) => {
      setMyLiked(res.data.responseObj);
    });
    axiosAPi.get(`/review/${userSeq}`).then((res) => {
      setMyReview(res.data.responseObj);
      console.log(res);
    });
  }, []);
  return (
    <>
      <FavoriteCardList favorite={myLiked} />
      <ReviewList reviews={myReview} />
    </>
  );
}
export default MyActivity;
