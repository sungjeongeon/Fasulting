import * as React from "react";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemText from "@mui/material/ListItemText";
import IconButton from "@mui/material/IconButton";
import Pagination from "@mui/material/Pagination";
import Typography from "@mui/material/Typography";
import AdminReviewDeleteModal from "../Modal/AdminReviewDeleteModel";

function AdminSignupAcceptList({ reviewList }) {
  return (
    <div style={{ width: "40%" }}>
      <Typography variant="h5" gutterBottom>
        ⛔ 리뷰 신고 내역 ({reviewList ? reviewList.length : 0})
      </Typography>
      <hr />
      <List sx={{ bgcolor: "background.paper" }}>
        {reviewList &&
          reviewList.map((obj) => {
            const labelId = `checkbox-list-label-${obj.psSeq}`;

            return (
              <ListItem
                key={obj.psSeq}
                secondaryAction={<AdminReviewDeleteModal review={obj} />}
                disablePadding
              >
                <ListItemButton role={undefined} dense>
                  <ListItemText
                    id={labelId}
                    primary={`★${obj.point.toFixed(1)}`}
                    secondary={obj.content}
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
