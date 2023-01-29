import React, { useState } from "react";
import MainCategoryListItemSmall from "./MainCategoryListItemSmall";
import styles from "./MainCategoryListSmall.module.css"

function MainCategoryListSmall() {
  const [select, setSelect] = useState(0)
  function onClick(id) {
    setSelect(id)
  }

  const categorylist = [
    {
      id: 1,
      src: 'assets/images/1_eye.png',
      text: '눈'
    },
    {
      id: 2,
      src: 'assets/images/2_nose.png',
      text: '코'
    },
    {
      id: 3,
      src: 'assets/images/3_petit.png',
      text: '쁘띠'
    },
    {
      id: 4,
      src: 'assets/images/4_facial.png',
      text: '안티에이징'
    },
    {
      id: 5,
      src: 'assets/images/5_antiaging.png',
      text: '안면윤곽'
    },
    {
      id: 6,
      src: 'assets/images/6_hair.png',
      text: '모발이식'
    },
    {
      id: 7,
      src: 'assets/images/7_man.png',
      text: '맨즈'
    },
    {
      id: 8,
      src: 'assets/images/8_revision.png',
      text: '재수술'
    },
  ]

  return (
    <div className={styles.flex}>
      {categorylist.map((c, index) => (
        <MainCategoryListItemSmall
          key={index}
          id={c.id}
          src={c.src}
          text={c.text}
          onClick={onClick}
          select={select}
        />
      ))}
    </div>
  )
}

export default MainCategoryListSmall;