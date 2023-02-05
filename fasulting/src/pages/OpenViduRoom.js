import React from "react";
// import VideoRoomComponent from "../components/OpenVidu/VideoRoomComponent";
import VideoRoom from "../components/OpenVidu/VideoRoom";

function OpenViduRoom() {
  return (
    <div>
      <VideoRoom hospital={13} client={14} />
      {/* <VideoRoomComponent hospital={13} client={14} /> */}
    </div>
  );
}

export default OpenViduRoom;
