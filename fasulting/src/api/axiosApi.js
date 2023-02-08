import axios from "axios";

const axiosAPi = axios.create({ baseURL: process.env.REACT_APP_URL });
export default axiosAPi;
