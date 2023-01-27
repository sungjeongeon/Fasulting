import React from 'react'
import styles from "./Reservation.module.css"

function Reservation({ModalStateChange3}) {
  // 임시 data
  const ReservationConfirm = 
    {
      ps_name: "아이디병원",
      sub_category_list: [
        "쌍커풀",
        "눈매교정",
      ],
      reserve_date: "2023-01-12",
      day_of_week: "목",
      reserve_time: "15:00"
    }
  
  return (
    <div className={styles.background}>
      <div className={styles.modalbox}>
        <div className={styles.flexcol}>
          <h2 className={styles.confirm}>예약 확정</h2>
          <p className={`${styles.color} ${styles.hospital}`}>{ReservationConfirm.ps_name}</p>
          <div className={styles.line}></div>
          <div className={`${styles.flexrow} ${styles.mt}`}>
            <p className={`${styles.color} ${styles.mr}`}>예약 일정</p>
            <span>{ReservationConfirm.reserve_date}</span>
            <span className={styles.span}>({ReservationConfirm.day_of_week})</span>
            <span className={styles.span}>{ReservationConfirm.reserve_time}</span>
          </div>
          <div className={`${styles.flextop} ${styles.mb}`}>
            <p className={`${styles.color} ${styles.mr} ${styles.nomt}`}>상담 항목</p>
            <div>
            {ReservationConfirm.sub_category_list.map((ctg) => {
              return (
                <div>
                <span className={styles.color}>#</span>
                <span className={styles.mx}>{ctg}</span>
                </div>
            )})}
            </div>
          </div>
          <div className={`${styles.warninggray} ${styles.mb} ${styles.mt}`}>
          성형 부작용에 대한 법적 책임은 페이설팅에게 없으며 병원과 충분한 상담 후 신중한 결정 하시길 바랍니다.
          </div>
          <div className={`${styles.warningred} ${styles.mb}`}>
          “ 섣부른 선택이 평생 상처로 이어질 수 있습니다 ”
          </div>
          <div className={styles.flexbtn}>
            <button className={styles.okay} onClick={ModalStateChange3}>
              확인
            </button>
            <button className={styles.back} onClick={ModalStateChange3}>
              취소
            </button>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Reservation