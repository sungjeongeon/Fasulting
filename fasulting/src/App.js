
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Header from "./components/Header";
import FindPw from "./pages/client/FindPw";
import Home from "./pages/client/Home";
import Login from "./pages/client/Login";
import PsList from "./pages/client/PsList";
import Register from "./pages/client/Register";
import Reserve from "./pages/client/Reserve";
import Mypage from "./pages/client/Mypage";

function App() {
  return (
    <Router>
      <Link to="/">Home</Link> | <Link to="/login">login</Link> | <Link to="/findpw">findpw</Link> | <Link to="/register">register</Link> | <Link to="/pslsit">pslsit</Link> |
      <Header />
      <Routes>
        {/* 비밀번호 찾기 */}
        <Route path="/findpw" element={<FindPw />} />
        {/* 로그인 */}
        <Route path="/login" element={<Login />} />
        {/* 병원리스트 */}
        <Route path="/pslist" element={<PsList />} />
        {/* 회원가입 */}
        <Route path="/register" element={<Register />} />
        {/* 메인 */}
        <Route path="/" element={<Home />} />
        {/* 나의 예약 */}
        <Route path="/reserve" element={<Reserve />} />
        {/* 나의 활동 */}
        <Route path="/mypage" element={<Mypage />} />
      </Routes>
    </Router>
  );
}

export default App;
