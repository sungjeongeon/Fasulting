import React from "react";
import ConsultingCardList from "../../components/Card/ConsultingCardList";
import LastConsulting from "../../components/Table/LastConsulting";
import { useEffect } from "react";
import axiosAPi from "../../api/axiosApi";
import { useState } from "react";

function MyReservation() {
  // const [loading, setLoading] = useState(true);
  const [consulting, setConsulting] = useState([]);
  useEffect(() => {
    axiosAPi.get("/reservation/post/1").then((res) => {
      setConsulting(res.data.responseObj);
      // setLoading(false);
    });
  }, []);

  // if (loading) return <div>Loading...</div>;
  return (
    <>
      <ConsultingCardList consulting={consulting} />
      <LastConsulting />
    </>
  );
}
export default MyReservation;
