import React, { useState } from 'react'
import styles from "./HospitalReservation.module.css"
import DisabledByDefaultOutlinedIcon from '@mui/icons-material/DisabledByDefaultOutlined';

function HospitalReservation({ModalStateChange4}) {
  const [open, setOpen] = useState(false)

  // 임시 data
  const ReservationManage = 
    {
      name: "김싸피",
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
          <h2 className={styles.confirm}>예약 확인</h2>
          <DisabledByDefaultOutlinedIcon
            fontSize="large"
            onClick={ModalStateChange4}
            color="action"
            className={styles.back}
          />
          <p className={`${styles.color} ${styles.hospital}`}>{ReservationManage.name}</p>
          <div className={styles.line}></div>
          <div className={`${styles.flexrow} ${styles.mt}`}>
            <p className={`${styles.color} ${styles.mr}`}>예약 일정</p>
            <span>{ReservationManage.reserve_date}</span>
            <span className={styles.span}>({ReservationManage.day_of_week})</span>
            <span className={styles.span}>{ReservationManage.reserve_time}</span>
          </div>
          <div className={styles.flextop}>
            <p className={`${styles.color} ${styles.mr} ${styles.nomt}`}>상담 항목</p>
            <div>
            {ReservationManage.sub_category_list.map((ctg) => {
              return (
                <div className={styles.spancol}>
                <span className={styles.color}>#</span>
                <span className={styles.mx}>{ctg}</span>
                </div>
            )})}
            </div>
          </div>
          <div className={`${styles.warning} ${styles.mb} ${styles.confirm}`}>
          상담 시 성형 부작용 및 주의사항에 대한 충분한 안내를 요구합니다.
          </div>
          { open ?
          <div className={styles.flexcol}>
            <button className={styles.okay} onClick={ModalStateChange4}>
              상담 입장
            </button>
          </div>
          : 
          <div className={styles.flexcol}>
            <button className={styles.yet} onClick={ModalStateChange4}>
              상담 입장
            </button>
            <p className={styles.color}>아직 상담시간이 아닙니다 !</p>
          </div>
          }
        </div>
      </div>
    </div>
  )
}

export default HospitalReservation