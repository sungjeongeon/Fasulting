import ConsultingCard from "./ConsultingCard";
import { Typography } from "@mui/material";
import Box from "@mui/material/Box";

function ConsultingCardList() {
  const consulting = [
    {
      user_id: 1,
      ps_id: 1,
      ps_name: "아이디 병원",
      sub_category_name: "쌍커풀",
      calender_id: "2020.12.30 10시 25분",
    },
    {
      user_id: 2,
      ps_id: 2,
      ps_name: "더페이스 병원",
      sub_category_name: "콧볼축소",
      calender_id: "2022.01.31 12시 30분",
    },
  ];
  return (
    <>
      <Typography variant="h5">진행중인 예약</Typography>
      <Box sx={{ display: "flex" }}>
        {consulting.map((consult) => (
          <ConsultingCard key={consult.ps_id.toString()} consult={consult} />
        ))}
      </Box>
    </>
  );
}
export default ConsultingCardList;
