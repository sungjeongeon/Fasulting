import React from "react";
import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";
import ForbiddenPage from "../pages/ForbiddenPage";
import { toast, ToastContainer } from "react-toastify";

function PrivateRoute({ authenticated, role, component: Component }) {
  // 병원 회원, 일반 회원 private route를 위한 정보
  const ps = useSelector(state => state.ps)
  const user = useSelector(state => state.user)

  const isAdmin = user.adminYn ? true : false
  const isPs = ps.psSeq ? true : false

  const isPossible = (role, isAdmin, isPs) => {
    switch(role) {
      case "user":
        return (isAdmin ? false : (isPs ? false : true));
      case "ps":
        return (isPs ? true : false);
      case "admin":
        return (isAdmin ? true : false);
      default:
        return true
    }
  }

  const toastAlert = () => {
    toast.error(
      <h3>
        로그인 후 이용해주세요 !
      </h3>,
      {
        position: toast.POSITION.TOP_CENTER,
        autoClose: false,
      }
    );
  }
  // 로그인 X 상태 -> 로그인 페이지, 로그인 O -> 일반, 병원, 관리자인지에 따라 (403)
  return authenticated ? (
    isPossible(role, isAdmin, isPs) ? Component : <ForbiddenPage/>
  ) : (
    <Navigate to="/login" {...toastAlert()} />
  );
}

export default PrivateRoute;
