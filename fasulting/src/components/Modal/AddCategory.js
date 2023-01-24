import * as React from 'react';
import ListSubheader from '@mui/material/ListSubheader';
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Collapse from '@mui/material/Collapse';
import SubdirectoryArrowRightIcon from '@mui/icons-material/SubdirectoryArrowRight';
import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
import styles from "./AddCategory.module.css";

export default function AddCategory({ModalStateChange2}) {
  const [open1, setOpen1] = React.useState(false);
  const [open2, setOpen2] = React.useState(false);
  const [open3, setOpen3] = React.useState(false);
  const [open4, setOpen4] = React.useState(false);
  const [open5, setOpen5] = React.useState(false);
  const [open6, setOpen6] = React.useState(false);
  const [open7, setOpen7] = React.useState(false);
  const [open8, setOpen8] = React.useState(false);

  const [serviceAdd, setServiceAdd] = React.useState(0)

  const handleClick1 = () => {
    setOpen1(!open1);
  };
  const handleClick2 = () => {
    setOpen2(!open2);
  };
  const handleClick3 = () => {
    setOpen3(!open3);
  };
  const handleClick4 = () => {
    setOpen4(!open4);
  };
  const handleClick5 = () => {
    setOpen5(!open5);
  };
  const handleClick6 = () => {
    setOpen6(!open6);
  };
  const handleClick7 = () => {
    setOpen7(!open7);
  };
  const handleClick8 = () => {
    setOpen8(!open8);
  };

  return (
    <div className={styles.background}>
    <List
      sx={{ 
      width: '100%', 
      height: '34rem',
      maxWidth: '23rem', 
      overflow: 'auto',
      bgcolor: 'background.paper', 
      zIndex: 999,
      position: 'absolute', 
      top: '50%', 
      left: '50%', 
      transform: 'translate(-50%, -50%)',
      display: 'block',
    }}
      component="nav"
      aria-labelledby="nested-list-subheader"
      subheader={
        <ListSubheader component="div" id="nested-list-subheader"
          sx={{ color: 'black', fontSize: '1rem', fontWeight: 'bold', paddingTop: '1.2rem' }}
        >
          항목 추가
        </ListSubheader>
      }
    >
      <div>
      <ListItemButton onClick={handleClick1}>
        <ListItemText primary="눈 성형" />
        {open1 ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={open1} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="쌍꺼풀" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="눈매교정" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="안검하수" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="트임성형" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="눈썹거상" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="지방제거" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="눈밑지방 재배치" />
          </ListItemButton>
        </List>
      </Collapse>
      </div>
      <ListItemButton onClick={handleClick2}>
        <ListItemText primary="코 성형" />
        {open2 ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={open2} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="콧대 성형" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="콧등 성형" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="코끝 성형" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="비중격 수술" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="콧볼 축소" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="이물질 제거술" />
          </ListItemButton>
        </List>
      </Collapse>

      <ListItemButton onClick={handleClick3}>
        <ListItemText primary="쁘띠 성형" />
        {open3 ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={open3} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="보톡스" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="필러" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="동안주사" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="물광주사" />
          </ListItemButton>
        </List>
      </Collapse>

      <ListItemButton onClick={handleClick4}>
        <ListItemText primary="안티에이징" />
        {open4 ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={open4} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="리프팅" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="주름 성형" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="상안검 / 하안검" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="다크서클" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="지방이식" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="안면거상술" />
          </ListItemButton>
        </List>
      </Collapse>

      <ListItemButton onClick={handleClick5}>
        <ListItemText primary="안면윤곽" />
        {open5 ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={open5} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="사각턱" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="광대" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="턱끝 성형" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="이마 성형" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="양악 성형" />
          </ListItemButton>
        </List>
      </Collapse>

      <ListItemButton onClick={handleClick6}>
        <ListItemText primary="모발이식" />
        {open6 ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={open6} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="절개" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="비절개" />
          </ListItemButton>
        </List>
      </Collapse>

      <ListItemButton onClick={handleClick7}>
        <ListItemText primary="남자 성형" />
        {open7 ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={open7} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="눈 성형" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="코 성형" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="안면윤곽" />
          </ListItemButton>
        </List>
      </Collapse>


      <ListItemButton onClick={handleClick8}>
        <ListItemText primary="재수술" />
        {open8 ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={open8} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="눈 성형" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="코 성형" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemIcon>
              <SubdirectoryArrowRightIcon />
            </ListItemIcon>
            <ListItemText primary="안면윤곽" />
          </ListItemButton>
        </List>
      </Collapse>

      <div className={styles.center}>
        { serviceAdd === 0 ? <button
          className={styles.before}
          onClick={ModalStateChange2}
          >
          서비스를 등록해주세요
        </button> :
        <button
        className={styles.after}
        onClick={ModalStateChange2}
        >
        {serviceAdd}개 서비스 추가
        </button>
        }
      </div>
    </List>
    </div>
  );
}