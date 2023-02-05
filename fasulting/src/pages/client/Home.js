import Banner from "../../components/Banner";
import MainCategoryList from "../../components/Category/MainCategoryList";
import { Container } from "@mui/system";
// import { useState } from "react";
// import axios from "axios";
import Footer from "../../components/Footer";

import axios from "../../api/axiosApi";
import axiosApi from "../../api/axiosApi";
import { useEffect, useState } from "react";
function Home() {
  //통신 테스트
  // axios.get("/main").then((res) => {
  //   console.log(res);
  // });
  // const [maincategory, setMaincategory] = useState([]);
  // useEffect(() => {
  //   axiosApi.get("/main").then((res) => {
  //     setMaincategory(res.data.responseObj);
  //   });
  // }, []);

  const [maincategory, setMaincategory] = useState([]);
  useEffect(() => {
    axiosApi.get("/main").then((res) => {
      setMaincategory(res.data.responseObj);
    });
  }, []);
  return (
    <div>
      <Container>
        <Banner />
      </Container>
      <Container maxidth="lg">
        <MainCategoryList maincategory={maincategory} />
      </Container>
      <Footer />
    </div>
  );
}

export default Home;
