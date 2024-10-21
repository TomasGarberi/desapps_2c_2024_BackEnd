/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* delete my user
* Delete my user
*
* userId Integer pass an id to retrieve a specific user
* no response value expected for this operation
* */
const deleteUserID = ({ userId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        userId,
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
* follow a specific user by id
* follow specific user by id 
*
* userId Integer pass an id to retrieve a specific user
* returns List
* */
const followSpecificUserID = ({ userId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        userId,
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
* Search for a user or users
* You can use search by name, use pagination and orderby 
*
* contains String pass a search string for looking up users
* skip Integer number of records to skip for pagination
* limit Integer maximum number of records to return
* orderby String pass an search string to order the result (optional)
* returns List
* */
const getAllUsersID = ({ contains, skip, limit, orderby }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        contains,
        skip,
        limit,
        orderby,
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
* retrieve a specific users quantity of comments made
* retrieve a specific user comments 
*
* userId Integer pass an id to retrieve a specific user
* returns Integer
* */
const getSpecificUserComments = ({ userId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        userId,
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
* retrieve a specific user followers by id
* retrieve a specific user followers by id 
*
* userId Integer pass an id to retrieve a specific user
* returns List
* */
const getSpecificUserFollowersID = ({ userId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        userId,
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
* retrieve a specific user by id
* retrieve a specific user by id 
*
* userId Integer pass an id to retrieve a specific user
* returns List
* */
const getSpecificUserID = ({ userId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        userId,
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
* retrieve a specific users posts by id
* retrieve a specific user posts favorites 
*
* userId Integer pass an id to retrieve a specific user
* returns List
* */
const getSpecificUserPostsFavorites = ({ userId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        userId,
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
* retrieve a specific users posts by id
* retrieve a specific user posts by id 
*
* userId Integer pass an id to retrieve a specific user
* returns List
* */
const getSpecificUserPostsID = ({ userId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        userId,
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
* retrieve a specific user following users by id
* retrieve a specific user following users by id 
*
* userId Integer pass an id to retrieve a specific user
* returns List
* */
const getSpecificUserfollowingID = ({ userId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        userId,
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
* unfollows a specific user by id
* unfollow a specific user by id 
*
* userId Integer pass an id to retrieve a specific user
* returns List
* */
const unfollowSpecificUserID = ({ userId }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        userId,
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
* update my user
* Update my user
*
* userId Integer pass an id to retrieve a specific user
* user User Inventory item to add (optional)
* no response value expected for this operation
* */
const updateUserID = ({ userId, user }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        userId,
        user,
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
