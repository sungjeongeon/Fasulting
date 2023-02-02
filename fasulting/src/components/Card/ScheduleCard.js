import * as React from "react";
import Paper from "@mui/material/Paper";
import { styled, alpha } from "@mui/material/styles";
import { ViewState } from "@devexpress/dx-react-scheduler";
import {
  Scheduler,
  WeekView,
  Appointments,
} from "@devexpress/dx-react-scheduler-material-ui";
import appointments from "../../demo-data/today-appointments";

const PREFIX = "Demo";

const classes = {
  todayCell: `${PREFIX}-todayCell`,
  // disable Cell로 바꾸면 될 듯 -> 나중에 class에 이름 수정
  disabledCell: `${PREFIX}-disabledCell`,
  today: `${PREFIX}-today`,
  disabled: `${PREFIX}-disabled`,
};

const StyledWeekViewTimeTableCell = styled(WeekView.TimeTableCell)(
  ({ theme }) => ({
    [`&.${classes.todayCell}`]: {
      "&:hover": {
        backgroundColor: alpha(theme.palette.primary.main, 0.14),
      },
      "&:focus": {
        backgroundColor: alpha(theme.palette.primary.main, 0.16),
      },
    },
    [`&.${classes.disabledCell}`]: {
      backgroundColor: alpha(theme.palette.action.disabledBackground, 0.04),
      "&:hover": {
        backgroundColor: alpha(theme.palette.action.disabledBackground, 0.04),
      },
      "&:focus": {
        backgroundColor: alpha(theme.palette.action.disabledBackground, 0.04),
      },
    },
  })
);

const StyledWeekViewDayScaleCell = styled(WeekView.DayScaleCell)(
  ({ theme }) => ({
    [`&.${classes.today}`]: {
      backgroundColor: alpha(theme.palette.primary.main, 0.16),
    },
    [`&.${classes.disabled}`]: {
      backgroundColor: alpha(theme.palette.action.disabledBackground, 0.06),
    },
  })
);

const TimeTableCell = (props) => {
  const { startDate } = props;
  const date = new Date(startDate);
  const dateNow = new Date();
  // console.log(date)
  if (date.getDate() === new Date().getDate()) {
    return (
      <StyledWeekViewTimeTableCell {...props} className={classes.todayCell} />
    );
  }
  if (
    date < dateNow ||
    date > new Date(dateNow.setDate(dateNow.getDate() + 13))
  ) {
    return (
      <StyledWeekViewTimeTableCell
        {...props}
        className={classes.disabledCell}
      />
    );
  }
  return <StyledWeekViewTimeTableCell {...props} />;
};

const DayScaleCell = (props) => {
  const { startDate, today } = props;
  const dateNow = new Date();
  if (today) {
    return <StyledWeekViewDayScaleCell {...props} className={classes.today} />;
  }
  if (
    startDate < dateNow ||
    startDate > new Date(dateNow.setDate(dateNow.getDate() + 13))
  ) {
    return (
      <StyledWeekViewDayScaleCell {...props} className={classes.disabled} />
    );
  }
  return <StyledWeekViewDayScaleCell {...props} />;
};

// 스케줄 각 데이터 색깔 커스텀
const Appointment = ({ children, style, ...restProps }) => (
  <Appointments.Appointment
    {...restProps}
    style={{
      ...style,
      backgroundColor: "#72A1A6",
    }}
  >
    {children}
  </Appointments.Appointment>
);

export default function ScheduleCard() {
  return (
    <Paper sx={{ marginY: "2rem" }}>
      <Scheduler data={appointments} height={660}>
        <WeekView
          startDayHour={9}
          endDayHour={20}
          timeTableCellComponent={TimeTableCell}
          dayScaleCellComponent={DayScaleCell}
        />
        <Appointments appointmentComponent={Appointment} />
      </Scheduler>
    </Paper>
  );
}
