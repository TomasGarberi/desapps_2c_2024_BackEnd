/**
 * The PostsController file is a very simple one, which does not need to be changed manually,
 * unless there's a case where business logic routes the request to an entity which is not
 * the service.
 * The heavy lifting of the Controller item is done in Request.js - that is where request
 * parameters are extracted and sent to the service, and where response is handled.
 */

const Controller = require('./Controller');
const service = require('../services/PostsService');
const deleteFavoritePostID = async (request, response) => {
  await Controller.handleRequest(request, response, service.deleteFavoritePostID);
};

const deleteSpecificPostID = async (request, response) => {
  await Controller.handleRequest(request, response, service.deleteSpecificPostID);
};

const getFavoritePostID = async (request, response) => {
  await Controller.handleRequest(request, response, service.getFavoritePostID);
};

const getLikesPostID = async (request, response) => {
  await Controller.handleRequest(request, response, service.getLikesPostID);
};

const getSpecificImageID = async (request, response) => {
  await Controller.handleRequest(request, response, service.getSpecificImageID);
};

const getSpecificPostID = async (request, response) => {
  await Controller.handleRequest(request, response, service.getSpecificPostID);
};

const getTimelineID = async (request, response) => {
  await Controller.handleRequest(request, response, service.getTimelineID);
};

const postFavoritePostID = async (request, response) => {
  await Controller.handleRequest(request, response, service.postFavoritePostID);
};

const postLikePostID = async (request, response) => {
  await Controller.handleRequest(request, response, service.postLikePostID);
};

const postPostID = async (request, response) => {
  await Controller.handleRequest(request, response, service.postPostID);
};


module.exports = {
  deleteFavoritePostID,
  deleteSpecificPostID,
  getFavoritePostID,
  getLikesPostID,
  getSpecificImageID,
  getSpecificPostID,
  getTimelineID,
  postFavoritePostID,
  postLikePostID,
  postPostID,
};
