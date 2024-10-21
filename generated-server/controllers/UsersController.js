/**
 * The UsersController file is a very simple one, which does not need to be changed manually,
 * unless there's a case where business logic routes the request to an entity which is not
 * the service.
 * The heavy lifting of the Controller item is done in Request.js - that is where request
 * parameters are extracted and sent to the service, and where response is handled.
 */

const Controller = require('./Controller');
const service = require('../services/UsersService');
const deleteUserID = async (request, response) => {
  await Controller.handleRequest(request, response, service.deleteUserID);
};

const followSpecificUserID = async (request, response) => {
  await Controller.handleRequest(request, response, service.followSpecificUserID);
};

const getAllUsersID = async (request, response) => {
  await Controller.handleRequest(request, response, service.getAllUsersID);
};

const getSpecificUserComments = async (request, response) => {
  await Controller.handleRequest(request, response, service.getSpecificUserComments);
};

const getSpecificUserFollowersID = async (request, response) => {
  await Controller.handleRequest(request, response, service.getSpecificUserFollowersID);
};

const getSpecificUserID = async (request, response) => {
  await Controller.handleRequest(request, response, service.getSpecificUserID);
};

const getSpecificUserPostsFavorites = async (request, response) => {
  await Controller.handleRequest(request, response, service.getSpecificUserPostsFavorites);
};

const getSpecificUserPostsID = async (request, response) => {
  await Controller.handleRequest(request, response, service.getSpecificUserPostsID);
};

const getSpecificUserfollowingID = async (request, response) => {
  await Controller.handleRequest(request, response, service.getSpecificUserfollowingID);
};

const unfollowSpecificUserID = async (request, response) => {
  await Controller.handleRequest(request, response, service.unfollowSpecificUserID);
};

const updateUserID = async (request, response) => {
  await Controller.handleRequest(request, response, service.updateUserID);
};


module.exports = {
  deleteUserID,
  followSpecificUserID,
  getAllUsersID,
  getSpecificUserComments,
  getSpecificUserFollowersID,
  getSpecificUserID,
  getSpecificUserPostsFavorites,
  getSpecificUserPostsID,
  getSpecificUserfollowingID,
  unfollowSpecificUserID,
  updateUserID,
};
