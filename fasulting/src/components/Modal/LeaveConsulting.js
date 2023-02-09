import * as React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import PowerSettingsNewIcon from "@mui/icons-material/PowerSettingsNew";

import InputAdornment from "@mui/material/InputAdornment";
import Input from "@mui/material/Input";
import FormControl from "@mui/material/FormControl";

import Chip from "@mui/material/Chip";
import Box from "@mui/material/Box";

import styles from "./LeaveConsulting.module.css";
import { useState } from "react";
import { useNavigate } from "react-router";
import axiosAPi from "../../api/axiosApi";

export default function LeaveConsulting({ leaveSession }) {
  const navigate = useNavigate();
  const [open, setOpen] = React.useState(false);
  const [img, setImg] = useState([]);
  const [content, setContent] = useState("");
  const [cost, setCost] = useState(0);
  const subCategory = ["쌍커풀", "앞트임"];

  const submitReport = async () => {
    leaveSession();
    // 소견서 제출 api ( + 상담 id )
    // {
    //   "reservationSeq" : "예약 seq",
    //   "reportContent" : "상담 소견",
    //   "reportEstimate" : "예상 견적",
    //   "afterImg" : "after 사진 파일"
    // }
    const formData = new FormData();
    formData.append("reservationSeq", 47);
    formData.append("reportContent", content);
    formData.append("reportEstimate", cost);
    formData.append("afterImg", img);
    try {
      await axiosAPi.post("/ps-consulting/result", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          //Authorization: localStorage.getItem("access_token")
        },
        withCredentials: false,
      });
    } catch (e) {
      // FormData의 key 확인
      for (let key of formData.keys()) {
        console.log(key);
      }

      // FormData의 value 확인
      for (let value of formData.values()) {
        console.log(value);
      }
      console.log(e);
    }

    // 예약 스케줄 페이지로 라우팅
    // navigate("/myreservationho");
  };

  const handleClickOpen = () => {
    setOpen(true);
    // 상담 아이디 요청 api
  };

  const handleClose = () => {
    setOpen(false);
  };

  // 이미지 업로드
  //파일(이미지) 업로드
  const onChange = (e) => {
    const uploadimg = e.target.files[0];
    setImg(uploadimg);
    // const formData = new FormData();
    // formData.append("file", img);
  };

  const contentChanged = (e) => {
    setContent(e.target.value);
  };
  const costChanged = (e) => {
    setCost(e.target.value);
  };

  return (
    <div>
      <PowerSettingsNewIcon fontSize="large" onClick={handleClickOpen} />

      <Dialog open={open} onClose={handleClose}>
        <div className={styles.modalbody}>
          <DialogTitle>OOO 님 소견서</DialogTitle>
          <DialogContent>
            <DialogContentText>
              상담 내용을 기반으로 추천 시술, 견적 등 상담자에게 제공될 소견을
              작성해주세요.
            </DialogContentText>
            {/* after 사진 첨부 */}
            <DialogContentText sx={{ mt: 5 }}>
              After 사진 파일첨부
            </DialogContentText>
            <div>
              {/* <p className={styles.ptag}>반드시 정면이 나온 사진을 첨부해주세요</p> */}
              <label htmlFor="inputfile">파일 선택</label>
              <input
                type="file"
                id="inputfile"
                accept="image/jpg,impge/png,image/jpeg,image/gif"
                name="profile_img"
                onChange={onChange}
                style={{ display: "none" }}
              ></input>
              {img.name}
            </div>
            <DialogContentText sx={{ mt: 5 }}>상담 항목</DialogContentText>
            <Box sx={{ display: "flex", flexWrap: "wrap", gap: 0.5 }}>
              {subCategory.map((value) => (
                <Chip key={value} label={value} />
              ))}
            </Box>
            {/* 상담 소견 작성 */}
            <TextField
              sx={{ mt: 2 }}
              autoFocus
              margin="dense"
              id="name"
              label="상담 소견"
              value={content}
              onChange={contentChanged}
              multiline
              fullWidth
              variant="standard"
            />
            {/* 견적 금액 작성 */}
            <div className={styles.money}>
              <FormControl
                variant="standard"
                sx={{ m: 1, mt: 4, width: "13ch" }}
                value={cost}
                onChange={costChanged}
              >
                <Input
                  id="standard-adornment-weight"
                  endAdornment={
                    <InputAdornment position="end">만원</InputAdornment>
                  }
                  aria-describedby="standard-weight-helper-text"
                  inputProps={{
                    "aria-label": "weight",
                  }}
                />
              </FormControl>
            </div>
          </DialogContent>
          <DialogActions sx={{ mx: 3 }}>
            <Button variant="text" onClick={handleClose} sx={{ mx: 3 }}>
              돌아가기
            </Button>
            <Button variant="contained" onClick={submitReport}>
              제출
            </Button>
          </DialogActions>
        </div>
      </Dialog>
    </div>
  );
}
