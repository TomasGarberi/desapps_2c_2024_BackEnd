const config = require('./config');
const logger = require('./logger');
const ExpressServer = require('./expressServer');
const sequelize = require('./db');  // Importa la conexión de Sequelize
const models = require('./models');  // Importa todos los modelos definidos en la carpeta models

const launchServer = async () => {
  try {
    // Sincroniza los modelos con la base de datos
    await sequelize.sync({ force: false });  // Cambiar `force` a `true` si se necesita sobrescribir las tablas.
    logger.info('Base de datos sincronizada correctamente');

    // Lanza el servidor de Express después de sincronizar la base de datos
    this.expressServer = new ExpressServer(config.URL_PORT, config.OPENAPI_YAML);
    this.expressServer.launch();
    logger.info('Express server corriendo en el puerto ' + config.URL_PORT);
  } catch (error) {
    logger.error('Error al lanzar el servidor de Express o al sincronizar la base de datos', error.message);
    await this.close();
  }
};

// Lanza el servidor y captura cualquier error
launchServer().catch(e => logger.error(e));
