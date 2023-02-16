import * as React from "react";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemText from "@mui/material/ListItemText";
import Typography from "@mui/material/Typography";
import AdminSignupAcceptModal from "../Modal/AdminSignUpAcceptModal";

function AdminSignupAcceptList({ signUpList }) {
  return (
    <div style={{ width: "40%" }}>
      <Typography variant="h5" gutterBottom>
        ✅ 가입 승인 대기 ({signUpList ? signUpList.length : 0})
      </Typography>
      <hr />
      <List sx={{ bgcolor: "background.paper" }}>
        {signUpList &&
          signUpList.map((obj) => {
            const labelId = `checkbox-list-label-${obj.psSeq}`;

            return (
              <ListItem
                key={obj.psSeq}
                secondaryAction={<AdminSignupAcceptModal ps={obj} />}
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
