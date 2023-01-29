import React from "react";
import styles from "./Form.module.css";
import InputField from "./InputField";
import { Button } from "@mui/material";
import DaumPost from "./DaumPost";
import { useState, useEffect } from "react";

export default function PsRegistForm01(props) {
  const {
    formField: { psname, psprofile, psintro, psaddress, psnumber, pshomepage },
  } = props;

  const [isOpen, setIsOpen] = useState(false);

  const onToggleModal = () => {
    setIsOpen((prev) => !prev);
  };
  const [psAddress, setpsAddress] = useState({
    address: "",
  });

  const handleInput = (e) => {
    setpsAddress({
      ...psAddress,
      [e.target.name]: e.target.value,
    });
  };
  return (
    <>
      <div className={styles.inputItem}>
        <div className={styles.labelFirst}>병원장</div>
        <InputField
          fullWidth
          placeholder="병원장 이름을 입력해주세요"
          name={psname.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 프로필사진</div>
        <InputField
          fullWidth
          type="file"
          placeholder="병원 프로필사진"
          name={psprofile.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 한줄 소개</div>
        <InputField
          fullWidth
          placeholder="병원 한줄 소개를 입력해주세요"
          name={psintro.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 주소</div>
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
        />
        <Button type="primary" onClick={onToggleModal}>
          주소 찾기
        </Button>
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
          type="password"
          placeholder="병원 홈페이지 주소를 입력해주세요."
          name={pshomepage.name}
        />
      </div>
    </>
  );
}
