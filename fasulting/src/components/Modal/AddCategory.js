import * as React from 'react';
import ListSubheader from '@mui/material/ListSubheader';
import List from '@mui/material/List';
import styles from "./AddCategory.module.css";
import AddCategoryListItem from './AddCategoryListItem';

export default function AddCategory({ModalStateChange2}) {
  // 카테고리 데이터 생성 
  const categorylists = [
    {
      id: 'eye',
      ctg: '눈 성형',
      sublists: [
        '쌍커풀', '눈매교정', '안검하수', '트임성형', '눈썹거상', '지방제거', '눈밑지방 재배치'
      ],
    },
    {
      id: 'nose',
      ctg: '코 성형',
      sublists: [
        '콧대 성형', '콧등 성형', '코끝 성형', '비중격 수술', '콧볼 축소', '이물질 제거술'
      ],
    },
    {
      id: 'petit',
      ctg: '쁘띠',
      sublists: [
        '보톡스', '필러', '동안주사', '물광주사'
      ],
    },
    {
      id: 'antiaging',
      ctg: '안티에이징',
      sublists: [
        '리프팅', '주름', '상안검 / 하안검', '다크서클', '지방이식', '안면거상술'
      ],
    },
    {
      id: 'contour',
      ctg: '안면윤곽',
      sublists: [
        '사각턱', '광대', '턱끝', '이마', '양악'
      ],
    },
    {
      id: 'hair',
      ctg: '모발이식',
      sublists: [
        '절개', '비절개'
      ],
    },
    {
      id: 'men',
      ctg: '남자 성형',
      sublists: [
        '눈 성형', '코 성형', '안면윤곽'
      ],
    },
    {
      id: 'reoperation',
      ctg: '재수술',
      sublists: [
        '눈 성형', '코 성형', '안면윤곽'
      ],
    },
  ]

  // 상태관리 필요 (컴포넌트마다 계산해야 하기 때문) => 리덕스 다시 공부하고 수정
  const [serviceAdd, setServiceAdd] = React.useState(0)

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
      {categorylists.map((item, index) => {
        return (
          <AddCategoryListItem
          key={index}
          id={item.id}
          ctg={item.ctg}
          sublists={item.sublists}
          />
        )})}

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