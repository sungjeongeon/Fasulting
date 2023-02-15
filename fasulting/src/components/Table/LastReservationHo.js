import * as React from "react";
import { useState, useEffect } from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import axiosApi from "../../api/axiosApi"
import LastReservationHoItem from "./LastReservationHoItem";
import { useDispatch, useSelector } from "react-redux";




export default function LastReservationHo({search, nowShow, setNowShow}) {
  // search 결과를 렌더링해야함 + 처음에는 모든 지난 예약 보기
  const [totalRes, setTotalRes] = useState([])
  // 현재 활성화된 예약 강조
  // const [nowShow, setNowShow] = useState(0)

  // const dispatch = useDispatch()
  // 지난 예약 조회 axios
  const psId = useSelector(state => state.ps.psSeq)
  useEffect(() => {
    // axiosApi.get(`/ps-reservation/pre/${psId}`)
    axiosApi.get('/ps-reservation/pre/1')
      .then(res => {
        res.data === "" ? setTotalRes([]) : setTotalRes(res.data.responseObj)
      }
      )
      .catch(err => console.log(err))
  }, [])

  console.log(totalRes)
  //검색 결과와 확인 필요
  const searchRes = totalRes.filter(
      (item) => 
      item.userName.includes(search) || item.subCategoryName.includes(search)
    ) 

  return (
    <>
      <TableContainer component={Paper} sx={{ marginTop: "2rem"}}>
        <Table sx={{ minWidth: 330 }} aria-label="simple table">
          {/* <TableHead className={classes.root}> */}
          <TableHead>
            <TableRow>
              <TableCell sx={{fontWeight: "bold", color: "#72A1A6", width: '25%'}}>상담자</TableCell>
              <TableCell sx={{fontWeight: "bold", color: "#72A1A6", width: '33%'}}>상담 내용</TableCell>
              <TableCell sx={{fontWeight: "bold", color: "#72A1A6", width: '42%'}}>상담 일시</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {searchRes.length === 0 ?
            null
            : 
            (searchRes.map((reservation) => (
              <LastReservationHoItem
                key={reservation.consultingSeq}
                reservation={reservation} 
                nowShow={nowShow}
                setNowShow={setNowShow}
              />
            )))
            }
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );
}

