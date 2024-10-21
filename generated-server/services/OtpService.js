/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* Send a mail to your email for the OTP
* Send a mail to your email for the OTP 
*
* usermail Usermail 
* no response value expected for this operation
* */
const getvalidateOTPId = ({ usermail }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        usermail,
      }));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Invalid input',
        e.status || 405,
      ));
    }
  },
);
/**
* validate a OTP
* validate One Time Password to validate login 
*
* tOPT TOPT 
* no response value expected for this operation
* */
const postvalidateOTPId = ({ tOPT }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        tOPT,
      }));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Invalid input',
        e.status || 405,
      ));
    }
  },
);

module.exports = {
  getvalidateOTPId,
  postvalidateOTPId,
};
