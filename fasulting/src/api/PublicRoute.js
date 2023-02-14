import { useSelector } from "react-redux";
import { Route, Navigate, Routes } from "react-router-dom";
import ForbiddenPage from "../pages/ForbiddenPage";

const PublicRoute = ({ component: Component, ...rest }) => {
  const authenticated = useSelector(state => state.authToken.authenticated)
  
  return (
    authenticated ? <ForbiddenPage/> : Component
  );
};

export default PublicRoute;