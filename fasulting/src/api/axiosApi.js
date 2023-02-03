import axios from "axios";

const axiosApi = axios.create({
  //http://localhost:8080/
  baseURL: "http://i8e106.p.ssafy.io:8080/",
});
export default axiosApi;
