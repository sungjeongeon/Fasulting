import * as React from 'react';
import Paper from '@mui/material/Paper';
import { styled, alpha } from '@mui/material/styles';
import { ViewState } from '@devexpress/dx-react-scheduler';
import {
  Scheduler,
  WeekView,
  Appointments,
} from '@devexpress/dx-react-scheduler-material-ui';
import appointments from '../../demo-data/today-appointments';
import { useSelector, useDispatch } from 'react-redux';
import { changeInfo } from "../../redux/modalInfo"
import { useEffect } from 'react';
import { update } from '../../redux/appointments';


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
            lineHeight: "46px",
            overflow: "hidden",
          }}
          onClick={appointments => 
            // {console.log(JSON.parse(JSON.stringify(appointments.data)).startDate)}
            dispatch(changeInfo(JSON.parse(JSON.stringify(appointments.data))))
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
    
    const dispatch = useDispatch()
    // 모든 appointment 리덕스에 추가
    useEffect(() => {
      dispatch(update(appointments))
    }, [dispatch])

    // 리덕스로부터 들고온 appointments로 렌더링
    const appointmentsRedux = useSelector(state => state.appointments.appointmentList)
    // console.log(appointmentsRedux)

    return (
    <Paper sx={{marginY: "2rem"}}>
      <Scheduler data={appointmentsRedux} height={'auto'}>
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
