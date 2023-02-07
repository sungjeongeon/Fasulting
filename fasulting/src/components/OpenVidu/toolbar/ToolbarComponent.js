import React, { Component } from "react";
import "./ToolbarComponent.css";

import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";

import MicIcon from "@mui/icons-material/Mic";
import MicOffIcon from "@mui/icons-material/MicOff";
import VideocamIcon from "@mui/icons-material/Videocam";
import VideocamOffIcon from "@mui/icons-material/VideocamOff";
import FullscreenIcon from "@mui/icons-material/Fullscreen";
import FullscreenExitIcon from "@mui/icons-material/FullscreenExit";
import SwitchVideoIcon from "@mui/icons-material/SwitchVideo";
import PictureInPictureIcon from "@mui/icons-material/PictureInPicture";
import ScreenShareIcon from "@mui/icons-material/ScreenShare";
import StopScreenShareIcon from "@mui/icons-material/StopScreenShare";
import Tooltip from "@mui/material/Tooltip";
import PowerSettingsNewIcon from "@mui/icons-material/PowerSettingsNew";
import QuestionAnswerIcon from "@mui/icons-material/QuestionAnswer";

import IconButton from "@mui/material/IconButton";
import LeaveConsulting from "../../Modal/LeaveConsulting";

// const logo = require("../../dassets/images/fasulting_logo.png");

export default class ToolbarComponent extends Component {
  constructor(props) {
    super(props);
    this.state = { fullscreen: false };
    this.camStatusChanged = this.camStatusChanged.bind(this);
    this.micStatusChanged = this.micStatusChanged.bind(this);
    this.screenShare = this.screenShare.bind(this);
    this.stopScreenShare = this.stopScreenShare.bind(this);
    this.toggleFullscreen = this.toggleFullscreen.bind(this);
    this.switchCamera = this.switchCamera.bind(this);
    this.leaveSession = this.leaveSession.bind(this);
    this.toggleChat = this.toggleChat.bind(this);
  }

  micStatusChanged() {
    this.props.micStatusChanged();
  }

  camStatusChanged() {
    this.props.camStatusChanged();
  }

  screenShare() {
    this.props.screenShare();
  }

  stopScreenShare() {
    this.props.stopScreenShare();
  }

  toggleFullscreen() {
    this.setState({ fullscreen: !this.state.fullscreen });
    this.props.toggleFullscreen();
  }

  switchCamera() {
    this.props.switchCamera();
  }

  leaveSession() {
    this.props.leaveSession();
  }

  toggleChat() {
    this.props.toggleChat();
  }

  render() {
    const mySessionId = this.props.sessionId;
    const localUser = this.props.user;
    return (
      <AppBar className="toolbar" id="header">
        <Toolbar className="toolbar">
          <div className="buttonsContent">
            <IconButton
              color="inherit"
              className="navButton"
              id="navMicButton"
              onClick={this.micStatusChanged}
            >
              {localUser !== undefined && localUser.isAudioActive() ? (
                <MicIcon fontSize="large" />
              ) : (
                <MicOffIcon sx={{ color: "#d9d4cf" }} fontSize="large" />
              )}
            </IconButton>

            <IconButton
              color="inherit"
              className="navButton"
              id="navCamButton"
              onClick={this.camStatusChanged}
            >
              {localUser !== undefined && localUser.isVideoActive() ? (
                <VideocamIcon fontSize="large" />
              ) : (
                <VideocamOffIcon sx={{ color: "#d9d4cf" }} fontSize="large" />
              )}
            </IconButton>

            {localUser !== undefined && !localUser.isScreenShareActive() && (
              <IconButton
                color="inherit"
                className="navButton"
                onClick={this.screenShare}
              >
                <ScreenShareIcon fontSize="large" />
              </IconButton>
            )}
            {localUser !== undefined && localUser.isScreenShareActive() && (
              <IconButton onClick={this.stopScreenShare} id="navScreenButton">
                <StopScreenShareIcon
                  sx={{ color: "#d9d4cf" }}
                  fontSize="large"
                />
              </IconButton>
            )}

            {/* <IconButton
              sx={{ color: "#e64c3c" }}
              className="navButton"
              onClick={this.leaveSession}
              id="navLeaveButton"
            >
              <PowerSettingsNewIcon fontSize="large" />
            </IconButton> */}
            {/* 의사 소견 작성 */}
            <IconButton
              sx={{ color: "#e64c3c" }}
              className="navButton"
              id="navLeaveButton"
            >
              <LeaveConsulting leaveSession={this.leaveSession} />
            </IconButton>
          </div>
        </Toolbar>
      </AppBar>
    );
  }
}
