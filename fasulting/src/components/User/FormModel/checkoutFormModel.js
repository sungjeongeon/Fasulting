export default {
  formId: "checkoutForm",
  formField: {
    email: {
      name: "email",
    },
    password: {
      name: "password",
      requiredErrorMsg: "Last name is required",
    },
    repassword: {
      name: "repassword",
      requiredErrorMsg: "Address Line 1 is required",
    },
    psname: {
      name: "psname",
      label: "Address Line 2",
    },
    psprofile: {
      name: "psprofile",
      requiredErrorMsg: "City is required",
    },
    psintro: {
      name: "psintro",
      label: "State/Province/Region",
    },
    psaddress: {
      name: "psaddress",
      label: "Zipcode*",
      requiredErrorMsg: "Zipcode is required",
      invalidErrorMsg: "Zipcode is not valid (e.g. 70000)",
    },
    psnumber: {
      name: "psnumber",
      label: "Country*",
      requiredErrorMsg: "Country is required",
    },
    pshomepage: {
      name: "pshomepage",
      label: "Use this address for payment details",
    },
    psdirector: {
      name: "psdirector",
      label: "Name on card*",
      requiredErrorMsg: "Name on card is required",
    },
    psregistration: {
      name: "psregistration",
      label: "Card number*",
      requiredErrorMsg: "Card number is required",
      invalidErrorMsg: "Card number is not valid (e.g. 4111111111111)",
    },
    psregistrationimg: {
      name: "psregistrationimg",
      label: "Expiry date*",
      requiredErrorMsg: "Expiry date is required",
      invalidErrorMsg: "Expiry date is not valid",
    },
    doctor: {
      name: "doctor",
      label: "CVV*",
      requiredErrorMsg: "CVV is required",
      invalidErrorMsg: "CVV is invalid (e.g. 357)",
    },
    category: {
      name: "category",
      label: "CVV*",
      requiredErrorMsg: "CVV is required",
      invalidErrorMsg: "CVV is invalid (e.g. 357)",
    },
  },
};
