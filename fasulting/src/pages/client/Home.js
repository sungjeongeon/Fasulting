import Banner from "../../components/Banner";
import MainCategoryList from "../../components/Category/MainCategoryList";
import { Container } from "@mui/system";
import ReviewList from "../../components/List/ReviewList";

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
