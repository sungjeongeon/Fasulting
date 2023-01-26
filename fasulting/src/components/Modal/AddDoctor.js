import React from "react"
import styles from "./AddDoctor.module.css"
import MainCategoryListSmall from "../Category/MainCategoryListSmall"
import CancelPresentationOutlinedIcon from '@mui/icons-material/CancelPresentationOutlined';

function AddDoctor({ModalStateChange}) {
  const basicsrc = "../../assets/images/doctorBasic.png"
  
  return (
    <div className={styles.background}>
      <div className={styles.modalbox}>
        <div className={styles.inside}>
          <div className={styles.nav}>
            <span className={styles.title}>의사등록</span>
            <CancelPresentationOutlinedIcon
              fontSize="large"
              onClick={ModalStateChange}
              color="action"
            />
          </div>
          <div className={styles.center}>
            <img src={basicsrc} alt="의사프로필" className={styles.img} />
            <label htmlFor="file" className={styles.label}>
              +
            </label>
            <input
              accept="image/*"
              type="file"
              id="file"
              className={styles.input}
              />
          </div>
          <form>
            <div className={styles.namediv}>
              <label htmlFor="name" className={styles.font}>이름</label>
              <input type="text" id="name" style={{ height: '2rem'}}/>
            </div>
            <p className={styles.font}>전문분야 (택 1)</p>
              <MainCategoryListSmall/>
            <div className={styles.center}>
              <button className={styles.register} onClick={ModalStateChange}>
                등록
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}

export default AddDoctor