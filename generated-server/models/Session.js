// models/Session.js
const { DataTypes } = require('sequelize');
const sequelize = require('../db');

const Session = sequelize.define('Session', {
    accessToken: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    refreshToken: {
        type: DataTypes.STRING,
        allowNull: false,
    }
    }, {
    tableName: 'sessions',
    timestamps: false,
    });

module.exports = Session;
