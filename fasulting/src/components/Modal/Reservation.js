import React from "react";
import styles from "./Reservation.module.css";
import propTypes from "prop-types";
import axios from "axios";
import { toast } from "react-toastify";
import { Token } from "@mui/icons-material";
import axiosAPi from "../../api/axiosApi";

function Reservation(props) {
  // 임시 data
  const ReservationConfirm = {
    ps_name: "아이디병원",
    sub_category_list: ["쌍커풀", "눈매교정"],
    reserve_date: "2023-01-12",
    day_of_week: "목",
    reserve_time: "15:00",
  };
  console.log(props.subCategory);
  const submitForm = async () => {
    const formData = new FormData();
    formData.append("userSeq", 1); //유저 변수 필요
    formData.append("psSeq", props.psSeq);
    formData.append("year", props.year);
    formData.append("month", props.month);
    formData.append("day", props.day);
    formData.append("dayOfWeek", props.dayOfWeek);
    formData.append("time", props.hour);
    formData.append("beforeImg", props.img);
    formData.append("subCategory", props.subCategory);

    try {
      await axiosAPi.post("/reservation/register", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          //Authorization: localStorage.getItem("access_token")
        },
        withCredentials: true,
      });
      toast.success(
        <h3>
          예약이 완료되었습니다.
          <br />
          나의 예약탭에서 확인하세요😄
        </h3>,
        {
          position: toast.POSITION.TOP_CENTER,
          autoClose: 2000,
        }
      );
    } catch (e) {
      // FormData의 key 확인
      for (let key of formData.keys()) {
        console.log(key);
      }

      // FormData의 value 확인
      for (let value of formData.values()) {
        console.log(value);
      }
      console.log(e.response.data.message);
    }
  };
  return (
    <div className={styles.background}>
      <div className={styles.modalbox}>
        <div className={styles.flexcol}>
          <h2 className={styles.confirm}>예약 확인</h2>
          <p className={`${styles.color} ${styles.hospital}`}>{props.psName}</p>
          <div className={styles.line}></div>
          <div className={`${styles.flexrow} ${styles.mt}`}>
            <p className={`${styles.color} ${styles.mr}`}>예약 일정</p>
            <span>{props.year}.</span>
            <span className={styles.span}>{props.month}.</span>
            <span className={styles.span}>{props.day}.</span>
            <span className={styles.span}>({props.dayOfWeek})</span>
            <span className={styles.span}>{props.time}</span>
          </div>
          <div className={`${styles.flextop} ${styles.mb}`}>
            <p className={`${styles.color} ${styles.mr} ${styles.nomt}`}>
              상담 항목
            </p>
            <div>
              {props.subCategory.sub.map((ctg) => {
                return (
                  <div>
                    <span className={styles.color}>#</span>
                    <span className={styles.mx}>{ctg}</span>
                  </div>
                );
              })}
            </div>
          </div>
          <div className={`${styles.warninggray} ${styles.mb} ${styles.mt}`}>
            성형 부작용에 대한 법적 책임은 페이설팅에게 없으며 병원과 충분한
            상담 후 신중한 결정 하시길 바랍니다.
          </div>
          <div className={`${styles.warningred} ${styles.mb}`}>
            “ 섣부른 선택이 평생 상처로 이어질 수 있습니다 ”
          </div>
          <div className={styles.flexbtn}>
            <button className={styles.okay} onClick={submitForm}>
              예약
            </button>
            <button className={styles.back} onClick={props.ModalStateChange}>
              취소
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

Reservation.propTypes = {
  year: propTypes.number.isRequired,
  month: propTypes.number.isRequired,
  day: propTypes.number.isRequired,
  hour: propTypes.number,
  // consultItem: propTypes.objectOf([main: string),
  ModalStateChange: propTypes.func.isRequired,
};

export default Reservation;
