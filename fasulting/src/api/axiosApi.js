import axios from "axios";

const axiosApi = axios.create({
  baseURL: "http://i8e106.p.ssafy.io:8080/",
});
export default axiosApi;
