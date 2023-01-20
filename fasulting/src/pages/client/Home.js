import Banner from "../../components/Banner";
import MainCategoryList from "../../components/Category/MainCategoryList";
import { Container } from "@mui/system";
import ReviewList from "../../components/List/ReviewList";
import { useState } from "react";
import Review from "../../components/Modal/Review"

function Home() {
  // 모달창 임시 테스트
  const [ModalOpen, setModalOpen] = useState(false)

  // 모달창 열기, 닫기 상태 변경
  const ModalStateChange = () => setModalOpen((current) => !current)

  return (
    <div>
      <button onClick={ModalStateChange}>
        모달창 테스트
      </button>
      {ModalOpen && 
      <Review 
        ModalStateChange={ModalStateChange}
      />}

      <Banner/>
      <Container maxidth="lg">
      <MainCategoryList/>
      </Container>
      <ReviewList/>
    </div>
  );
}

export default Home;
