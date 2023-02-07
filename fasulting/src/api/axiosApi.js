import axios from "axios";

const axiosApi = axios.create({
  // baseURL: "http://localhost:8080/",
  baseURL: "http://i8e106.p.ssafy.io:8080/",
  //baseURL: "https://fasulting.hotsix.duckdns.org/api/",
});
export default axiosApi;
