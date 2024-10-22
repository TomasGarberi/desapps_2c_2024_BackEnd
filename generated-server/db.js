const { Sequelize } = require('sequelize');

// Configurar la conexión a MySQL
const sequelize = new Sequelize('bd1', 'user', 'pass123', {
    host: 'localhost',
    dialect: 'mysql', 
});

module.exports = sequelize;
