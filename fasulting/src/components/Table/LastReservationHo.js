import * as React from "react";
import { useState, useEffect } from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import { useDispatch } from "react-redux";
import { changeReserveId } from "../../redux/lastReservationHo"

// 검색 전 전체 결과 보여줄 때 데이터 -> axios 요청으로 수정하기
const lastReservation = [
  {
    user_id: 1,
    user_name: "김싸피",
    user_email: 'ssafy@naver.com',
    user_number: "010-1111-2222",
    user_birth: '1990-01-01',
    year: 2023,
    month: 1,
    day: 31,
    day_of_week: 3, // 1~7 (일~화)
    sub_category_name: ["쌍커풀", "눈매교정"],
    opinion: "인상이 더 뚜렷해보여, 눈매교정과 트임 시술 모두 추천드립니다.",
    price: 250,
    reservation_id: 1,
  },
  {
    user_id: 2,
    user_name: "권싸피",
    user_email: 'ssafy@naver.com',
    user_number: "010-3333-4444",
    user_birth: '1994-11-31',
    year: 2023,
    month: 2,
    day: 1,
    day_of_week: 4, // 1~7 (일~화)
    sub_category_name: ["보톡스", "필러"],
    opinion: "보톡스로 동안의 효과를 볼 수 있습니다.",
    price: 70,
    reservation_id: 2,
  },
];

const twolen = (num) => {
  return '0' + num.toString()
}

const dayOfWeek = (num) => {
  // eslint-disable-next-line default-case
  switch(num) {
    case 1:
      return "일"
    case 2:
      return "월"
    case 3:
      return "화"
    case 4:
      return "수"
    case 5:
      return "목"
    case 6:
      return "금"
    case 7:
      return "토"
  }
}
export default function LastReservationHo({search}) {
  // search 결과를 렌더링해야함 + 처음에는 모든 지난 예약 보기
  const [totalRes, setTotalRes] = useState([])
  useEffect(() => {
    setTotalRes(lastReservation)
  }, [])

  // 지난 예약 조회 axios
  // 각 병원 id 필요 -> 어디서 가져와야 할지 ??
  const psId = 1
  // useEffect(() => {
  //   axiosApi.get(`/ps-reservation/pre/${psId}`)
  //     .then(res => 
  //       console.log(res.responseObj)
  //       // setTotalRes(res.responseObj)
  //     )
  // }, [])

  //검색 결과와 확인 필요
  const searchRes = totalRes.filter(
      (item) => 
      item.user_name.includes(search) || item.sub_category_name.includes(search)
    ) 
  // setTotalRes(searchRes)
  // useEffect(() => {
  //   setTotalRes(searchRes)
  // }, [searchRes])

  const [nowInfo, setNowInfo] = useState({})
  // console.log(nowInfo)
  const dispatch = useDispatch()
  useEffect(() => {
    dispatch(changeReserveId(nowInfo))
  }, [dispatch, nowInfo])



  // detail 조회를 위해 consulting 아이디 필요
  // const consultingId = totalRes.consultingSeq
  // useEffect(() => {
  //   axiosApi.get(`/ps-reservation/pre/detail/${consultingId}`)
  //     .then(res =>
  //       console.log(res.data)
  //         // 
  //     )
  // }, [consultingId])



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
            {searchRes.map((reservation) => (
              <TableRow
                key={reservation.user_id}
                sx={{ "&:last-child td, &:last-child th": { border: 0 }, cursor: 'pointer' }}
                onClick={() => {
                  // console.log(reservation.reservation_id)
                  setNowInfo(reservation)
                  // console.log(nowId)
                }}
              >
                <TableCell component="th" scope="row" sx={{fontWeight: "bold"}}>
                  {reservation.user_name}
                </TableCell>
                <TableCell>
                  { reservation.sub_category_name.map((sub, index) => {
                    return <p style={{margin: 0}} key={index}>• {sub}</p>
                  })}
                </TableCell>
                <TableCell>
                  <span>{[reservation.year, 
                          reservation.month.toString().length < 2 ? twolen(reservation.month) : reservation.month , 
                          reservation.day.toString().length < 2 ? twolen(reservation.day) : reservation.day].join('.')}</span>
                  <span style={{marginLeft: '0.2rem'}}>({dayOfWeek(reservation.day_of_week)})</span>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );
}

