import Banner from "../../components/Banner";
import MainCategoryList from "../../components/Category/MainCategoryList";
import { Container } from "@mui/system";
import ReviewList from "../../components/List/ReviewList";
// import { useState } from "react";

import axios from "../../api/axios";
function Home() {
  //통신 테스트
  axios.get("/account/ps").then((res) => {
    console.log(res);
  });
  return (
    <div>
      <Container>
        <Banner />
      </Container>
      <Container maxidth="lg">
        <MainCategoryList />
      </Container>
      <ReviewList />
    </div>
  );
}

export default Home;
