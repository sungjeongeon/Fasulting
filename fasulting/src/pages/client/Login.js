import LoginForm from "../../components/User/LoginForm";
import { Container } from "@mui/system";
// import { makeStyles } from "@mui/styles";

// const useStyles = makeStyles(() => ({
//   container: {
//     position: "absolute",
//     left: "0",
//     width: "100vw",
//     backgroundColor: "#E5F3F5",
//     display: "flex",
//     justifyContent: "center",
//     alignItems: "center",
//     minHeight: "100vh",
//   },
// }));

function Login() {
  // const classes = useStyles();
  return (
    // <div className={classes.container}>
    <div>
      <Container maxidth="lg">
        <LoginForm />
      </Container>
    </div>
  );
}

export default Login;
