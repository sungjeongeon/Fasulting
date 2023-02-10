import React from "react";
import { useLocation } from "react-router-dom";
// import VideoRoomComponent from "../components/OpenVidu/VideoRoomComponent";
import VideoRoom from "../components/OpenVidu/VideoRoom";

function OpenViduRoom() {
  const location = useLocation();
  console.log(location.state);
  return (
    <div>
      <VideoRoom
        hospital={location.state.psSeq}
        client={location.state.userSeq}
        reservationSeq={location.state.reservationSeq}
        who={location.state.who}
      />
      {/* <VideoRoomComponent hospital={13} client={14} /> */}
    </div>
  );
}

export default OpenViduRoom;
