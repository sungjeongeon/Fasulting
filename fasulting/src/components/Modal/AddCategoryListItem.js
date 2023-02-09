import React from "react"
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Collapse from '@mui/material/Collapse';
import SubdirectoryArrowRightIcon from '@mui/icons-material/SubdirectoryArrowRight';
import Checkbox from '@mui/material/Checkbox';
import { useEffect } from "react";
import { useState } from "react";

function AddCategoryListItem ({mainId, mainName, subList, selectedSubIdxList, increaseCnt, decreaseCnt, pushCtg, spliceCtg}) {
  
  // 서버에 보낼 거 (기존 select -> selectedSubList)
  // selectedSubList가 체크 박스 선택 여부에 따라서 바뀜 => 현재 (하위 컴포넌트)
  // 등록 버튼 누르면 axios 요청 (put) => 부모 컴포넌트


  // 체크박스 state 생성 + 토글 함수 생성
  const [checked, setChecked] = React.useState(selectedSubIdxList);
  const handleToggle = (value) => () => {
    const currentIndex = checked.indexOf(value);
    const newChecked = [...checked];
    // console.log(subList[value])


    if (currentIndex === -1) {
      increaseCnt()
      newChecked.push(value);
      pushCtg(subList[value])
      // console.log(copyCtg)

    } else {
      decreaseCnt()
      newChecked.splice(currentIndex, 1);
      spliceCtg(subList[value])
    }

    setChecked(newChecked);
  };
  const currentopen = selectedSubIdxList.length === 0 ? false : true
  const [open, setOpen] = React.useState(currentopen);
  const handleClick = () => {
    setOpen((current) => !current)
  };

  return (
    <div>
      <ListItemButton 
      onClick={handleClick}
      sx={ open ? {background: '#E5F3F5'} : null}
      >
        <ListItemText primary={mainName} />
      </ListItemButton>
      {subList.map((sub, index) => {
        const labelId = `${mainId}-${index}`
        return (
          <Collapse in={open} timeout="auto" unmountOnExit
            key={labelId}
          >
            <List component="div" disablePadding>
              <ListItemButton sx={{ pl: 4 }} onClick={handleToggle(index)} dense>
                <ListItemIcon>
                  <SubdirectoryArrowRightIcon />
                </ListItemIcon>
                <ListItemText id={labelId} primary={sub} />
                <ListItemIcon>
                  <Checkbox
                    edge="end"
                    checked={checked.indexOf(index) !== -1}
                    tabIndex={-1}
                    disableRipple
                    inputProps={{ 'aria-labelledby': labelId }}
                  />
                </ListItemIcon>
              </ListItemButton>
            </List>
          </Collapse>
        )
      })}
      </div>
  )
}

export default AddCategoryListItem