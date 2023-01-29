import React from "react";
import styles from "./Form.module.css";
import InputField from "./InputField";

export default function PsRegistForm03(props) {
  const {
    formField: {
      psdirector,
      psregistration,
      psregistrationimg,
      doctor,
      category,
    },
  } = props;
  return (
    <>
      <div className={styles.inputItem}>
        <div className={styles.labelFirst}>병원장</div>
        <InputField
          fullWidth
          placeholder="병원장 이름을 입력해주세요"
          name={psdirector.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 사업자 등록번호</div>
        <InputField
          fullWidth
          placeholder="병원 사업자 등록 번호를 입력해주세요"
          name={psregistration.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 사업자 등록증</div>
        <InputField
          fullWidth
          placeholder="병원 사업자 등록증을 업로드 해주세요"
          type="file"
          name={psregistrationimg.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 의사</div>
        <InputField
          fullWidth
          placeholder="병원 의사 정보를 입력해주세요."
          name={doctor.name}
        />
      </div>
      <div className={styles.inputItem}>
        <div className={styles.label}>병원 카테고리 선택</div>
        <InputField
          fullWidth
          type="password"
          placeholder="수술 메인 카테고리를 선택해주세요."
          name={category.name}
        />
      </div>
    </>
  );
}
