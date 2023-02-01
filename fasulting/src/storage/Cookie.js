//Refresh Token
import Cookies from "react-cookie/cjs/Cookies";
const cookies = new Cookies();

//Refresh Token을 Cookie에 저장
export const setRefreshToken = (refreshToken) => {
  const today = new Date();
  const expireDate = today.setDate(today.getDate() + 7);

  return cookies.set("refresh_token", refreshToken, {
    sameSite: "strict",
    path: "/",
    expires: new Date(expireDate),
  });
};

//Cookie에 저장된 Refresh Token 가져오기
export const getCookieToken = () => {
  return cookies.get("refresh_token");
};

//Cookie 삭제
export const removeCookieToken = () => {
  return cookies.remove("refresh_token", { sameSite: "strict", path: "/" });
};
