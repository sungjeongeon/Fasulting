import React from "react";
import DoctorCard from "./DoctorCard";
import styles from "./DoctorCardList.module.css";

function DoctorCardList({ detailhospital }) {
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
  console.log("doctor", detailhospital.doctor);
  return (
    <div>
      <p className={styles.title}>의사 현황</p>
      <div className={styles.cardList}>
        {!detailhospital.doctor ? (
          <div>의사 현황이 존재하지 않습니다</div>
        ) : (
          detailhospital.doctor.map((doc) => (
            <DoctorCard
              key={doc.doctorSeq}
              name={doc.name}
              main_category={doc.mainCategoryName}
            />
          ))
        )}
      </div>
    </div>
  );
}

export default DoctorCardList;
