import React from "react";
import IconButton from "@mui/material/IconButton";
import ManageSearchIcon from "@mui/icons-material/ManageSearch";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import { useSelector } from "react-redux";
import axiosAPi from "../../api/axiosApi";

function AdminReviewDeleteModal({ review }) {
  const adminSeq = useSelector((store) => store.user.userSeq);
  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };
  const deleteReview = () => {
    // 관리자seq(=유저seq), 리뷰seq 로 api 요청
    axiosAPi
      .patch("/admin/review", {
        adminSeq: adminSeq,
        reviewSeq: review.reviewSeq,
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
        <ManageSearchIcon />
      </IconButton>

      <Dialog
        open={open}
        onClose={handleClose}
        fullWidth="xs"
        scroll="body"
        aria-labelledby="scroll-dialog-title"
        aria-describedby="scroll-dialog-description"
      >
        <DialogTitle id="scroll-dialog-title">리뷰 내역</DialogTitle>
        <DialogContent dividers={false}>
          <DialogContentText
            id="scroll-dialog-description"
            tabIndex={-1}
            sx={{ mt: 3, mb: 4 }}
          >
            User id : {review.userSeq}
            <br />
            Ps id : {review.psSeq}
            <hr />
            Review id : {review.reviewSeq}
            <br />
            작성일자 : {review.regDate}
            <br />
            상담항목 : {review.subCategoryName.join(", ")}
            <hr />
            평점 : ★{review.point.toFixed(1)}
            <br />
            내용 : {review.content}
          </DialogContentText>
        </DialogContent>
        <DialogActions sx={{ mx: 4, mb: 3 }}>
          <Button color="error" onClick={handleClose} sx={{ mx: 3 }}>
            돌아가기
          </Button>
          <Button color="error" variant="contained" onClick={deleteReview}>
            삭제
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}

export default AdminReviewDeleteModal;
