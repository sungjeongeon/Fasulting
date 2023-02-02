import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import React, { Component } from "react";
import StreamComponent from "./stream/StreamComponent";
import styles from "./VideoTest.module.css";

import OpenViduLayout from "./layout/openvidu-layout";
import UserModel from "./models/user-model";
import ToolbarComponent from "./toolbar/ToolbarComponent";

// 유저 생성
var localUser = new UserModel();
// 애플리케이션 서버 URL
const APPLICATION_SERVER_URL =
  process.env.NODE_ENV === "production" ? "" : "http://localhost:5000/";

class VideoTest extends Component {
  constructor(props) {
    super(props);
    this.remotes = [];
    this.state = {
      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
      session: undefined,
      localUser: undefined,
      mainStreamManager: undefined, // Main video of the page. Will be the 'publisher' or one of the 'subscribers'
      publisher: undefined,
      subscriber: undefined,
    };
  }

  componentDidMount() {
    this.joinSession();
  }

  joinSession() {
    // OpenVidu 객체 가져오기
    this.OV = new OpenVidu();

    this.setState(
      {
        // state의 세션 속성 초기화
        session: this.OV.initSession(),
      },
      async () => {
        // 관심있는 세션 이벤트 구독
        this.subscribeToStreamCreated();
        await this.connectToSession();
      }
    );
  }

  subscribeToStreamCreated() {
    console.log("subscribeToStreamCreated 함수 실행");
    var mySession = this.state.session;
    // stream 생성 이벤트 발생 시
    mySession.on("streamCreated", (event) => {
      const subscriber = mySession.subscribe(event.stream, undefined);
      // subscriber 추가
      this.setState({
        subscriber: subscriber,
      });

      subscriber.on("streamPlaying", (e) => {
        // this.checkSomeoneShareScreen();
        subscriber.videos[0].video.parentElement.classList.remove(
          "custom-class"
        );
      });
      const newUser = new UserModel();
      newUser.setStreamManager(subscriber);
      newUser.setConnectionId(event.stream.connection.connectionId);
      newUser.setType("remote");
      const nickname = event.stream.connection.data.split("%")[0];
      newUser.setNickname(JSON.parse(nickname).clientData);
      this.remotes.push(newUser);
      if (this.localUserAccessAllowed) {
        this.updateSubscribers();
      }
    });
  }

  async connectToSession() {
    // 토큰 있으면
    if (this.props.token !== undefined) {
      console.log("토큰 이미 있음");
      console.log("token received: ", this.props.token);
      this.connect(this.props.token);
    } else {
      try {
        console.log("토큰get시도");
        var token = await this.getToken();
        // 받은 token으로 연결 시도
        this.connect(token);
      } catch (error) {
        console.error(
          "There was an error getting the token:",
          error.code,
          error.message
        );
        if (this.props.error) {
          this.props.error({
            error: error.error,
            messgae: error.message,
            code: error.code,
            status: error.status,
          });
        }
        alert("There was an error getting the token:", error.message);
      }
    }
  }

  connect(token) {
    this.state.session
      .connect(token, { clientData: this.state.myUserName })
      .then(() => {
        this.connectWebCam();
      })
      .catch((error) => {
        if (this.props.error) {
          this.props.error({
            error: error.error,
            messgae: error.message,
            code: error.code,
            status: error.status,
          });
        }
        alert("There was an error connecting to the session:", error.message);
        console.log(
          "There was an error connecting to the session:",
          error.code,
          error.message
        );
      });
  }

  async connectWebCam() {
    await this.OV.getUserMedia({
      audioSource: undefined,
      videoSource: undefined,
    });
    var devices = await this.OV.getDevices();
    var videoDevices = devices.filter((device) => device.kind === "videoinput");

    let publisher = this.OV.initPublisher(undefined, {
      audioSource: undefined,
      videoSource: videoDevices[0].deviceId,
      publishAudio: true,
      publishVideo: true,
      resolution: "640x480",
      frameRate: 30,
      insertMode: "APPEND",
      mirror: true,
    });
    console.log("publish 전");

    // 스트림 publish
    console.log(this.state.session);
    if (this.state.session.capabilities.publish) {
      publisher.on("accessAllowed", () => {
        this.state.session.publish(publisher).then(() => {
          this.updateSubscribers();
          this.localUserAccessAllowed = true;
          if (this.props.joinSession) {
            this.props.joinSession();
          }
        });
      });
    }
    console.log(localUser);
    console.log("1 여기서 문제 있음ㅋ");
    localUser.setNickname(this.state.myUserName);
    localUser.setConnectionId(this.state.session.connection.connectionId);
    localUser.setScreenShareActive(false);
    localUser.setStreamManager(publisher);
    console.log(localUser);
    // this.subscribeToUserChanged();
    // this.subscribeToStreamDestroyed();
    // this.sendSignalUserChanged({
    //   isScreenShareActive: localUser.isScreenShareActive(),
    // });
    console.log("2");
    this.setState(
      { currentVideoDevice: videoDevices[0], localUser: localUser },
      () => {
        this.state.localUser.getStreamManager().on("streamPlaying", (e) => {
          // this.updateLayout();
          publisher.videos[0].video.parentElement.classList.remove(
            "custom-class"
          );
        });
      }
    );
    console.log("publish 완료");
  }

  updateSubscribers() {
    console.log("updateSubscribers 함수실행");
    var subscribers = this.remotes;
    this.setState(
      {
        subscribers: subscribers,
      },
      () => {
        if (this.state.localUser) {
          console.log("여기??");
          // this.sendSignalUserChanged({
          //   isAudioActive: this.state.localUser.isAudioActive(),
          //   isVideoActive: this.state.localUser.isVideoActive(),

          //   me: this.state.localUser.getNickname(),
          //   isScreenShareActive: this.state.localUser.isScreenShareActive(),
          // });
        }
        // this.updateLayout();
      }
    );
  }

  // 툴바 ===========================
  camStatusChanged() {
    localUser.setVideoActive(!localUser.isVideoActive());
    localUser.getStreamManager().publishVideo(localUser.isVideoActive());
    this.sendSignalUserChanged({ isVideoActive: localUser.isVideoActive() });
    this.setState({ localUser: localUser });
  }

  micStatusChanged() {
    localUser.setAudioActive(!localUser.isAudioActive());
    localUser.getStreamManager().publishAudio(localUser.isAudioActive());
    this.sendSignalUserChanged({ isAudioActive: localUser.isAudioActive() });
    this.setState({ localUser: localUser });
  }
  // ================================

  render() {
    const localUser = this.state.localUser;
    const mySessionId = this.state.mySessionId;
    return (
      <div>
        <ToolbarComponent
          sessionId={mySessionId}
          user={localUser}
          showNotification={this.state.messageReceived}
          camStatusChanged={this.camStatusChanged}
          micStatusChanged={this.micStatusChanged}
          screenShare={this.screenShare}
          stopScreenShare={this.stopScreenShare}
          toggleFullscreen={this.toggleFullscreen}
          switchCamera={this.switchCamera}
          leaveSession={this.leaveSession}
          toggleChat={this.toggleChat}
        />
        {localUser !== undefined &&
          localUser.getStreamManager() !== undefined && (
            <div className={styles.div}>
              <h1>test</h1>
              <StreamComponent user={localUser} />
            </div>
          )}
      </div>
    );
  }

  async getToken() {
    const sessionId = await this.createSession(this.state.mySessionId);
    return await this.createToken(sessionId);
  }

  async createSession(sessionId) {
    const response = await axios.post(
      APPLICATION_SERVER_URL + "api/sessions",
      { customSessionId: sessionId },
      {
        headers: { "Content-Type": "application/json" },
      }
    );
    return response.data; // The sessionId to getToken()
  }

  async createToken(sessionId) {
    const response = await axios.post(
      APPLICATION_SERVER_URL + "api/sessions/" + sessionId + "/connections",
      {},
      {
        headers: { "Content-Type": "application/json" },
      }
    );
    return response.data; // The token
  }
}
export default VideoTest;
