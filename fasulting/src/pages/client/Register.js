import RegisterForm from "../../components/User/RegisterForm";
import { Container } from "@mui/system";

function Register() {
  return (
    <div>
      <Container maxWidth="lg">
        <RegisterForm />
      </Container>
    </div>
  );
}

export default Register;
