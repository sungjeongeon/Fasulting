import React, { useEffect, useState } from "react";
import { styled } from "@mui/material/styles";
import ArrowForwardIosSharpIcon from "@mui/icons-material/ArrowForwardIosSharp";
import MuiAccordion from "@mui/material/Accordion";
import MuiAccordionSummary from "@mui/material/AccordionSummary";
import MuiAccordionDetails from "@mui/material/AccordionDetails";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import styles from "./ReserveCard.module.css";
import ReserveCardDateItem from "./ReserveCardDateItem";
import ReserveCardTimeItem from "./ReserveCardTimeItem";

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

// 여기 위로는 수정 X (MUI 코드) ==============================
export default function ReserveCard() {
  // MUL modal 코드
  const [expanded, setExpanded] = React.useState("panel0");
  const handleChange = (panel) => (event, newExpanded) => {
    setExpanded(newExpanded ? panel : false);
  };
  // 임시 data
  const operating = [
    { id: 1, year: 2023, month: 1, day: 26, day_of_week: 2, hour: 1 },
    { id: 2, year: 2023, month: 1, day: 26, day_of_week: 2, hour: 2 },
    { id: 3, year: 2023, month: 1, day: 26, day_of_week: 2, hour: 5 },
    { id: 4, year: 2023, month: 1, day: 26, day_of_week: 2, hour: 9 },
    { id: 5, year: 2023, month: 1, day: 26, day_of_week: 2, hour: 11 },
    { id: 6, year: 2023, month: 1, day: 26, day_of_week: 2, hour: 13 },
    { id: 7, year: 2023, month: 1, day: 26, day_of_week: 2, hour: 14 },
    { id: 8, year: 2023, month: 1, day: 27, day_of_week: 3, hour: 3 },
  ];
  // ==========================================================

  // 선택된 일자 정보 (서버에 넘길 것)
  const [year, setYear] = useState(0);
  const [month, setMonth] = useState(0);
  const [day, setDay] = useState(0);
  const [hour, setHour] = useState(-1);
  // 사용자 선택 날짜로 필터링된 운영시간 Array (시간선택 component에 넘길 것)
  const [operatingByDate, setOperingByDate] = useState([]);

  const getTimeTable = (date) => {
    // submit 할 state에 날짜 값 저장
    setYear(date.year);
    setMonth(date.month);
    setDay(date.day);
    // 병원의 7일간 모든 운영시간 중에 사용자가 선택한 날짜와 일치하는 data만 filtering
    const filterByDate = operating.filter(
      (time) =>
        time.year === date.year &&
        time.month === date.month &&
        time.day === date.day
    );
    const times = filterByDate.map((o) => o.hour);
    setOperingByDate(times);
    setExpanded("panel1");
  };

  const getReserveTime = (h) => {
    setHour(h);
    setExpanded("panel2");
  };

  const reservate = () => {
    console.log(year, month, day, hour);
  };
  return (
    <div className={styles.outerDiv}>
      {/* 날짜 선택 구간 */}
      <div className={styles.dateDiv}>
        <ReserveCardDateItem getTimeTable={getTimeTable} />
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
          <Typography>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse
            malesuada lacus ex, sit amet blandit leo lobortis eget. Lorem ipsum
            dolor sit amet, consectetur adipiscing elit. Suspendisse malesuada
            lacus ex, sit amet blandit leo lobortis eget.
          </Typography>
        </AccordionDetails>
      </Accordion>

      <div className={styles.footerDiv}>
        <Button
          onClick={reservate}
          type="submit"
          variant="contained"
          sx={{ mt: 3, mb: 2 }}
        >
          에약하기
        </Button>
      </div>
    </div>
  );
}
