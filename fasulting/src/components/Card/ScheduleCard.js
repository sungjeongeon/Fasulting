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
import { useState } from 'react';
import HospitalReservation from '../Modal/HospitalReservation';
import styles from "./ScheduleCard.module.css"


const PREFIX = 'Demo';
const Vertical = 'VerticalAppointment'

const classes = {
  todayCell: `${PREFIX}-todayCell`,
  disabledCell: `${PREFIX}-disabledCell`,
  today: `${PREFIX}-today`,
  disabled: `${PREFIX}-disabled`,
  textContainer: `${PREFIX}-textContainer`,
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
  // console.log(date)
  if (date.getDate() === new Date().getDate()) {
    return <StyledWeekViewTimeTableCell {...props} className={classes.todayCell} />;
  } if (date < dateNow || date > new Date(dateNow.setDate(dateNow.getDate()+13))) {
    return <StyledWeekViewTimeTableCell {...props} className={classes.disabledCell} />;
  } return <StyledWeekViewTimeTableCell {...props} />;
};

const DayScaleCell = (props) => {
  const { startDate, today } = props;
  const dateNow = new Date();
  if (today) {
    return <StyledWeekViewDayScaleCell {...props} className={classes.today} />;
  } if (startDate < dateNow || startDate > new Date(dateNow.setDate(dateNow.getDate()+13))) {
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
  const [modalOpen, setModalOpen] = useState(false)
  const ModalStateChange = () => {setModalOpen((current) => !current)}
  return (
    <div>
      <div onClick={ModalStateChange}>
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
          onClick={({target}) => {
            console.log(target)
            // ModalStateChange()
          }}
        >
          {/* <React.Fragment> */}
          {children}
          {/* </React.Fragment> */}
        </Appointments.Appointment>
      </div>
    </div>
  )}


export default function ScheduleCard() {
  

  return (
  <Paper sx={{marginY: "2rem"}}>
    <Scheduler data={appointments} height={660}>
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
