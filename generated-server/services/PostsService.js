/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* removes from favorites a post
* 'removes from favorites a post' 
*
* postId Integer pass an id to retrieve a specific post
* returns List
* */
const deleteFavoritePostID = ({ postId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        postId,
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
* deletes specific post by id
* deletes specific post by id
*
* postId Integer pass an id to delete a specific post
* returns List
* */
const deleteSpecificPostID = ({ postId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        postId,
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
* adds to favorite a post
* adds to favorite a post 
*
* postId Integer pass an id to retrieve a specific post
* returns List
* */
const getFavoritePostID = ({ postId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        postId,
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
* retrieves likes from post
* retrieves likes from post 
*
* postId Integer pass an id to retrieve a specific post likes
* returns Integer
* */
const getLikesPostID = ({ postId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        postId,
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
* retrieve a specific image by id of a specific post
* retrieve a specific post by id 
*
* postId Integer pass an id to retrieve a specific post
* imageId Integer pass an id to retrieve a specific image of a post
* returns List
* */
const getSpecificImageID = ({ postId, imageId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        postId,
        imageId,
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
* retrieve a specific post by id
* retrieve a specific post by id 
*
* postId Integer pass an id to retrieve a specific post
* returns List
* */
const getSpecificPostID = ({ postId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        postId,
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
* retrieves timeline
* Recupera la timeline. La respuesta es un único elemento, un vector de enteros. 
*
* idUsuario Integer ID del usuario que solicita la timeline
* returns inline_response_200
* */
const getTimelineID = ({ idUsuario }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        idUsuario,
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
* adds to favorite a post
* adds to favorite a post 
*
* postId Integer pass an id to retrieve a specific post
* returns List
* */
const postFavoritePostID = ({ postId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        postId,
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
* adds like from a user to a post
* adds like from a user to a post. Posts userID to add to post. 
*
* postId Integer pass an id to retrieve a specific post
* returns Integer
* */
const postLikePostID = ({ postId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        postId,
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
* posts a  post
* posts a post 
*
* postPost PostPost here goes post information
* returns List
* */
const postPostID = ({ postPost }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        postPost,
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
