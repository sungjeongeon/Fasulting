import { useSelector } from "react-redux";
import { Route, Navigate, Routes } from "react-router-dom";
import ForbiddenPage from "../pages/ForbiddenPage";

const PublicRoute = ({ authenticated, component: Component }) => {
  const loading = useSelector(state => state.authToken.loading)
  console.log(loading)
  
  // 로그인한 상태에서는 접근 불가능한 페이지 (로그인, 회원가입)
  // 로그인 후 로딩에 대해서도 component 그대로 보여주기
  return (
    authenticated? (loading ? Component : <ForbiddenPage/>) : Component
  );
};

export default PublicRoute;