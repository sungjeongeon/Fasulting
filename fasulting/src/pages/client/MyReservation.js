import React from "react";
import ConsultingCardList from "../../components/Card/ConsultingCardList";
import LastConsulting from "../../components/Table/LastConsulting";
import { useEffect } from "react";
import axiosAPi from "../../api/axiosApi";
import { useState } from "react";
import { useSelector } from "react-redux";

function MyReservation() {
  // const [loading, setLoading] = useState(true);
  const [postConsult, setPostConsult] = useState([]);
  const [preConsult, setPreCounsult] = useState([]);
  const userSeq = useSelector((store) => store.user.userSeq);
  useEffect(() => {
    console.log(userSeq);
    axiosAPi
      .get(`/reservation/post/${userSeq}`)
      .then((res) => {
        setPostConsult(res.data.responseObj);
        // setLoading(false);
      })
      .catch((e) => console.log(e));
    axiosAPi.get(`/reservation/pre/${userSeq}`).then((res) => {
      setPreCounsult(res.data.responseObj);
      console.log(res.data.responseObj);
    });
  }, []);

  // if (loading) return <div>Loading...</div>;
  return (
    <>
      <ConsultingCardList consulting={postConsult} />
      <LastConsulting preConsult={preConsult} />
    </>
  );
}
export default MyReservation;
