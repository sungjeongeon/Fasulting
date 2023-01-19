import { useParams } from "react-router-dom";
import ReserveCard from "../../components/Card/ReserveCard";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Unstable_Grid2";

function Detail() {
  const { id } = useParams();
  return (
    <Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={2}>
        {/* 프로필(배경) 이미지 */}
        <div
          style={{
            position: "absolute",
            left: "0",
            width: "100vw",
            height: "18rem",
            backgroundColor: "gray",
            opacity: "50%",
          }}
        ></div>
        <Grid xs={12} style={{ height: "18rem" }}></Grid>
        <Grid xs={9}>
          <h1>Detail</h1>
        </Grid>
        <Grid xs={3}>
          <ReserveCard />
        </Grid>
      </Grid>
    </Box>
  );
}

export default Detail;
