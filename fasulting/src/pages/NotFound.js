import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import Link from "@mui/material/Link";

function NotFound() {
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
        src="/assets/images/notFound404.png"
      />
      <h2
        style={{
          margin: "0",
        }}
      >
        페이지를 찾을 수 없어요 :(
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
        <Link href="/">
          <h5>메인페이지로 돌아가기</h5>
        </Link>
      </div>
    </div>
  );
}

export default NotFound;
