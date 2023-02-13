import * as React from 'react';
import ListSubheader from '@mui/material/ListSubheader';
import List from '@mui/material/List';
import styles from "./AddCategory.module.css";
import AddCategoryListItem from './AddCategoryListItem';
// import DisabledByDefaultOutlinedIcon from '@mui/icons-material/DisabledByDefaultOutlined';
import CloseIcon from "@mui/icons-material/Close";
import propTypes from "prop-types"
import { useEffect } from 'react';
import { useState } from 'react';
import axiosAPi from '../../api/axiosApi';

export default function AddCategory({ModalStateChange, ctg_list, setUpdateCtg, psSeq}) {
  // 카테고리 데이터 생성 
  const categorylists = [
    {
      id: 1,
      name: "눈 성형",
      sub_ctg: [ "쌍꺼풀", "눈매교정", "안검하수", "트임성형", "눈썹거상", "지방제거", "지방재배치"]
    },
    {
      id: 2,
      name: "코 성형",
      sub_ctg: [ "콧대 성형", "콧등 성형", "코끝 성형", "비중격 수술", "콧볼 축소", "이물질 제거"]
    },
    {
      id: 3,
      name: "쁘띠",
      sub_ctg: [ "보톡스", "필러", "동안주사", "물광주사"]
    },
    {
      id: 4,
      name: "안티에이징",
      sub_ctg: [ "리프팅", "주름", "상안검, 하안검", "다크서클", "지방이식", "안면거상술"]
    },
    {
      id: 5,
      name: "안면윤곽",
      sub_ctg: [ "사각턱", "광대", "턱끝", "이마", "양악"]
    },
    {
      id: 6,
      name: "모발이식",
      sub_ctg: [ "절개", "비절개"]
    },
    {
      id: 7,
      name: "남자 성형",
      sub_ctg: [ "남자 눈 성형", "남자 코 성형", "남자 안면윤곽"]
    },
    {
      id: 8,
      name: "재수술",
      sub_ctg: [ "눈 재수술", "코 재수술", "안면윤곽 재수술" ]
    },

    
  ]

  const ctgcount = ctg_list.length
  // 선택된 서브 카테고리 총 개수
  const [totalcnt, setTotalCnt] = React.useState(ctgcount)
  
  const increaseCnt = () => {
    setTotalCnt(totalcnt + 1)
  }
  const decreaseCnt = () => {
    setTotalCnt(totalcnt - 1)
  }
  
  // check박스에 따라 서브 카테고리 +-
  const [copyCtg, setCopyCtg] = useState(ctg_list)
  const pushCtg = (ctg) => {
    setCopyCtg([...copyCtg, ctg])
    // console.log(copyCtg)
  }
  const spliceCtg = (ctg) => {
    const notSelect = copyCtg.filter((copyitem) => copyitem !== ctg)
    setCopyCtg(notSelect)
    // console.log(copyCtg)
  }

  // 전체 카테고리에서 총 선택된 서브 카테고리 (서버에 보낼 것) => axios
  const onClickHandler = () => {
    axiosAPi.put('/ps/category', {
      "seq": psSeq,
      "subCategoryList": copyCtg,
    })
    .then(setUpdateCtg(copyCtg))
    .catch(err => console.log(err))
  }



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
        // eslint-disable-next-line array-callback-return
        const selectedSubListAll = main.sub_ctg.map((sub, subidx) => {
          if (ctg_list.includes(sub)) {
            return subidx
          }
        })
        const selectedSubIdxList = selectedSubListAll.filter((item) => item !== undefined)
        return (
          <AddCategoryListItem
            key={index}
            mainId={main.id}
            mainName={main.name}
            subList={main.sub_ctg}
            // selectedSubList={ctg_list}
            selectedSubIdxList={selectedSubIdxList}
            increaseCnt={increaseCnt}
            decreaseCnt={decreaseCnt}
            pushCtg={pushCtg}
            spliceCtg={spliceCtg}
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
        onClick={() => {
          ModalStateChange()
          onClickHandler()
        }}
        >
        {totalcnt}개 서비스 등록
        </button>
        }
      </div>
    </List>
    </div>
  );
}