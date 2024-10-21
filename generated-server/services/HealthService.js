/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* API health status
* API health status
*
* returns Health
* */
const healthID = () => new Promise(
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

module.exports = {
  healthID,
};
