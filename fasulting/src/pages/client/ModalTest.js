import { useState } from "react";
import Review from "../../components/Modal/Review"
import AddCategory from "../../components/Modal/AddCategory"


function ModalTest() {
  // 모달창 임시 테스트
  const [ModalOpen, setModalOpen] = useState(false)

  // 모달창 열기, 닫기 상태 변경
  const ModalStateChange = () => setModalOpen((current) => !current)
  
  // 병원 카테고리 추가 모달창 테스트
  const [ModalOpen2, setModalOpen2] = useState(false)
  // 모달창 열기, 닫기 상태 변경
  const ModalStateChange2 = () => setModalOpen2((current) => !current)

  return (
    <div>
      <h1>ModalTest</h1>
      <button onClick={ModalStateChange}>
        모달창 테스트
      </button>
      {ModalOpen && 
      <Review 
        ModalStateChange={ModalStateChange}
      />}

      <hr/>
      <button onClick={ModalStateChange2}>
        병원 카테고리 추가
      </button>
      {ModalOpen2 && 
        <AddCategory
          ModalStateChange2={ModalStateChange2}
      />}
    </div>
  );
}

export default ModalTest;
