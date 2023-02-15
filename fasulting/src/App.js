// import "devextreme/dist/css/dx.light.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import FindPw from "./pages/client/FindPw";
import Home from "./pages/client/Home";
import Login from "./pages/client/Login";
import PsList from "./pages/client/PsList";
import Register from "./pages/client/Register";
import Detail from "./pages/client/Detail";
import MyActivity from "./pages/client/MyActivity.js";
import Download from "./pages/hospital/Download.js";
import { Container } from "@mui/system";
import * as React from "react";
import CssBaseline from "@mui/material/CssBaseline";
import { ThemeProvider } from "@emotion/react";
import { createTheme } from "@mui/material";
import MyReservation from "./pages/client/MyReservation";
import MyEstimate from "./pages/client/MyEstimate";
import MypageHo from "./pages/hospital/MypageHo";
import PsRegister from "./pages/hospital/PsRegister";
import MyReservationHo from "./pages/hospital/MyReservationHo";
import { createGlobalStyle } from "styled-components";
import OpenViduRoom from "./pages/OpenViduRoom";
import { SignLanguage } from "@mui/icons-material";
import AdminMain from "./pages/admin/AdminMain";
import PrivateRoute from "./api/PrivateRoute";
import { useSelector } from "react-redux";
import NotFound from "./pages/NotFound";
import PublicRoute from "./api/PublicRoute";
import ForbiddenPage from "./pages/ForbiddenPage";

const GlobalStyle = createGlobalStyle`
text: {
  fontSize: 20,
  font-weight: 400;
  font-style: normal;
  font-family: 'Pretendard-Regular';
  src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
},
text-decoration: none;

    &:focus, &:hover, &:visited, &:link, &:active {
        text-decoration: none;
    }
`;
const theme = createTheme({
  typography: {
    fontFamily: "Pretendard-Regular",
  },
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
  const token = useSelector((state) => state.authToken);



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
              <Route path="/findpw" element={<FindPw/>} 
              />
              {/* 로그인 - 로그인된 회원 접근 금지 => 403페이지로*/}
              <Route path="/login" element={
                <PublicRoute 
                  authenticated={token.authenticated}
                  component={<Login/>}
                />
              } />
              {/* 병원리스트 */}
              <Route path="/pslist/:seq" element={<PsList />} />
              {/* 회원가입 */}
              <Route path="/register" element={
                <PublicRoute 
                  authenticated={token.authenticated}
                  component={<Register/>}
                />
              } />
              {/* 상세페이지 */}
              <Route
                path="/detail/:psSeq"
                element={
                  <PrivateRoute
                    role="user"
                    // authenticated={token.accessToken}
                    authenticated={token.authenticated}
                    component={<Detail />}
                  />
                }
              />
              {/* 나의 예약 */}
              <Route path="/myreservation" element={
                <PrivateRoute
                  role="user"
                  authenticated={token.authenticated}
                  component={<MyReservation />}
                />
              } 

              />
              {/* 나의 활동 */}
              <Route path="/myactivity" element={
                <PrivateRoute
                  role="user"
                  authenticated={token.authenticated}
                  component={<MyActivity />}
              />
              } 
              
              />
              {/* 나의 견적서 */}
              <Route path="/myestimate/:consultSeq" element={
                <PrivateRoute
                  role="user"
                  authenticated={token.authenticated}
                  component={<MyEstimate />}
              />
              } 

              />
              {/* ========= hospital ========== */}
              {/* 병원 회원가입 */}
              <Route path="/psregist" element={
                <PublicRoute 
                  authenticated={token.authenticated}
                  component={<PsRegister/>}
                />
              } />
              {/* 병원 마이페이지 */}
              <Route path="/mypageho" element={
                <PrivateRoute
                  role="ps"
                  authenticated={token.authenticated}
                  component={<MypageHo />}
                />
                } 
              />
              {/* 병원 예약관리 */}
              <Route path="/myreservationho" element={
                <PrivateRoute
                  role="ps"
                  authenticated={token.authenticated}
                  component={<MyReservationHo />}
              />
              } />
              {/* 병원 프로그램 다운로드 */}
              <Route path="/ps/download" element={
                <PrivateRoute
                  role="ps"
                  authenticated={token.authenticated}
                  component={<Download />}
                />
              } />
              {/* ========= admin ========== */}
              <Route path="/admin" element={
                <PrivateRoute
                  role="admin"
                  authenticated={token.authenticated}
                  component={<AdminMain />}
              />} />
              {/* 상담page */}
              <Route path="/consult" element={<OpenViduRoom />} />
              {/* 메인 */}
              <Route path="/" element={<Home />} />
              {/* 404 */}
              <Route path="/403" element={<ForbiddenPage />}/>
              <Route path="*" element={<NotFound />}/>
            </Routes>
          </Router>
        </Container>
      </React.Fragment>
    </ThemeProvider>
  );
}

export default App;
