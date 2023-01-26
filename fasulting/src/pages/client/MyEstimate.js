import { Container } from "@mui/system";
import EstimateCard from "../../components/Card/EstimateCard";
import HospitalInfo from "../../components/Detail/HospitalInfo";
import BeforeAfterCard from "../../components/Card/BeforeAfterCard";

function MyEstimate() {
  return (
    <Container maxidth="lg">
      <div style={{ display: 'flex' }}>
        <div style={{ display: 'flex', flexDirection: 'column' }}>
          <BeforeAfterCard />
          <p
            style={{ color: '#E64C3C', fontSize: '1rem', fontWeight: 'bold', textAlign: 'center'}}
            >
            상기 이미지는 가상 시뮬레이션 결과로,<br/>실제 수술 결과와 다를 수 있습니다.
          </p>
        </div>
        <EstimateCard />
      </div>
      <HospitalInfo />
    </Container>
  );
}

export default MyEstimate;
