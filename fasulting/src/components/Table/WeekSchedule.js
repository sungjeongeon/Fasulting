import { useSelector, useDispatch } from "react-redux";
import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

function WeekSchedule() {
  // const dispatch = useDispatch()
  // const propdate = useSelector(state => {
  //   return state.calendar
  // })
  // console.log(propdate) // {year: 2023, month: 2, day: 6}

  // 날짜 인덱싱 위한 배열 (1~21)
  const array = Array(21).fill().map((arr, i) => {
    return i+1
  })

  return (
    <TableContainer component={Paper} sx={{height: 550}}>
      <Table sx={{ minWidth: 650}} aria-label="simple table">
        <TableHead>
          <TableRow sx={{position: "sticky"}}>
            <TableCell sx={{color: '#B1B1B1'}}>Week</TableCell>
            <TableCell>Sun</TableCell>
            <TableCell>Mon</TableCell>
            <TableCell>Tue</TableCell>
            <TableCell>Wed</TableCell>
            <TableCell>Thu</TableCell>
            <TableCell>Fri</TableCell>
            <TableCell>Sat</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          <Table>
            {array.map((row) => (
              <TableRow
                key={row}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {row}
                </TableCell>
              </TableRow>
            ))}
          </Table>
          <Table>
            {array.map((row) => (
              <TableRow
                key={row}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                <TableCell></TableCell>
                <TableCell></TableCell>
                <TableCell></TableCell>
                <TableCell></TableCell>
                <TableCell></TableCell>
                <TableCell></TableCell>
                <TableCell></TableCell>
              </TableRow>
            ))}
          </Table>
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default WeekSchedule;