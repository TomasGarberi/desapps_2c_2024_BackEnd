/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* Login
* login a user with email & password 
*
* userLogin UserLogin 
* returns Session
* */
const loginID = ({ userLogin }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        userLogin,
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
* Logout
* Logout 
*
* no response value expected for this operation
* */
const logoutID = () => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
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
* refresh token
* refresh token 
*
* session Session 
* returns Session
* */
const refreshTokenID = ({ session }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        session,
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
* Register
* Create a user with email & password
*
* userCreation UserCreation 
* returns UserCreation
* */
const registerID = ({ userCreation }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        userCreation,
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
* reset password
* Reset password 
*
* usermail Usermail 
* returns User
* */
const resetPasswordID = ({ usermail }) => new Promise(
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

module.exports = {
  loginID,
  logoutID,
  refreshTokenID,
  registerID,
  resetPasswordID,
};
