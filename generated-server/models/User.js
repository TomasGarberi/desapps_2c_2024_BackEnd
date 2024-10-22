// models/User.js
const { DataTypes } = require('sequelize');
const sequelize = require('../db');  // Importa la conexión configurada

const User = sequelize.define('User', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    name: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    lastName: {
        type: DataTypes.STRING,
    },
    email: {
        type: DataTypes.STRING,
        allowNull: false,
        unique: true,
    },
    password: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    urlImage: {
        type: DataTypes.STRING,
    },
    description: {
        type: DataTypes.STRING,
    }
    }, {
    tableName: 'users',
    timestamps: false,  // Si no quieres que agregue createdAt y updatedAt
    });

module.exports = User;
