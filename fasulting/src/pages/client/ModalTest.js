import { useState } from "react";
// import Review from "../../components/Modal/Review"
// import AddDoctor from "../../components/Modal/AddDoctor"
// import AddCategory from "../../components/Modal/AddCategory"
// import Reservation from "../../components/Modal/Reservation"
import HospitalReservation from "../../components/Modal/HospitalReservation"
// import ReviewReport from "../../components/Modal/ReviewReport"


function ModalTest() {
  // 모달창 임시 테스트
  // const [ModalOpen, setModalOpen] = useState(false)
  // 모달창 열기, 닫기 상태 변경
  // const ModalStateChange = () => setModalOpen((current) => !current)
  
  // 병원 카테고리 추가 모달창 테스트
  // const [ModalOpen2, setModalOpen2] = useState(false)
  // 모달창 열기, 닫기 상태 변경
  // const ModalStateChange2 = () => setModalOpen2((current) => !current)
  

  // 사용자 예약확인
  // const [ModalOpen3, setModalOpen3] = useState(false)
  // 모달창 열기, 닫기 상태 변경
  // const ModalStateChange3 = () => setModalOpen3((current) => !current)

  // 병원 측 예약 확인
  const [ModalOpen4, setModalOpen4] = useState(false)
  // 모달창 열기, 닫기 상태 변경
  const ModalStateChange4 = () => setModalOpen4((current) => !current)

  // 병원 측 리뷰 신고
  // const [ModalOpen5, setModalOpen5] = useState(false)
  // 모달창 열기, 닫기 상태 변경
  // const ModalStateChange5 = () => setModalOpen5((current) => !current)

  return (
    <div>
      {/* <h1>ModalTest</h1>
      <button onClick={ModalStateChange}>
        모달창 테스트
      </button>
      {ModalOpen && 
      <AddDoctor 
        ModalStateChange={ModalStateChange}
      />} */}

      {/* <hr/>
      <button onClick={ModalStateChange2}>
        병원 카테고리 추가
      </button>
      {ModalOpen2 && 
        <AddCategory
          ModalStateChange2={ModalStateChange2}
      />} */}

      {/* <hr/>
      <button onClick={ModalStateChange3}>
        예약 확정
      </button>
      {ModalOpen3 && 
        <Reservation
          ModalStateChange3={ModalStateChange3}
      />} */}

      <hr/>
      <button onClick={ModalStateChange4}>
        병원 측 예약 관리
      </button>
      {ModalOpen4 && 
        <HospitalReservation
        ModalStateChange4={ModalStateChange4}
        />}
      
      {/* <hr/>
      <button onClick={ModalStateChange5}>
        리뷰신고
      </button>
      {ModalOpen5 && 
        <ReviewReport
          ModalStateChange5={ModalStateChange5}
      />} */}
    </div>
  );
}

export default ModalTest;
