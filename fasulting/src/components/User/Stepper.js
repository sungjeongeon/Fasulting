import * as React from "react";
import CssBaseline from "@mui/material/CssBaseline";
import Container from "@mui/material/Container";
import Paper from "@mui/material/Paper";
import Stepper from "@mui/material/Stepper";
import Step from "@mui/material/Step";
import StepLabel from "@mui/material/StepLabel";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import PsRegistForm01 from "./PsRegistForm01";
import PsRegistForm02 from "./PsRegistForm02";
import PsRegistForm03 from "./PsRegistForm03";
import styles from "./Form.module.css";

import { Formik, Form } from "formik";

import validationSchema from "./FormModel/validationSchema";
import formInitialValues from "./FormModel/formInitialValues";
import checkoutFormModel from "./FormModel/checkoutFormModel";
import "react-toastify/dist/ReactToastify.css";
import { toast, ToastContainer } from "react-toastify";
import PsRegistFormComplete from "./PsRegistFormComplete";
import { useNavigate } from "react-router-dom";
import axios from "axios";
const steps = ["개인 정보", "병원 관련 등록", "병원 인증"];
const { formId, formField } = checkoutFormModel;

function getStepContent(step) {
  switch (step) {
    case 0:
      return <PsRegistForm01 formField={formField} />;
    case 1:
      return <PsRegistForm02 formField={formField} />;
    case 2:
      return <PsRegistForm03 formField={formField} />;
    default:
      throw new Error("Unknown step");
  }
}

export default function PsRegist() {
  const navigate = useNavigate();
  const [activeStep, setActiveStep] = React.useState(0);
  const currentValidationSchema = validationSchema[activeStep];
  const isLastStep = activeStep === steps.length - 1;

  async function _submitForm(values, actions) {
    console.log(values);
    console.log(actions);
    alert(JSON.stringify(values, null, 2));
    actions.setSubmitting(false);

    const formData = new FormData();
    const dataSet = {
      email: values.email,
      password: values.password,
      name: values.name,
      address: values.address,
      zipcode: values.zipcode,
      registration: values.registration,
      number: values.number,
      director: values.director,
      hompage: values.hompage,
      intro: values.intro,
    };
    formData.append("ps", JSON.stringify(dataSet));
    formData.append("registrationImg", values.psregistrationimg);
    formData.append("profileImg", values.profileImg);
    console.log(formData);
    try {
      await axios.post("http://localhost:8080/ps/regist", {
        headers: {
          "Content-Type": "multiaprt/form",
        },
        data: formData,
      });
      toast.success(
        <h3>
          회원가입이 완료되었습니다.
          <br />
          로그인 하세요😄
        </h3>,
        {
          position: toast.POSITION.TOP_CENTER,
          autoClose: 2000,
        }
      );
      setTimeout(() => {
        navigate("/login");
      }, 2000);
    } catch (e) {
      console.log(e.response.data.message);
    }
  }

  function _handleSubmit(values, actions) {
    if (isLastStep) {
      _submitForm(values, actions);
    } else {
      setActiveStep(activeStep + 1);
      actions.setTouched({});
      actions.setSubmitting(false);
    }
  }

  function _handleBack() {
    setActiveStep(activeStep - 1);
  }

  return (
    <>
      <ToastContainer />
      <Container component="main" maxWidth="sm" sx={{ mb: 4 }}>
        <CssBaseline />
        <Paper
          variant="outlined"
          sx={{ my: { xs: 3, md: 6 }, p: { xs: 2, md: 3 } }}
        >
          <Typography component="h1" variant="h4" align="center">
            병원 등록
          </Typography>
          <Stepper
            component="main"
            activeStep={activeStep}
            sx={{ pt: 3, pb: 5 }}
          >
            {steps.map((label) => (
              <Step key={label}>
                <StepLabel>{label}</StepLabel>
              </Step>
            ))}
          </Stepper>
          <React.Fragment>
            {activeStep === steps.length ? (
              <PsRegistFormComplete />
            ) : (
              <Formik
                initialValues={formInitialValues}
                validationSchema={currentValidationSchema}
                onSubmit={_handleSubmit}
              >
                <Form id={formId}>
                  {getStepContent(activeStep)}

                  <div className={styles.buttons}>
                    {activeStep !== 0 && (
                      <Button onClick={_handleBack} className={styles.button}>
                        Back
                      </Button>
                    )}
                    <div>
                      <Button
                        className={styles.button}
                        type="submit"
                        variant="contained"
                        color="primary"
                      >
                        {isLastStep ? "회원가입" : "다음"}
                      </Button>
                    </div>
                  </div>
                </Form>
              </Formik>
            )}
          </React.Fragment>
        </Paper>
      </Container>
    </>
  );
}
