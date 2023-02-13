import React, { useState } from "react";
import TableCell from "@mui/material/TableCell";
import TableRow from "@mui/material/TableRow";
import { useDispatch } from "react-redux";
import { changeReserveInfo, changeLoading, changeLoadingTrue } from "../../redux/lastReservationHo"
import axiosAPi from "../../api/axiosApi";


function LastReservationHoItem({reservation, nowShow, setNowShow}) {
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



  const dispatch = useDispatch()
  const onClickHandler = () => {
    axiosAPi.get(`/ps-reservation/pre/detail/${reservation.consultingSeq}`)
    .then(res => {
      dispatch(changeReserveInfo(res.data.responseObj))
      dispatch(changeLoading())
      setNowShow(reservation.consultingSeq)
    })
    .catch(err => console.log(err))
  }

  return (
    <TableRow
      sx={{ 
        "&:last-child td, &:last-child th": { border: 0 }, 
        cursor: 'pointer', 
        "&:hover": {backgroundColor : "#72A1A640"}, 
        backgroundColor: (nowShow === reservation.consultingSeq ? "#72A1A640" : "white") 
      }}
      onClick={onClickHandler}
    >
      <TableCell component="th" scope="row" sx={{fontWeight: "bold"}}>
        {reservation.userName}
      </TableCell>
      <TableCell>
        { reservation.subCategoryName.map((sub, index) => {
          return <p style={{margin: 0}} key={index}>• {sub}</p>
        })}
      </TableCell>
      <TableCell>
        <span>{[reservation.year, 
                reservation.month.toString().length < 2 ? twolen(reservation.month) : reservation.month , 
                reservation.day.toString().length < 2 ? twolen(reservation.day) : reservation.day].join('.')}</span>
        <span style={{marginLeft: '0.2rem'}}>({dayOfWeek(reservation.dayOfWeek)})</span>
      </TableCell>
    </TableRow>
  )
}
export default LastReservationHoItem;