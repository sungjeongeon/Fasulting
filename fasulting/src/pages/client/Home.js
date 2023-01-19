import MainCategoryList from "../../components/MainCategoryList";
import { Container } from "@mui/system";
import ReviewList from "../../components/ReviewList";

function Home() {
  return (
    <div>
      <Container maxidth="lg">
      <MainCategoryList/>
      </Container>
      <ReviewList/>
    </div>
  );
}

export default Home;
