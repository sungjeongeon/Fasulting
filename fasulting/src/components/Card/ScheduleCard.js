import * as React from 'react';
import Paper from '@mui/material/Paper';
import { styled, alpha } from '@mui/material/styles';
import { ViewState } from '@devexpress/dx-react-scheduler';
import {
  Scheduler,
  WeekView,
  Appointments,
} from '@devexpress/dx-react-scheduler-material-ui';
// import appointments from '../../demo-data/today-appointments';
import { useSelector, useDispatch } from 'react-redux';
import { changeInfo } from "../../redux/modalInfo"
import { useEffect } from 'react';
import { update } from '../../redux/appointments';
import moment from 'moment';
import { useState } from 'react';
import axiosAPi from "../../api/axiosApi"


const PREFIX = 'Demo';
// const Vertical = 'VerticalAppointment'

const classes = {
  todayCell: `${PREFIX}-todayCell`,
  disabledCell: `${PREFIX}-disabledCell`,
  today: `${PREFIX}-today`,
  disabled: `${PREFIX}-disabled`,
};

const StyledWeekViewTimeTableCell = styled(WeekView.TimeTableCell)(({ theme }) => ({
  [`&.${classes.todayCell}`]: {
    '&:hover': {
      backgroundColor: alpha(theme.palette.primary.main, 0.14),
    },
    '&:focus': {
      backgroundColor: alpha(theme.palette.primary.main, 0.16),
    },
  },
  [`&.${classes.disabledCell}`]: {
    backgroundColor: alpha(theme.palette.action.disabledBackground, 0.04),
    // pointerEvents: "none",
    '&:hover': {
      backgroundColor: alpha(theme.palette.action.disabledBackground, 0.04),
    },
    '&:focus': {
      backgroundColor: alpha(theme.palette.action.disabledBackground, 0.04),
    },
  },
}));

const StyledWeekViewDayScaleCell = styled(WeekView.DayScaleCell)(({ theme }) => ({
  [`&.${classes.today}`]: {
    backgroundColor: alpha(theme.palette.primary.main, 0.16),
  },
  [`&.${classes.disabled}`]: {
    backgroundColor: alpha(theme.palette.action.disabledBackground, 0.06),
  },
}));

const TimeTableCell = (props) => {
  const { startDate } = props;
  const date = new Date(startDate);
  const dateNow = new Date();
  // onclick=((event)=> console.log(event.target.getAttribute('class')))

  if (date.getDate() === new Date().getDate()) {
    return <StyledWeekViewTimeTableCell {...props} className={classes.todayCell} />;
  } if (date < dateNow || date.getDate() > dateNow.getDate()+13) {
    return <StyledWeekViewTimeTableCell {...props} className={classes.disabledCell} />;
  } return <StyledWeekViewTimeTableCell {...props} />;
};

const DayScaleCell = (props) => {
  const { startDate, today } = props;
  const dateNow = new Date();
  if (today) {
    return <StyledWeekViewDayScaleCell {...props} className={classes.today} />;
  } if (startDate < dateNow || startDate.getDate() > dateNow.getDate()+13) {
    return <StyledWeekViewDayScaleCell {...props} className={classes.disabled} />;
  } return <StyledWeekViewDayScaleCell {...props} />;
};


///////////////////////////
// axios 요청 더미데이터 const로 들고오고, 해당 변수를 map으로 돌려서 function의 result를 받기




// 스케줄 각 데이터 색깔 커스텀
const Appointment = ({
  children,
  onClick,
  style,
  ...restProps
}) => {
  const dispatch = useDispatch()

  return (
    <div>
      <div>
        <Appointments.Appointment
          {...restProps}
          style={{
            ...style,
            backgroundColor: '#72A1A6',
            width: '111%',
            textAlign: "center",
            fontSize: "13px",
            lineHeight: "44px",
            overflow: "hidden",
          }}
          onClick={appointment => 
            // {console.log(JSON.parse(JSON.stringify(appointments.data)).startDate)}
            dispatch(changeInfo(JSON.parse(JSON.stringify(appointment.data))))
          }
        >
          {/* <React.Fragment> */}
          {children}
          {/* </React.Fragment> */}
        </Appointments.Appointment>
      </div>
    </div>
  )}


  export default function ScheduleCard() {
    // 캘린더 날짜 바뀔때 주간 스케줄표 날짜도 바뀌게끔
    const changeDate = useSelector(state => state.calendar)
    let current= `${changeDate.year}-${changeDate.month}-${changeDate.day}`
    

    // 계산하는 함수
    const currentDate = moment();
    let date = currentDate.date();
    // console.log(date)
    const makeAppointment = (reservationDateStart, reservationDateEnd) => {
      // console.log(year)
      const startDate = new Date(reservationDateStart)
      const endDate = new Date(reservationDateEnd)

      const nextStartDate = moment(startDate)
      const nextEndDate = moment(endDate)
      // console.log(nextStartDate.format('YYYY-MM-DD HH:mm'))
      return {
        startDate: nextStartDate.format(),
        endDate: nextEndDate.format(),
      };
    };

    // axios 요청을 위한 resSeq 들고오기 (일단 임시 resSeq)
    // const resSeq = useSelector(state => state.)
    const resSeq = 1
    // loading 처리
    // // axios 요청으로 데이터 들고오기
    const [loading, setLoading] = useState(true)
    const [allAppointments, setAllAppointments] = useState([]) // 기존 전체 
    const [operatingTime, setOperatingTime] = useState([])
    useEffect(() => {
      axiosAPi.get(`/ps-reservation/post/${resSeq}`)
        // .then(res => console.log(res.data.responseObj.reservation))
        .then(res => {
          setAllAppointments(res.data.responseObj.reservation)
          setOperatingTime(res.data.responseObj.operatingTime)
        })
        .then(setLoading(false))
        .catch(err => console.log(err))
    }, [])

    // axios로 요청한 데이터를 가공한 것을 appointments로 생성
    const appointments = 
      allAppointments.map(({ reservationDateStart, reservationDateEnd, ...restArgs }) => {
        const result = {
          ...makeAppointment(reservationDateStart, reservationDateEnd),
          ...restArgs,
        };
        date += 1;
        if (date > 31) date = 1;
        // console.log(result)
        return result;
      });
    // console.log(makeData)
    // const [appointments, setAppointments] = useState()
    // console.log(appointments)

    console.log(appointments)



    return (
      loading ? <div></div> :
    <Paper sx={{marginY: "2rem"}}>
      <Scheduler data={appointments} height={'auto'}>
        <ViewState
        currentDate={current}
        />
        <WeekView 
          startDayHour={9} 
          endDayHour={20}
          timeTableCellComponent={TimeTableCell}
          dayScaleCellComponent={DayScaleCell}
        />
        <Appointments
          appointmentComponent={Appointment}
        />
      </Scheduler>
    </Paper>
    );
  }
