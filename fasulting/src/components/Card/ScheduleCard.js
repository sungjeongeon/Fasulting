import * as React from 'react';
import Paper from '@mui/material/Paper';
import { styled, alpha } from '@mui/material/styles';
import { ViewState } from '@devexpress/dx-react-scheduler';
import {
  Scheduler,
  WeekView,
  Appointments,
  CurrentTimeIndicator
} from '@devexpress/dx-react-scheduler-material-ui';
// import appointments from '../../demo-data/today-appointments';
import { useSelector, useDispatch } from 'react-redux';
import { changeInfo } from "../../redux/modalInfo"
import { useEffect } from 'react';
import { update } from '../../redux/appointments';
import moment from 'moment';
import { useState } from 'react';
import axiosAPi from "../../api/axiosApi"


const AppointmentContent = ({ style, ...restProps }) => {
  return (
    <Appointments.AppointmentContent {...restProps}>
      <div className={restProps.container} style={{margin: "auto"}}>
        <div>{restProps.data.title}</div>
      </div>
    </Appointments.AppointmentContent>
  );
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
    const makeAppointment = (reservationDateStart, reservationDateEnd) => {
      const startDate = new Date(reservationDateStart)
      const endDate = new Date(reservationDateEnd)

      const nextStartDate = moment(startDate)
      const nextEndDate = moment(endDate)
      return {
        startDate: nextStartDate.format(),
        endDate: nextEndDate.format(),
      };
    };

    // axios 요청을 위한 resSeq 들고오기 (일단 임시 resSeq)
    const psSeq = 1
    // loading 처리
    // // axios 요청으로 데이터 들고오기
    const [loading, setLoading] = useState(true)
    const [allAppointments, setAllAppointments] = useState([]) // 기존 전체 
    const [operatingTime, setOperatingTime] = useState([])
    useEffect(() => {
      if (loading) {
      axiosAPi.get(`/ps-reservation/post/${psSeq}`)
        // .then(res => console.log(res.data.responseObj.reservation))
        .then(res => {
          console.log(res.data.responseObj)
          setAllAppointments(res.data.responseObj.reservation)
          setOperatingTime(res.data.responseObj.operatingTime)
          setLoading(false)
          // console.log(operatingTime)
          // setupOperating()
          // updateFirst(dayTimeList)
        })
        .catch(err => console.log(err))
      } else {
          setupOperating()
          updateFirst(dayTimeList)
      }
    }, [loading])

    // axios로 요청한 데이터를 가공한 것을 appointments로 생성
    const appointments = 
      allAppointments.map(({ reservationDateStart, reservationDateEnd, ...restArgs }) => {
        const result = {
          ...makeAppointment(reservationDateStart, reservationDateEnd),
          ...restArgs,
        };
        date += 1;
        if (date > 31) date = 1;
        return result;
      });


    const twolen = (num) => {
      if (num < 10) {
        return '0'+num.toString()
      } return num.toString()
    }

    // 필요한 데이터로 operating 다시 만들기
    let dayTimeList = []
    const setupOperating = () => {
      operatingTime.map((obj) => {
        let h = 0
        let m = 0
        obj.time.map((timeItem) => {
          if (timeItem%2) {
            h = 9+parseInt(timeItem/2)
            m = '30'
          } else {
            h = 9+parseInt(timeItem/2)
            m = '00'
          }
          // day + hour + minute을 문자열로 합침
          dayTimeList.push(twolen(obj.year)+'.'+twolen(obj.month)+'.'+twolen(obj.day)+'.'+twolen(h)+'.'+m)
        })
      })
    }

    // console.log(dayTimeList)
    const [testList, setTestList] = useState([])
    const updateFirst = (list) => {
      setTestList(list)
    }

    const updateList = (str) => {
      // console.log(testList)
      let newList = []
      if (!isNaN(str.split('.').join(""))) {
        if (testList.includes(str)) {
          newList = testList.filter((testItem) => testItem !== str)
          console.log("포함됐던,,")
        } else {
          newList = [...testList, str]
          console.log("미포함됐던,,")
        }
        console.log(newList)
        setTestList(newList)
      }
    }


    const PREFIX = 'Demo';

    const classes = {
      allCell: `${PREFIX}-allCell`,
      fixedDisabledCell: `${PREFIX}-fixedDisabledCell`,
      disabledCell: `${PREFIX}-disabledCell`,
      today: `${PREFIX}-today`,
      disabled: `${PREFIX}-disabled`,
      shadedPart: `${PREFIX}-shadedPart`,
    };
    
    const StyledWeekViewTimeTableCell = styled(WeekView.TimeTableCell)(({ theme, currentTimeIndicatorPosition }) => ({
      [`&.${classes.allCell}`]: {
        '&:hover': {
          backgroundColor: alpha(theme.palette.primary.main, 0.14),
        },
        '&:focus': {
          backgroundColor: alpha(theme.palette.primary.main, 0.16),
        },
      },
      [`&.${classes.fixedDisabledCell}`]: {
        backgroundColor: alpha(theme.palette.action.disabledBackground, 0.04),
        '&:hover': {
          backgroundColor: alpha(theme.palette.action.disabledBackground, 0.04),
        },
        '&:focus': {
          backgroundColor: alpha(theme.palette.action.disabledBackground, 0.04),
        },
      },
      [`&.${classes.disabledCell}`]: {
        backgroundColor: alpha(theme.palette.action.disabledBackground, 0.04),
      },
      [`& .${classes.shadedPart}`]: {
        backgroundColor: alpha(theme.palette.primary.main, 0.08),
        position: 'absolute',
        height: currentTimeIndicatorPosition,
        width: '100%',
        left: 0,
        top: 0,
        'td:focus &': {
          backgroundColor: alpha(theme.palette.primary.main, 0.12),
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
      // onclick = ((e) => console.log(e.target.getAttribute('class').includes("include-time")))
      // onclick = ((e) => console.log(e.target.getAttribute('class').indexOf("date-")))
      // onclick = ((e) => console.log(e.target.getAttribute('class').substr(106,6)))
      onclick = ((e) => updateList(e.target.getAttribute('class').substr(e.target.getAttribute('class').indexOf("date-")+5,16)))

      // console.log(testList)
      const dateStr = twolen(date.getFullYear())+'.'+twolen(date.getMonth()+1)+'.'+twolen(date.getDate())+'.'+twolen(date.getHours())+'.'+twolen(date.getMinutes())
    
      if (!loading) {
        
      if (startDate.getMonth() === dateNow.getMonth()) {
        if (date < dateNow || date.getDate() > dateNow.getDate()+13 ) {
        return <StyledWeekViewTimeTableCell {...props} className={classes.fixedDisabledCell} />;
      }} else {
        return <StyledWeekViewTimeTableCell {...props} className={classes.fixedDisabledCell} />;
      }
      return <StyledWeekViewTimeTableCell {...props} className={testList.includes(dateStr) ? `${classes.allCell} ${`date-${dateStr}`} ${"include-time"}` : `${classes.disabledCell} ${classes.allCell} ${`date-${dateStr}`} ${"exclude-time"}`}/>;
      }
    };
    
    const DayScaleCell = (props) => {
      const { startDate, today } = props;
      const dateNow = new Date();
    
      if (today) {
        return <StyledWeekViewDayScaleCell {...props} className={classes.today} />;
      } 
      if (startDate.getMonth() === dateNow.getMonth()) {
        if (startDate.getDate() < dateNow.getDate() || startDate.getDate() > dateNow.getDate()+13) {
        return <StyledWeekViewDayScaleCell {...props} className={classes.disabled} />;
      }} else {
        return <StyledWeekViewDayScaleCell {...props} className={classes.disabled} />;
      }
      return <StyledWeekViewDayScaleCell {...props} />;
    };
    
    

    return (
      loading ? <div></div> :
      <Paper sx={{marginY: "2rem"}}>
        <Scheduler
          data={appointments}
          height={'auto'}
        >
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
            appointmentContentComponent={AppointmentContent}
          />
          <CurrentTimeIndicator
            
          />
        </Scheduler>
      </Paper>
    );
  }
