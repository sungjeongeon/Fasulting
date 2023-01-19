import RegisterForm from "../../components/User/RegisterForm";
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
function Register() {
  const classes = useStyles();
  return (
    <div className={classes.container}>
      <Container maxWidth="lg">
        <RegisterForm/>
      </Container>
    </div>
  );
}

export default Register;
