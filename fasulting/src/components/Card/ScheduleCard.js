import * as React from 'react';
import Paper from '@mui/material/Paper';
import { ViewState } from '@devexpress/dx-react-scheduler';
import {
  Scheduler,
  WeekView,
  Appointments,
} from '@devexpress/dx-react-scheduler-material-ui';


// import appointments from '../../../demo-data/today-appointments';

export default function ScheduleCard() {
  
  return (
  <Paper sx={{marginY: "2rem"}}>
    <Scheduler height={660}>
      <WeekView 
        startDayHour={9} 
        endDayHour={20}
        
      />
      <Appointments />
    </Scheduler>
  </Paper>
  );
}
