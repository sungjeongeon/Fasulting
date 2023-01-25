import * as React from "react";
import { useState } from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import { Typography } from "@mui/material";
import { makeStyles } from "@material-ui/core";
import Button from "@mui/material/Button";
import BorderColorIcon from "@mui/icons-material/BorderColor";
import AssignmentIcon from "@mui/icons-material/Assignment";
import { useNavigate } from "react-router-dom";
import Review from "../Modal/Review"

const lastconsulting = [
  {
    user_email: "이메일",
    ps_name: "아이디 병원",
    ps_id: 1,
    consulting_time: "2020.12.30 10시 25분",
    sub_category_name: "쌍커풀",
    sq_content: "소견 내용",
    sq_estimate: "300만원",
    //     "consulting_time" : "예약 시간",
    //     "sub_category" : {
    //         "sub_category_name" : "예약한 서브 카테고리 이름"
    //
  },
  {
    user_email: "이메일",
    ps_name: "더페이스 병원",
    ps_id: 2,
    consulting_time: "2020.12.30 10시 25분",
    sub_category_name: "안검하수",
    sq_content: "소견 내용",
    sq_estimate: "300만원",
    //     "consulting_time" : "예약 시간",
    //     "sub_category" : {
    //         "sub_category_name" : "예약한 서브 카테고리 이름"
    //
  },
];

const useStyles = makeStyles({
  root: {
    "& .MuiTableCell-head": {
      color: "#72A1A6",
      fontWeight: "bold",
    },
  },
});

export default function LastConsulting() {
  const classes = useStyles();
  const navigate = useNavigate();

  const pgEstimate = () => {
    navigate("/myestimate");
  };

  // 모달창
  const [ModalOpen, setModalOpen] = useState(false)
  const ModalStateChange = () => setModalOpen((current) => !current)
    
  return (
    <>
      <Typography variant="h5">지난 예약</Typography>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead className={classes.root}>
            <TableRow>
              <TableCell>병원명</TableCell>
              <TableCell>견적</TableCell>
              <TableCell>상담내용&nbsp;</TableCell>
              <TableCell>상담일시&nbsp;</TableCell>
              <TableCell>리뷰작성&nbsp;</TableCell>
              <TableCell>견적확인&nbsp;</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {lastconsulting.map((lastconsult) => (
              <TableRow
                key={lastconsult.ps_id}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {lastconsult.ps_name}
                </TableCell>
                <TableCell>{lastconsult.sq_estimate}</TableCell>
                <TableCell>{lastconsult.sub_category_name}</TableCell>
                <TableCell>{lastconsult.consulting_time}</TableCell>
                <TableCell>
                  <Button variant="outlined" startIcon={<AssignmentIcon />} onClick={ModalStateChange}>
                    리뷰작성
                  </Button>
                  {ModalOpen && 
                  <Review
                    ModalStateChange={ModalStateChange}
                  />}
                </TableCell>
                <TableCell>
                  <Button
                    variant="contained"
                    style={{ color: "white" }}
                    startIcon={<BorderColorIcon />}
                    onClick={pgEstimate}
                  >
                    견적상세
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );
}

// "consulting" : {
//     "user_email" : "이메일",
//     "ps_id": "병원 아이디",
//     "consulting_time" : "예약 시간",
//     "sub_category" : {
//         "sub_category_name" : "예약한 서브 카테고리 이름"
//     }
// }
