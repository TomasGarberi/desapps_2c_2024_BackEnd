// models/Comment.js
const { DataTypes } = require('sequelize');
const sequelize = require('../db');
const User = require('./User');
const Post = require('./Post');

const Comment = sequelize.define('Comment', {
    commentId: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    comment: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    postId: {
        type: DataTypes.INTEGER,
        allowNull: false,
        references: {
        model: Post,
        key: 'postId'
        }
    },
    userId: {
        type: DataTypes.INTEGER,
        allowNull: false,
        references: {
        model: User,
        key: 'id'
        }
    }
    }, {
    tableName: 'comments',
    timestamps: false,
    });

module.exports = Comment;
