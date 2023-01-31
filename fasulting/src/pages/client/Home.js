import Banner from "../../components/Banner";
import MainCategoryList from "../../components/Category/MainCategoryList";
import { Container } from "@mui/system";
import ReviewList from "../../components/List/ReviewList";
// import { useState } from "react";
import axios from "axios";

function Home() {
  // axios.get("https://jsonplaceholder.typicode.com/todos/1").then((res) => {
  //   console.log(res);
  // });
  axios.get("https://i8e106.p.ssafy.io:8081/account/ps").then((res) => {
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
