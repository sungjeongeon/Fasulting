import moment from 'moment';
import { appointments } from './appointments';

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

export default appointments.map(({ reservationDateStart, reservationDateEnd, ...restArgs }) => {

  const result = {
    ...makeAppointment(reservationDateStart, reservationDateEnd),
    ...restArgs,
  };
  date += 1;
  if (date > 31) date = 1;
  return result;
});
