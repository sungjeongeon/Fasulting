import React from "react";
import IconButton from "@mui/material/IconButton";
import ContentPasteSearchIcon from "@mui/icons-material/ContentPasteSearch";

import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import axiosAPi from "../../api/axiosApi";
import { useSelector } from "react-redux";

function AdminSignupAcceptModal({ ps }) {
  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const adminSeq = useSelector((store) => store.user.userSeq);
  const acceptSignUp = () => {
    // 관리자seq(=유저seq), 병원seq 로 api 요청
    console.log(ps.psSeq);
    axiosAPi
      .patch("/admin/account/ps", {
        adminSeq: adminSeq,
        psSeq: ps.psSeq,
      })
      .then((res) => {
        console.log("요청보냄");
        console.log(res);
      })
      .catch((e) => console.log(e));
    // 페이지 새로고침
    window.location.reload();
  };

  return (
    <>
      <IconButton edge="end" aria-label="comments" onClick={handleClickOpen}>
        <ContentPasteSearchIcon />
      </IconButton>

      <Dialog
        open={open}
        onClose={handleClose}
        fullWidth="sm"
        scroll="body"
        aria-labelledby="scroll-dialog-title"
        aria-describedby="scroll-dialog-description"
      >
        <DialogTitle id="scroll-dialog-title">제출 서류</DialogTitle>
        <DialogContent dividers={false}>
          <DialogContentText
            id="scroll-dialog-description"
            // ref={descriptionElementRef}
            tabIndex={-1}
            sx={{ mt: 3, mb: 4 }}
          >
            <img
              src={ps.profileImg}
              alt="프로필이미지"
              style={{ width: "100%" }}
            />
            <hr />
            id : {ps.psSeq}
            <br />
            병원명 : {ps.name}
            <br />
            원장 : {ps.director}
            <br />
            소개글 : {ps.intro}
            <br />
            카테고리(메인) : {ps.mainCategoryList.join(", ")}
            <br />
            카테고리(서브) : {ps.subCategoryList.join(", ")}
            <hr />
            우편번호 : {ps.zipcode}
            <br />
            주소 : {ps.address}
            <br />
            연락처 : {ps.number}
            <br />
            e-mail : {ps.email}
            <br />
            홈페이지 : {ps.homepage}
            <hr />
            사업자등록증 : {ps.registeration}
            <br />
            <img
              src={ps.registrationImg}
              alt="사업자등록증"
              style={{ width: "100%" }}
            />
          </DialogContentText>
        </DialogContent>
        <DialogActions sx={{ mx: 4, mb: 3 }}>
          <Button onClick={handleClose} sx={{ mx: 3 }}>
            돌아가기
          </Button>
          <Button variant="contained" onClick={acceptSignUp}>
            승인
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}

export default AdminSignupAcceptModal;
