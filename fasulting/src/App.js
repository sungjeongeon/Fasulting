import "devextreme/dist/css/dx.light.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import FindPw from "./pages/client/FindPw";
import Home from "./pages/client/Home";
import Login from "./pages/client/Login";
import PsList from "./pages/client/PsList";
import Register from "./pages/client/Register";
import MypageCl from "./pages/client/MypageCl";
import Detail from "./pages/client/Detail";
import MyActivity from "./pages/client/MyActivity.js";
import { Container } from "@mui/system";
import * as React from "react";
import CssBaseline from "@mui/material/CssBaseline";
import { ThemeProvider } from "@emotion/react";
import { createTheme } from "@mui/material";
import MyReservation from "./pages/client/MyReservation";
import MyEstimate from "./pages/client/MyEstimate";
import ModalTest from "./pages/client/ModalTest";
import MypageHo from "./pages/hospital/MypageHo";
import PsRegister from "./pages/hospital/PsRegister";
import MyReservationHo from "./pages/hospital/MyReservationHo";
import { createGlobalStyle } from "styled-components";

const GlobalStyle = createGlobalStyle`
text-decoration: none;

&:focus, &:hover, &:visited, &:link, &:active {
    text-decoration: none;
}
`;
const theme = createTheme({
  palette: {
    primary: {
      main: "#72A1A6",
      contrastText: "#fff",
    },
    side: {
      main: "#E5F3F5",
    },
    side2: {
      main: "#F0E5DE",
    },
    disabled: {
      main: "#D9D4CF",
    },
    disabled2: {
      main: "#7C7877",
    },
    error: {
      main: "#E64C3C",
    },
  },
});
function App() {
  return (
    <ThemeProvider theme={theme}>
      <React.Fragment>
        <GlobalStyle />
        <CssBaseline />
        <Container fixed disableGutters={true} maxWidth="lg">
          <Router>
            <Header />
            <Routes>
              {/* ========= client ========== */}
              {/* 비밀번호 찾기 */}
              <Route path="/findpw" element={<FindPw />} />
              {/* 로그인 */}
              <Route path="/login" element={<Login />} />

              {/* 병원리스트 */}
              <Route path="/pslist/:seq" element={<PsList />} />
              {/* 회원가입 */}
              <Route path="/register" element={<Register />} />
              {/* 상세페이지 */}
              <Route path="/detail/:id" element={<Detail />} />
              {/* 나의 예약 */}
              <Route path="/myreservation" element={<MyReservation />} />
              {/* 나의 정보 */}
              <Route path="/mypagecl" element={<MypageCl />} />
              {/* 나의 활동 */}
              <Route path="/myactivity" element={<MyActivity />} />
              {/* 나의 견적서 */}
              <Route path="/myestimate" element={<MyEstimate />} />

              {/* ========= hospital ========== */}
              {/* 병원 회원가입 */}
              <Route path="/psregist" element={<PsRegister />} />
              {/* 병원 마이페이지 */}
              <Route path="/mypageho" element={<MypageHo />} />
              {/* 병원 예약관리 */}
              <Route path="/myreservationho" element={<MyReservationHo />} />

              {/* 메인 */}
              <Route path="/" element={<Home />} />
              {/* 모달 테스트용 페이지욤.. */}
              <Route path="/test" element={<ModalTest />} />
            </Routes>
          </Router>
        </Container>
      </React.Fragment>
    </ThemeProvider>
  );
}

export default App;
