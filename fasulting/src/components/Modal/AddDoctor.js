import React, { useState } from "react"
import styles from "./AddDoctor.module.css"
import MainCategoryListSmall from "../Category/MainCategoryListSmall"
// import DisabledByDefaultOutlinedIcon from '@mui/icons-material/DisabledByDefaultOutlined';
import CloseIcon from "@mui/icons-material/Close";
import axiosAPi from "../../api/axiosApi";


function AddDoctor({ModalStateChange, doctorList, setDoctorList, psSeq}) {
  const basicsrc = "../../assets/images/doctorBasic.png"
  const [docName, setDocName] = useState('')
  const [mainCtg, setMainCtg] = useState('')
  // 미리보기 위한 src
  const [imgSrc, setImgSrc] = useState(basicsrc)
  // img 파일 객체 (서버에 보내줄 것)
  const [imgFile, setImgFile] = useState('')

  // 의사 input value
  const onChange = (e) => {
    setDocName(e.target.value)
  }

  // 파일 객체 state에 저장 + 이미지 미리보기 위한 url state 변경 
  const saveImage = (e) => {
  // if (e.target.files) {
    const uploadImg = e.target.files[0]
    // console.log(e.target.files[0])
    setImgFile(uploadImg)
    setImgSrc(URL.createObjectURL(uploadImg))
  // }
  }
  
  const prevent = (e) => {
    e.preventDefault()
  }
  // console.log(psSeq, docName, mainCtg, imgFile)
  
  // 재렌더링 할 수 있도록
  console.log(doctorList)
  const newDoc = {
    name: docName,
    profileImg: imgSrc,
    mainCategoryName: mainCtg
  }
  

  const addDoctor = async () => {
    const formData = new FormData();
    formData.append("psSeq", psSeq)
    formData.append("name", docName);
    formData.append("mainCategory", mainCtg);
    if (imgFile) {
      formData.append("img", imgFile);
    }
    // for (var key of formData.keys()) {
    //   console.log(key);
    // }

    // for (var value of formData.values()) {
    //   console.log(value);
    // }

    try {
      await axiosAPi.post("/ps/doctor", formData, {
        headers: {
          // access_token 토큰 필요
          "Content-Type": "multipart/form-data"
        },
      })
      .then(() => {
        ModalStateChange()
        setDoctorList([...doctorList, newDoc])
        setTimeout(() => {
          URL.revokeObjectURL(imgSrc)
        }, 2000);
      })
    } catch (e) {
      console.log(e)
    }
  }



  return (
    <div className={styles.background}>
      <div className={styles.modalbox}>
        <div className={styles.inside}>
          <div className={styles.nav}>
            <span className={styles.title}>의사등록</span>
            <CloseIcon
              fontSize="large"
              onClick={ModalStateChange}
              color="error"
            />
          </div>
          <div className={styles.center}>
            <img src={imgSrc === '' ? basicsrc : imgSrc} alt="의사프로필" className={styles.img} />
            <label htmlFor="doctorImg" className={styles.label}>
              +
            </label>
            <input
              accept="image/*"
              type="file"
              id="doctorImg"
              className={styles.input}
              onChange={saveImage}
            />
          </div>
          <form onSubmit={prevent}>
            <div className={styles.namediv}>
              <label htmlFor="name" className={styles.font}>이름</label>
              <input
                type="text"
                id="name"
                value={docName}
                onChange={onChange}
                style={{ height: '2rem'}}
              />
            </div>
            <p className={styles.font}>전문분야 (택 1)</p>
              <MainCategoryListSmall mainCtg={mainCtg} setMainCtg={setMainCtg}/>
            <div className={styles.center}>
              <button
                className={styles.register}
                onClick={addDoctor}>
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