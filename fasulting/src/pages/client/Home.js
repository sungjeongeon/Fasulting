import Banner from "../../components/Banner";
import MainCategoryList from "../../components/Category/MainCategoryList";
import { Container } from "@mui/system";
// import { useState } from "react";
import axios from "axios";
import Footer from "../../components/Footer";

import axios from "../../api/Axios";
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
      <Footer />
    </div>
  );
}

export default Home;
