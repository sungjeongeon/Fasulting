import React, { useEffect, useState } from "react";
import styles from "./HospitalReservation.module.css";
import DisabledByDefaultOutlinedIcon from "@mui/icons-material/DisabledByDefaultOutlined";
import { useDispatch, useSelector } from "react-redux";
import { modalStateChange } from "../../redux/modalInfo";
import { update, cancel } from "../../redux/appointments";
import DownloadIcon from "@mui/icons-material/Download";
import CancelDialog from "../Dialog/CancelDialog";
import { useNavigate } from "react-router-dom";

function HospitalReservation() {
  // 삭제 확인 dialog
  const [open, setOpen] = useState(false);
  const handleStateChange = () => setOpen((current) => !current);

  // 임시 data
  const ReservationManage = useSelector((state) => {
    return {
      id: state.modalInfo.reservationId,
      name: state.modalInfo.userName,
      subList: state.modalInfo.subList,
      reservationTime: state.modalInfo.reservationStart,
      dayOfWeek: state.modalInfo.dayOfWeek,
      userSeq: state.modalInfo.userSeq,
      psSeq: state.modalInfo.psSeq,
      reservationSeq: state.modalInfo.reservationId,
    };
  });

  // 모달창 닫기 위한 action
  const dispatch = useDispatch();
  const modalClose = () => {
    dispatch(modalStateChange());
  };
  // 상담방 입장 (병원)
  const navigate = useNavigate();
  const enterConsultingRoom = () => {
    modalClose();
    // 병원 미래 예약 조회 api 구현 후 전달 바람 ==> 미림
    navigate("/consult", {
      state: {
        userSeq: ReservationManage.userSeq,
        psSeq: ReservationManage.psSeq,
        reservationSeq: ReservationManage.reservationSeq,
        who: "hospital",
      },
    });
  };

  const twolen = (num) => {
    return "0" + num.toString();
  };

  // yyyy-mm-dd
  const dateStr = ReservationManage.reservationTime.slice(0, 10);
  const year = Number(dateStr.split("-")[0]);
  const month = Number(dateStr.split("-")[1]);
  const day = Number(dateStr.split("-")[2]);
  const hour = Number(ReservationManage.reservationTime.slice(11, 13)); //9시간 시차 계산
  const minute = ReservationManage.reservationTime.slice(14, 16);

  // 예약 일정 (30분 전까지만 상담 입장 막아놓기)
  const reservetime = new Date(year, month - 1, day, hour, minute - 30);
  const afterReserveTime = new Date(year, month - 1, day, hour, minute + 30);
  const cancelTime = new Date(year, month - 1, day - 1, hour, minute);

  return (
    <div className={styles.background}>
      <div className={styles.modalbox}>
        <div className={styles.flexcol}>
          <h2 className={styles.confirm}>예약 확인</h2>
          <DisabledByDefaultOutlinedIcon
            fontSize="large"
            onClick={modalClose}
            color="action"
            className={styles.back}
          />
          <p className={`${styles.color} ${styles.hospital}`}>
            {ReservationManage.name}
          </p>
          <div className={styles.line}></div>
          <div className={`${styles.flexrow} ${styles.mt}`}>
            <p className={`${styles.color} ${styles.mr}`}>예약 일정</p>
            <span>{dateStr}</span>
            <span className={styles.span}>({ReservationManage.dayOfWeek})</span>
            <span className={styles.span}>{`${
              hour.toString().length < 2 ? twolen(hour) : hour
            }시 ${minute}분`}</span>
          </div>
          <div className={styles.flextop}>
            <p className={`${styles.color} ${styles.mr} ${styles.nomt}`}>
              상담 항목
            </p>
            <div>
              {ReservationManage.subList.map((ctg) => {
                return (
                  <div className={styles.spancol} key={ctg}>
                    <span className={styles.color}>#</span>
                    <span className={styles.mx}>{ctg}</span>
                  </div>
                );
              })}
            </div>
          </div>

          <div className={`${styles.warning} ${styles.mb} ${styles.confirm}`}>
            상담 시 성형 부작용 및 주의사항에 대한 충분한 안내를 요구합니다.
          </div>
          {afterReserveTime > new Date() ? (
            reservetime <= new Date() ? (
              <div className={styles.flexcol}>
                <div className={styles.flex}>
                  <button className={`${styles.okay} ${styles.mr}`}>
                    이미지 다운로드
                  </button>
                  <button className={styles.okay} onClick={enterConsultingRoom}>
                    상담 입장
                  </button>
                </div>
              </div>
            ) : cancelTime < new Date() ? (
              <div className={styles.flexcol}>
                <div className={styles.flex}>
                  <button className={`${styles.okay} ${styles.mr}`}>
                    이미지 다운로드
                  </button>
                  <button className={styles.yet}>상담 입장</button>
                </div>
                <p className={styles.color}>아직 상담시간이 아닙니다 !</p>
                {/* <p className={styles.color}>상담 1시간 전부터 입장하실 수 있습니다.</p> */}
              </div>
            ) : (
              <div className={styles.flexcol}>
                <div className={styles.flex}>
                  <button
                    className={`${styles.cancel} ${styles.mr}`}
                    onClick={handleStateChange}
                  >
                    상담 취소
                    {open && (
                      <CancelDialog
                        ReservationManage={ReservationManage}
                        modalClose={modalClose}
                      />
                    )}
                  </button>
                  <button className={styles.yet}>상담 입장</button>
                </div>
                <p className={styles.color}>아직 상담시간이 아닙니다 !</p>
              </div>
            )
          ) : (
            <div className={styles.flexcol}>
              <button className={styles.yet}>상담 완료</button>
              <p className={styles.color}>상담이 완료된 예약입니다.</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default HospitalReservation;
