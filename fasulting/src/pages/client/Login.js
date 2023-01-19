import LoginForm from "../../components/LoginForm";
import { Container } from "@mui/system";
import { makeStyles } from "@mui/styles";

const useStyles = makeStyles(() => ({
  container: {
    backgroundColor: '#E5F3F5',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    minHeight: '100vh',
  },
}))
function Login() {
  const classes = useStyles();
  return (  
    <div className={classes.container}>
      <Container maxidth="lg">
        <LoginForm />
      </Container>
    </div>
  );
}

export default Login;
