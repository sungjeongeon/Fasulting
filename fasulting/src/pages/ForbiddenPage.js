import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import Link from "@mui/material/Link";
import { useSelector } from "react-redux";
function ForbiddenPage () {
  // const nowLogin = useSelector(state => state.authToken.authenticated)
  const nowUser = useSelector(state => state.user)
  const isUser =  nowUser.userSeq === "" ? false : true 
  const isAdmin = nowUser.adminYn === false ? false : true
  
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        position: "absolute",
        top: "50%",
        left: "50%",
        transform: "translate(-50%, -50%)",
      }}
    >
      <img
        style={{
          width: "25rem",
        }}
        src="/assets/images/403Fobidden.png"
        alt="403 Forbidden"
      />
      <h2
        style={{
          margin: "0",
        }}
      >
        접근할 수 없는 페이지입니다 :(
      </h2>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          margin: "0",
          color: "#72a1a6",
        }}
      >
        <ArrowBackIosIcon fontSize="small" />
        { 
          isUser ? 
            isAdmin ? 
            <Link href="/admin">
              <h5>관리자 페이지로 돌아가기</h5>
            </Link> :
            <Link href="/">
              <h5>메인페이지로 돌아가기</h5>
            </Link> 
            : 
          <Link href="/mypageho">
            <h5>마이페이지로 돌아가기</h5>
          </Link>
        }
      </div>
    </div>
  )
}
export default ForbiddenPage;