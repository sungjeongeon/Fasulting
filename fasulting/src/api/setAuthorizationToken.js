import axios from "axios";

//토큰이 있으면 header에 포함시키고, 없으면 그 부분을 지우는 함수.
export default function setAuthorizationToken(token) {
  if (token) {
    axios.defaults.headers.common["Authorization"] = `${token}`;
  } else {
    delete axios.defaults.headers.common["Authorization"];
  }
}
