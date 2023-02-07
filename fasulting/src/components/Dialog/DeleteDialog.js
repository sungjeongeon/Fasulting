import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import axios from 'axios';

export default function DeleteDialog({DialogStateChange, doctorId}) {
  const [open, setOpen] = React.useState(true);

  const handleClose = () => {
    setOpen(false);
  };

  //axios 삭제 요청
  // const deleteDoc = () => {
  //   axios.delete(`/ps/doctor/${doctorId}`)
  //   .then(res => console.log("삭제"))
  //   .catch(err => console.log(err))
  // }

  return (
    <div>
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"삭제하시겠습니까?"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            의사 정보가 완전히 삭제되어 복구가 불가능합니다.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => {
            handleClose(); 
            DialogStateChange(); 
          }}
          >취소</Button>
          <Button onClick={() => {
            handleClose(); 
            DialogStateChange();
          }} autoFocus>
            삭제
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
