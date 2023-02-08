import * as React from 'react';
import ListSubheader from '@mui/material/ListSubheader';
import List from '@mui/material/List';
import styles from "./AddCategory.module.css";
import AddCategoryListItem from './AddCategoryListItem';
// import DisabledByDefaultOutlinedIcon from '@mui/icons-material/DisabledByDefaultOutlined';
import CloseIcon from "@mui/icons-material/Close";
import propTypes from "prop-types"
import { useEffect } from 'react';

export default function AddCategory({ModalStateChange, ctg_list}) {
  // 카테고리 데이터 생성 
  const categorylists = [
    {
      id: 1,
      name: "눈 성형",
      sub_ctg: [
        {
          id: 1,
          name: "쌍커풀"
        },
        {
          id: 2,
          name: "눈매교정"
        },
        {
          id: 3,
          name: "안검하수"
        },
        {
          id: 4,
          name: "트임성형"
        },
        {
          id: 5,
          name: "눈썹거상"
        },
        {
          id: 6,
          name: "지방제거"
        },
        {
          id: 7,
          name: "눈밑지방 재배치"
        },
      ]
    },
    {
      id: 2,
      name: "코 성형",
      sub_ctg: [
        {
          id: 1,
          name: "콧대 성형"
        },
        {
          id: 2,
          name: "콧등 성형"
        },
        {
          id: 3,
          name: "코끝 성형"
        },
        {
          id: 4,
          name: "비중격 수술"
        },
        {
          id: 5,
          name: "콧볼 축소"
        },
        {
          id: 6,
          name: "이물질 제거술"
        },
        {
          id: 7,
          name: "눈밑지방 재배치"
        },
      ]
    },
    {
      id: 3,
      name: "쁘띠",
      sub_ctg: [
        {
          id: 1,
          name: "보톡스"
        },
        {
          id: 2,
          name: "필러"
        },
        {
          id: 3,
          name: "동안주사"
        },
        {
          id: 4,
          name: "물광주사"
        },
      ]
    },
    {
      id: 4,
      name: "안티에이징",
      sub_ctg: [
        {
          id: 1,
          name: "리프팅"
        },
        {
          id: 2,
          name: "주름"
        },
        {
          id: 3,
          name: "상안검 / 하안검"
        },
        {
          id: 4,
          name: "다크서클"
        },
        {
          id: 5,
          name: "지방이식"
        },
        {
          id: 6,
          name: "안면거상술"
        },
      ]
    },
    {
      id: 5,
      name: "안면윤곽",
      sub_ctg: [
        {
          id: 1,
          name: "사각턱"
        },
        {
          id: 2,
          name: "광대"
        },
        {
          id: 3,
          name: "턱끝"
        },
        {
          id: 4,
          name: "이마"
        },
        {
          id: 5,
          name: "양악"
        },
      ]
    },
    {
      id: 6,
      name: "모발이식",
      sub_ctg: [
        {
          id: 1,
          name: "절개"
        },
        {
          id: 2,
          name: "비절개"
        },
      ]
    },
    {
      id: 7,
      name: "남자 성형",
      sub_ctg: [
        {
          id: 1,
          name: "남자 눈성형"
        },
        {
          id: 2,
          name: "남자 코성형"
        },
        {
          id: 3,
          name: "남자 안면윤곽"
        },
      ]
    },
    {
      id: 8,
      name: "재수술",
      sub_ctg: [
        {
          id: 1,
          name: "눈 재수술"
        },
        {
          id: 2,
          name: "코 재수술"
        },
        {
          id: 3,
          name: "안면윤곽 재수술"
        },
      ]
    },

    
  ]

  // console.log(ctg_list)
  const ctgcount = ctg_list.reduce((acc, cur, i) => {
    return acc + cur.sub_ctg.length
  }, 0)
  // console.log(ctgcount)
  // 선택된 서브 카테고리 총 개수
  const [totalcnt, setTotalCnt] = React.useState(ctgcount)
  
  const increaseCnt = () => {
    setTotalCnt(totalcnt + 1)
  }
  const decreaseCnt = () => {
    setTotalCnt(totalcnt - 1)
  }

  // // 전체 카테고리에서 총 선택된 서브 카테고리 (서버에 보낼 것)
  // const [totalChecked, setTotalChecked] = React.useState([]);
  // // const newTotalChecked = [...totalChecked]
  // const updateItem = (mainId, updatelist) => {
  //   setTotalChecked(
  //     [...totalChecked, {
  //       id: mainId,
  //       sub: updatelist
  //     }]
  //   )
  // }
  // console.log(totalChecked)
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
          <CloseIcon
          fontSize="medium"
          onClick={ModalStateChange}
          color="error"
          className={styles.back}
        />
          항목 추가
        </ListSubheader>
      }
    >
      {categorylists.map((main, index) => {
        const currentmain = ctg_list.filter(ctg => {
          return ctg.id === main.id
        })
        // name 빼고 subList 들고오기 (인덱스)
        const selectedSubObj = currentmain[0] ? currentmain[0].sub_ctg : []
        const selectedSubList = selectedSubObj.map((subobj) => {
          return subobj.id-1
        })
        // console.log(currentmain)
        return (
          <AddCategoryListItem
            key={index}
            mainId={main.id}
            mainName={main.name}
            subList={main.sub_ctg}
            selectedMain={currentmain}
            selectedSubList={selectedSubList}
            increaseCnt={increaseCnt}
            decreaseCnt={decreaseCnt}
            // updateItem={() => updateItem(main.id, selectedSubList)}
            // updateChecked={updateChecked}
          />
        )
      })}

      <div className={styles.center}>
        { totalcnt === 0 ? <button
          className={styles.before}
          disabled
          >
          서비스를 등록해주세요
        </button> :
        <button
        className={styles.after}
        onClick={ModalStateChange}
        >
        {totalcnt}개 서비스 등록
        </button>
        }
      </div>
    </List>
    </div>
  );
}