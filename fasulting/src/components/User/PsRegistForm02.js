import React from "react";
import styles from "./Form.module.css";
import InputField from "./InputField";
import DaumPost from "./DaumPost";
import { useState } from "react";

export default function PsRegistForm01(props) {
  const {
    formField: { psname, psprofile, psintro, psaddress, psnumber, pshomepage },
  } = props;

  //주소 검색 API 열기
  const [isOpen, setIsOpen] = useState(false);
  //주소 검색 열고 닫기
  const onToggleModal = () => {
    setIsOpen((prev) => !prev);
  };
  //선택한 주소 관리
  const [psAddress, setpsAddress] = useState({
    address: "",
  });
  //INPUT에 주소 넣기 위해
  const handleInput = (e) => {
    setpsAddress({
      ...psAddress,
      [e.target.name]: e.target.value,
    });
  };

  // const fileInput = React.useRef(null);
  // //파일 업로드 버튼
  // const handleClickBtn = () => {
  //   fileInput.current.click();
  // };
  // const handleChange = (e) => {
  //   console.log(e.target.files[0]);
  // };
  return (
    <>
      <div className={styles.inputItem}>
        <div className={styles.labelFirst}>병원명</div>
        <InputField
          fullWidth
          placeholder="병원명을 입력해주세요"
          name={psname.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 프로필사진 (선택)</div>
        {/* <Button >Upload a file</Button> */}
        <InputField
          fullWidth
          type="file"
          placeholder="병원 프로필사진"
          name={psprofile.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 한줄 소개 (선택)</div>
        <InputField
          fullWidth
          placeholder="병원 한줄 소개를 입력해주세요"
          name={psintro.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 주소 (선택)</div>
        {isOpen && (
          <DaumPost
            onToggleModal={onToggleModal}
            address={psAddress}
            setpsAddress={setpsAddress}
          />
        )}
        <InputField
          fullWidth
          placeholder="병원 주소를 입력해주세요."
          name={psaddress.name}
          onChange={handleInput}
          value={psAddress.address}
          onClick={onToggleModal}
        />
        {/* <Button type="primary" onClick={onToggleModal}>
          주소 찾기
        </Button> */}
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 전화번호</div>
        <InputField
          fullWidth
          placeholder="병원 전화번호를 입력해주세요."
          name={psnumber.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 홈페이지 주소</div>
        <InputField
          fullWidth
          placeholder="병원 홈페이지 주소를 입력해주세요."
          name={pshomepage.name}
        />
      </div>
    </>
  );
}
