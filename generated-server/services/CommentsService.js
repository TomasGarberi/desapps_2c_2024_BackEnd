/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* delete the comment of a post
* retrieve all the comments of a post 
*
* postId Integer pass an id to retrieve a specific post
* commentId Integer pass an id to delete a specific comment of the post
* returns List
* */
const deleteCommentsPostID = ({ postId, commentId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        postId,
        commentId,
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
* retrieve all the comments of a post
* retrieve all the comments of a post 
*
* postId Integer pass an id to retrieve a specific post
* returns List
* */
const getCommentsPostID = ({ postId }) => new Promise(
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
* post a comment on a post
* post a comment on a post 
*
* postId Integer pass an id to retrieve a specific post
* returns List
* */
const postCommentsPostID = ({ postId }) => new Promise(
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

module.exports = {
  deleteCommentsPostID,
  getCommentsPostID,
  postCommentsPostID,
};
