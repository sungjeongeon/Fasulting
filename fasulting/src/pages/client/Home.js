import Banner from "../../components/Banner";
import MainCategoryList from "../../components/MainCategoryList";
import { Container } from "@mui/system";
import ReviewList from "../../components/ReviewList";

function Home() {
  return (
    <div>
      <Banner/>
      <Container maxidth="lg">
      <MainCategoryList/>
      </Container>
      <ReviewList/>
    </div>
  );
}

export default Home;
