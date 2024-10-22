// models/Post.js
const { DataTypes } = require('sequelize');
const sequelize = require('../db');  // Importa la conexión configurada
const User = require('./User');  // Relacionar con el modelo User

const Post = sequelize.define('Post', {
    postId: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    description: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    image: {
        type: DataTypes.STRING,
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
    tableName: 'posts',
    timestamps: false,
    });

module.exports = Post;
