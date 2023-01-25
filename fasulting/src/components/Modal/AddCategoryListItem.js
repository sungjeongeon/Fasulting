import React from "react"
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Collapse from '@mui/material/Collapse';
import SubdirectoryArrowRightIcon from '@mui/icons-material/SubdirectoryArrowRight';
import Checkbox from '@mui/material/Checkbox';

function AddCategoryListItem ({id, ctg, sublists}) {

  // 체크박스 state 생성 + 토글 함수 생성
  const [checked, setChecked] = React.useState([]);
  const handleToggle = (value) => () => {
  const currentIndex = checked.indexOf(value);
  const newChecked = [...checked];

  if (currentIndex === -1) {
    newChecked.push(value);
  } else {
    newChecked.splice(currentIndex, 1);
  }

  setChecked(newChecked);
  };

  const [open, setOpen] = React.useState(false);
  const handleClick = () => {
    setOpen((current) => !current)
  };

  return (
    <div>
      <ListItemButton 
      onClick={handleClick}
      sx={ open ? {background: '#E5F3F5'} : null}
      // sx={{background: '#E5F3F5'}}
      >
        <ListItemText primary={ctg} />
      </ListItemButton>
      {sublists.map((sub, index) => {
        const main = id
        const labelId = `${main}-${index}`
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