import React from "react";
import VideoRoomComponent from "../components/OpenVidu/VideoRoomComponent";
import VideoTest from "../components/OpenVidu/VideoTest";

function OpenViduTest() {
  return (
    <div>
      <h1>OpenVidu</h1>
      <VideoTest />
      {/* <VideoRoomComponent sessionName={"hospital"} /> */}
    </div>
  );
}

export default OpenViduTest;
