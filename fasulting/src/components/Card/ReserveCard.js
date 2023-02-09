import React, { useEffect, useState } from "react";
import { styled } from "@mui/material/styles";
import ArrowForwardIosSharpIcon from "@mui/icons-material/ArrowForwardIosSharp";
import MuiAccordion from "@mui/material/Accordion";
import MuiAccordionSummary from "@mui/material/AccordionSummary";
import MuiAccordionDetails from "@mui/material/AccordionDetails";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Checkbox from "@mui/material/Checkbox";
import styles from "./ReserveCard.module.css";
import ReserveCardDateItem from "./ReserveCardDateItem";
import ReserveCardTimeItem from "./ReserveCardTimeItem";
import MainCategoryListSmall from "../Category/MainCategoryListSmall";
import ReserveCardCategoryItem from "./ReserveCardCategoryItem";
import Reservation from "../Modal/Reservation";

const Accordion = styled((props) => (
  <MuiAccordion disableGutters elevation={0} square {...props} />
))(({ theme }) => ({
  border: `1px solid #D9D9D9`,
  "&:not(:last-child)": {
    borderBottom: 0,
  },
  "&:before": {
    display: "none",
  },
}));

const AccordionSummary = styled((props) => (
  <MuiAccordionSummary
    expandIcon={<ArrowForwardIosSharpIcon sx={{ fontSize: "0.9rem" }} />}
    {...props}
  />
))(({ theme }) => ({
  backgroundColor:
    theme.palette.mode === "dark"
      ? "rgba(255, 255, 255, .05)"
      : "rgba(0, 0, 0, .03)",
  flexDirection: "row-reverse",
  "& .MuiAccordionSummary-expandIconWrapper.Mui-expanded": {
    transform: "rotate(90deg)",
  },
  "& .MuiAccordionSummary-content": {
    marginLeft: theme.spacing(1),
  },
}));

const AccordionDetails = styled(MuiAccordionDetails)(({ theme }) => ({
  padding: theme.spacing(2),
  borderTop: "1px solid rgba(0, 0, 0, .125)",
}));

const label = { inputProps: { "aria-label": "Checkbox demo" } };

// 여기 위로는 수정 X (MUI 코드) ==============================
export default function ReserveCard({ reshospital }) {
  // MUL modal 코드
  const [expanded, setExpanded] = React.useState("panel0");
  const handleChange = (panel) => (event, newExpanded) => {
    setExpanded(newExpanded ? panel : false);
  };
  // 임시 data
  const operatingTime = [
    { id: 2, year: 2023, month: 1, day: 27, day_of_week: 5, hour: 2 },
    { id: 3, year: 2023, month: 1, day: 27, day_of_week: 5, hour: 5 },
    { id: 1, year: 2023, month: 1, day: 27, day_of_week: 5, hour: 1 },
    { id: 4, year: 2023, month: 1, day: 27, day_of_week: 5, hour: 9 },
    { id: 5, year: 2023, month: 1, day: 27, day_of_week: 5, hour: 11 },
    { id: 6, year: 2023, month: 1, day: 27, day_of_week: 5, hour: 13 },
    { id: 7, year: 2023, month: 1, day: 28, day_of_week: 6, hour: 14 },
    { id: 8, year: 2023, month: 1, day: 28, day_of_week: 6, hour: 3 },
  ];
  // ==========================================================

  // 선택된 일자 + 상담 항목 정보 (서버에 넘길 것)
  const [year, setYear] = useState(0);
  const [month, setMonth] = useState(0);
  const [day, setDay] = useState(0);
  const [dayOfWeek, setDayOfWeek] = useState(0);
  const [hour, setHour] = useState(-1);
  const [consultItem, setConsultItem] = useState([]);
  const [isAgree, setIsAgree] = useState(false);
  // 사용자 선택 날짜로 필터링된 운영시간 Array (시간선택 component에 넘길 것)
  const [operatingByDate, setOperingByDate] = useState([]);

  const getDate = (date) => {
    // 하위항목 리셋 (선택하다가 날짜 바꿨을 때)
    setHour(-1);
    // submit 할 state에 날짜 값 저장
    setYear(date.year);
    setMonth(date.month);
    setDay(date.day);
    setDayOfWeek(date.dayOfWeek);

    // 병원의 7일간 모든 운영시간 중에 사용자가 선택한 날짜와 일치하는 data만 filtering
    //console.log("reshospital", reshospital);
    //console.log("res", reshospital);
    const filterByDate = reshospital.operatingTimeList.filter(
      (time) =>
        time.year === date.year &&
        time.month === date.month &&
        time.day === date.day
    );
    //console.log("filter", filterByDate);
    //const times = filterByDate.time.map((o) => o.hour);
    const times = filterByDate.map((o) => o.time);
    //console.log("time", times);
    setOperingByDate(times);
    setExpanded("panel1");
  };

  const getReserveTime = (h) => {
    setHour(h);
    setExpanded("panel2");
  };

  const getConsultItem = (i) => {
    setConsultItem(i);
  };

  const getAgree = () => {
    setIsAgree((current) => !current);
  };
  // 예약 관련 정보 제출 -> 서버
  const reservate = () => {
    console.log(
      `${year}년 ${month}월 ${day}일 시간${hour} 동의여부 ${isAgree}`
    );
  };

  // 예약하기 클릭 시 모달 창
  const [ModalOpen, setModalOpen] = useState(false);
  const ModalStateChange = () => setModalOpen((current) => !current);

  const [img, setImg] = useState([]);
  //파일(이미지) 업로드
  const onChange = (e) => {
    const uploadimg = e.target.files[0];
    setImg(uploadimg);
    // const formData = new FormData();
    // formData.append("file", img);
  };

  return (
    <div className={styles.outerDiv}>
      {/* 날짜 선택 구간 */}
      <div className={styles.dateDiv}>
        <ReserveCardDateItem getDate={getDate} />
      </div>
      {/* 시간 선택 구간 */}
      <Accordion
        expanded={expanded === "panel1" || expanded === "panel2"}
        onChange={handleChange("panel1")}
      >
        <AccordionSummary aria-controls="panel1d-content" id="panel1d-header">
          <Typography>시간 선택</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <ReserveCardTimeItem
            times={operatingByDate}
            getReserveTime={getReserveTime}
          />
        </AccordionDetails>
      </Accordion>
      {/* 상담항목 선택 구간 */}
      <Accordion
        expanded={expanded === "panel2"}
        onChange={handleChange("panel2")}
      >
        <AccordionSummary aria-controls="panel2d-content" id="panel2d-header">
          <Typography>상담 항목</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <ReserveCardCategoryItem getConsultItem={getConsultItem} />
        </AccordionDetails>
      </Accordion>
      <div className={styles.margin}>
        {/* <p className={styles.ptag}>반드시 정면이 나온 사진을 첨부해주세요</p> */}
        <label className={styles.inputfile} htmlFor="inputfile">
          파일 선택
        </label>
        <input
          type="file"
          id="inputfile"
          accept="image/jpg,impge/png,image/jpeg,image/gif"
          name="profile_img"
          onChange={onChange}
          style={{ display: "none" }}
        ></input>
        {img.name}
      </div>
      <div className={styles.inFooterDiv}>
        <p className={styles.agree}>개인정보 제공 동의</p>
        <div className={styles.checkBox}>
          <Checkbox {...label} onClick={getAgree} />
          <p>
            예약 진행, 고객 상담, 고객 관리 및 고객 문의를 위해 예약자 이름,
            예약자 휴대폰 번호를 수집하여 해당 병원 업체에 제공함에 동의합니다.
          </p>
        </div>
        <Button
          onClick={ModalStateChange}
          //onClick={ModalStateChange}
          type="submit"
          variant="contained"
          sx={{ mt: 3, mb: 2, fontWeight: 600 }}
        >
          에약하기
        </Button>
        {ModalOpen && (
          <Reservation
            psName={reshospital.psName}
            //psSeq={reshospital.psSeq}
            year={year}
            month={month}
            day={day}
            dayOfWeek={dayOfWeek}
            time={hour}
            beforeImg={img}
            subCategory={consultItem}
            ModalStateChange={ModalStateChange}
          />
        )}
      </div>
    </div>
  );
}
