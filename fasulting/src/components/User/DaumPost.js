import REACT, { useState } from "react";
import DaumPostCode from "react-daum-postcode";

const DaumPost = (props) => {
  const address = props.address;
  const setAddress = props.setAddress;

  const handleComplete = (data) => {
    console.log(data.address);
    setAddress(data.address);
  };

  return <DaumPostCode onComplete={handleComplete} />;
};
export default DaumPost;
