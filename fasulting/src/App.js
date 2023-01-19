import { BrowserRouter as Router, Routes, Route, BrowserRouter } from "react-router-dom";
import FindPw from "./pages/client/FindPw";
import Home from "./pages/client/Home";
import Login from "./pages/client/Login";
import PsList from "./pages/client/PsList";
import Register from "./pages/client/Register";
import Header from "./components/Header";
import { Link } from "react-router-dom";

function App() {
  return (
    <Router>
      <Link to="/">Home</Link> | <Link to="/login">login</Link> | <Link to="/findpw">findpw</Link> | <Link to="/register">register</Link> | <Link to="/pslsit">pslsit</Link> |
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
      </Routes>
    </Router>
  );
}

export default App;
