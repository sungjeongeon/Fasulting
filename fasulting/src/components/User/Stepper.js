import * as React from "react";
import CssBaseline from "@mui/material/CssBaseline";
import Container from "@mui/material/Container";
import Paper from "@mui/material/Paper";
import Stepper from "@mui/material/Stepper";
import Step from "@mui/material/Step";
import StepLabel from "@mui/material/StepLabel";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { createTheme } from "@mui/material/styles";
import PsRegistForm01 from "./PsRegistForm01";
import PsRegistForm02 from "./PsRegistForm02";
import PsRegistForm03 from "./PsRegistForm03";

import FormikStepper from "formik-stepper";
import { Formik, Form } from "formik";

import validationSchema from "./FormModel/validationSchema";
import formInitialValues from "./FormModel/formInitialValues";
import checkoutFormModel from "./FormModel/checkoutFormModel";

const steps = ["개인 정보", "병원 관련 등록", "병원 인증"];
const { formId, formField } = checkoutFormModel;

function getStepContent(step) {
  switch (step) {
    case 0:
      return <PsRegistForm01 formField={formField} />;
    case 1:
      return <PsRegistForm02 formField={formField} />;
    case 2:
      return <PsRegistForm03 />;
    default:
      throw new Error("Unknown step");
  }
}

const theme = createTheme();

export default function PsRegist() {
  const [activeStep, setActiveStep] = React.useState(0);
  const currentValidationSchema = validationSchema[activeStep];
  const isLastStep = activeStep === steps.length - 1;

  async function _submitForm(values, actions) {
    alert(JSON.stringify(values, null, 2));
    actions.setSubmitting(false);

    setActiveStep(activeStep + 1);
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
          component="primary"
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
            <PsRegistForm01 />
          ) : (
            <Formik
              initialValues={formInitialValues}
              validationSchema={currentValidationSchema}
              onSubmit={_handleSubmit}
            >
              {({ isSubmitting }) => (
                <Form id={formId}>
                  {getStepContent(activeStep)}

                  <div>
                    {activeStep !== 0 && (
                      <Button onClick={_handleBack}>Back</Button>
                    )}
                    <div>
                      <Button
                        disabled={isSubmitting}
                        type="submit"
                        variant="contained"
                        color="primary"
                      >
                        {isLastStep ? "Place order" : "Next"}
                      </Button>
                      {isSubmitting}
                    </div>
                  </div>
                </Form>
              )}
            </Formik>
          )}
        </React.Fragment>
      </Paper>
    </Container>
  );
}
