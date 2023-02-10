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
import Button from "@mui/material/Button";
import BorderColorIcon from "@mui/icons-material/BorderColor";
import AssignmentIcon from "@mui/icons-material/Assignment";
import { useNavigate } from "react-router-dom";
import Review from "../Modal/Review";

const preConsult = [
  {
    consultingSeq: 1,
    psName: "김싸피병원",
    estimate: 200,
    subCategoryName: ["쌍커풀, 안검하수"],
    date: "2023.01.23(월요일) 09시",
    reviewed: false,
    reported: false,
  },
  {
    consultingSeq: 2,
    psName: "장싸피병원",
    estimate: 300,
    subCategoryName: ["쌍커풀, 안검하수"],
    date: "2023.01.20(금요일) 17시 30분",
    reviewed: true,
    reported: false,
  },
];

export default function LastConsulting() {
  const navigate = useNavigate();

  const pgEstimate = (id) => {
    // navigate("/myestimate");
    navigate(`/myestimate/${id}`);
  };

  // 모달창
  const [ModalOpen, setModalOpen] = useState(false);
  const ModalStateChange = () => setModalOpen((current) => !current);

  return (
    <>
      <h2>지난 예약</h2>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          {/* <TableHead className={classes.root}> */}
          <TableHead>
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
            {preConsult.map((consult) => (
              <TableRow
                key={consult.consultingSeq}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {consult.psName}
                </TableCell>
                <TableCell>{consult.estimate} 만원</TableCell>
                <TableCell>{consult.subCategoryName.join(" ")}</TableCell>
                <TableCell>{consult.date}</TableCell>
                <TableCell>
                  <Button
                    variant="outlined"
                    startIcon={<AssignmentIcon />}
                    onClick={ModalStateChange}
                  >
                    리뷰작성
                  </Button>
                  {ModalOpen && (
                    <Review
                      ModalStateChange={ModalStateChange}
                      consultingSeq={consult.consultingSeq}
                    />
                  )}
                </TableCell>
                <TableCell>
                  <Button
                    variant="contained"
                    style={{ color: "white" }}
                    startIcon={<BorderColorIcon />}
                    onClick={() => {
                      pgEstimate(consult.ps_id);
                    }}
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
