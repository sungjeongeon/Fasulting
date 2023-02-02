import Banner from "../../components/Banner";
import MainCategoryList from "../../components/Category/MainCategoryList";
import { Container } from "@mui/system";
// import { useState } from "react";
import axios from "axios";
import Footer from "../../components/Footer";

function Home() {
  // axios.get("https://jsonplaceholder.typicode.com/todos/1").then((res) => {
  //   console.log(res);
  // });
  // axios.get("https://i8e106.p.ssafy.io:8080/account/ps").then((res) => {
  //   console.log(res);
  // });
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
