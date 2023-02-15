import * as React from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import CalendarCard from '../Card/CalendarCard';
import OldReservationList from './OldReservationList';


function TabPanel(props) {
  const { value, index, children } = props;
  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <div>{children}</div>
        </Box>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    'aria-controls': `simple-tabpanel-${index}`,
  };
}

export default function ReservationManage({handleChange, value, nowShow, setNowShow}) {
  // const [value, setValue] = React.useState(0);

  // const handleChange = (event, newValue) => {
  //   setValue(newValue);
  // };

  return (
    <Box sx={{ width: '100%', paddingLeft: '1rem', paddingRight: '1rem' }}>
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
          <Tab label="예약 확인" {...a11yProps(0)} />
          <Tab label="지난예약 보기" {...a11yProps(1)} />
        </Tabs>
      </Box>
      <TabPanel value={value} index={0} children={<CalendarCard/>}></TabPanel>
      <TabPanel value={value} index={1} children={<OldReservationList nowShow={nowShow} setNowShow={setNowShow}/>}></TabPanel>
    </Box>
  );
}
