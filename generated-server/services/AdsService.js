/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* retrieve an ad
* retrieve an ad 
*
* returns List
* */
const getAdsID = () => new Promise(
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
* retrieve an ads url
* retrieve an ads url 
*
* adId Integer pass an id to retrieve a specific ad
* returns List
* */
const getAdsUrlID = ({ adId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        adId,
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
* share an ad
* retrieve all the comments of a post 
*
* adId Integer pass an id to retrieve a specific post
* returns List
* */
const shareAdsID = ({ adId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        adId,
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
* visit an ad
* get the url of an ad 
*
* adId Integer pass an id to retrieve a specific post
* returns List
* */
const visitAdsID = ({ adId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        adId,
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
  getAdsID,
  getAdsUrlID,
  shareAdsID,
  visitAdsID,
};
