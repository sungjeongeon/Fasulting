import checkoutFormModel from "./checkoutFormModel";
const {
  formField: {
    email,
    password,
    repassword,
    psname,
    psprofile,
    psintro,
    psaddress,
    psnumber,
    pshomepage,
    psdirector,
    psregistration,
    psregistrationimg,
    doctor,
    category,
  },
} = checkoutFormModel;

export default {
  [email]: "",
  [password]: "",
  [repassword]: "",
  [psname]: "",
  [psprofile]: "",
  [psintro]: "",
  [psaddress]: "",
  [psnumber]: "",
  [pshomepage]: "",
  [psdirector]: "",
  [psregistration]: "",
  [psregistrationimg]: null,
  [doctor]: "",
  [category]: "",
};
