import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import { useDispatch } from 'react-redux';
import { cancel } from "../../redux/appointments"

export default function CancelDialog({ReservationManage, modalClose}) {
  const [dialogOpen, setDialogOpen] = React.useState(true)

  const handleClose = () => {
    setDialogOpen(false);
  };
  const dispatch = useDispatch()

  return (
    <div>
      <Dialog
        open={dialogOpen}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"예약을 취소하시겠습니까?"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            예약 취소 시 예약 정보가 완전히 삭제됩니다. <br/>
            예약자와 상의 후 취소해주시길 바랍니다.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button 
            onClick={() => {
              dispatch(cancel(ReservationManage.id));
              modalClose();
              handleClose();
            }}
          >
            네
          </Button>
          <Button onClick={handleClose} autoFocus>
            아니오
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
