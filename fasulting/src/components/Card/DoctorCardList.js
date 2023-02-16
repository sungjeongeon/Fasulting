import React from "react";
import DoctorCard from "./DoctorCard";
import styles from "./DoctorCardList.module.css";

function DoctorCardList({ detailhospital }) {
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
              profileImg={doc.profileImg}
            />
          ))
        )}
      </div>
    </div>
  );
}

export default DoctorCardList;
