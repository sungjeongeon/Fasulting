import * as React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import IconButton from "@mui/material/IconButton";
import PowerSettingsNewIcon from "@mui/icons-material/PowerSettingsNew";

import InputLabel from "@mui/material/InputLabel";
import InputAdornment from "@mui/material/InputAdornment";
import FormHelperText from "@mui/material/FormHelperText";
import Input from "@mui/material/Input";
import FormControl from "@mui/material/FormControl";

import Chip from "@mui/material/Chip";
import Stack from "@mui/material/Stack";
import Box from "@mui/material/Box";

import styles from "./LeaveConsulting.module.css";
import { useState } from "react";

export default function LeaveConsulting({ leaveSession }) {
  const [open, setOpen] = React.useState(false);
  const [content, setContent] = useState("");

  const handleClickOpen = () => {
    setOpen(true);
    // 상담 아이디 요청 api
  };

  const handleClose = () => {
    setOpen(false);
  };

  const contentChanged = (e) => {
    setContent(e.target.value);
  };

  const submitReport = () => {
    leaveSession();
    // 소견서 제출 api ( + 상담 id )
    // 예약 스케줄 페이지로 라우팅
  };

  const subCategory = ["쌍커풀", "앞트임"];

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
