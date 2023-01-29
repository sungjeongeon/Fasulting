import REACT, { useState } from "react";
import DaumPostCode from "react-daum-postcode";

const DaumPost = (props) => {
  const handleComplete = (data) => {
    let fullAddress = data.address;
    let extraAddress = "";
    if (data.addressType === "R") {
      if (data.bname !== "") {
        extraAddress += data.bname;
      }
      if (data.buildingName !== "") {
        extraAddress +=
          extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
    }
    console.log(fullAddress);
    //fullAddress -> 전체 주소반환
    props.setpsAddress({
      ...props.psAddress,
      address: fullAddress,
    });
  };
  return <DaumPostCode onComplete={handleComplete} />;
};
export default DaumPost;
