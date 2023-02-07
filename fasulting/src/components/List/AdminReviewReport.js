import * as React from "react";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemText from "@mui/material/ListItemText";
import IconButton from "@mui/material/IconButton";
import Pagination from "@mui/material/Pagination";
import ContentPasteSearchIcon from "@mui/icons-material/ContentPasteSearch";
import Typography from "@mui/material/Typography";

function AdminSignupAcceptList() {
  const signUpList = [
    {
      psSeq: "1",
      email: "이메일",
      name: "김싸피의원",
      address: "병원 주소",
      zipcode: "병원 우편번호",
      registration: "병원 사업자 등록 번호",
      number: "병원 전화번호",
      director: "병원 원장 이름",
      homepage: "병원 홈페이지", // null 가능
      intro: "병원 소개", // null 가능

      mainCategoryList: ["카테고리1", "카테고리2", "카테고리3"],
      subCategoryList: ["카테고리1", "카테고리2", "카테고리3"],

      registrationImg: "사업자 등록증",
      profileImg: "병원 프로필 사진",
    },
    {
      psSeq: "2",
      email: "이메일",
      name: "이싸피의원",
      address: "병원 주소",
      zipcode: "병원 우편번호",
      registration: "병원 사업자 등록 번호",
      number: "병원 전화번호",
      director: "병원 원장 이름",
      homepage: "병원 홈페이지", // null 가능
      intro: "병원 소개", // null 가능

      mainCategoryList: ["카테고리1", "카테고리2", "카테고리3"],
      subCategoryList: ["카테고리1", "카테고리2", "카테고리3"],

      registrationImg: "사업자 등록증",
      profileImg: "병원 프로필 사진",
    },
  ];
  return (
    <div style={{ width: "40%" }}>
      <Typography variant="h5" gutterBottom>
        ⛔ 리뷰 신고 내역
      </Typography>
      <List sx={{ bgcolor: "background.paper" }}>
        {signUpList.map((obj) => {
          const labelId = `checkbox-list-label-${obj.psSeq}`;

          return (
            <ListItem
              key={obj.psSeq}
              secondaryAction={
                <IconButton edge="end" aria-label="comments">
                  <ContentPasteSearchIcon />
                </IconButton>
              }
              disablePadding
            >
              <ListItemButton role={undefined} dense>
                <ListItemText
                  id={labelId}
                  primary={obj.name}
                  secondary={obj.address}
                />
              </ListItemButton>
            </ListItem>
          );
        })}
      </List>
      {/* <Pagination count={10} color="primary" /> */}
    </div>
  );
}

export default AdminSignupAcceptList;
