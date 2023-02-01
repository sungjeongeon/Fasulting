import axios from "axios";

export const customTodoAxios = axios.create({
  baseURL: "http://localhost:8080/",
});
