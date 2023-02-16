import React from "react";
import { useLocation } from "react-router-dom";
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
    </div>
  );
}

export default OpenViduRoom;
