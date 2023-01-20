import React from "react";
import DoctorCard from "./DoctorCard";
import styles from "./DoctorCardList.module.css";

function DoctorCardList() {
  const doctors = [
    {
      id: 1,
      name: "김의사",
      main_category: "안면윤곽",
    },
    {
      id: 2,
      name: "이의사",
      main_category: "쁘띠",
    },
    {
      id: 3,
      name: "권의사",
      main_category: "안티에이징",
    },
  ];
  return (
    <div>
      <p className={styles.title}>의사 현황</p>
      <div className={styles.cardList}>
        {doctors.map((doc) => (
          <DoctorCard
            key={doc.id}
            name={doc.name}
            main_category={doc.main_category}
          />
        ))}
      </div>
    </div>
  );
}

export default DoctorCardList;
