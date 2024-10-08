import "reflect-metadata";
import { DataSource } from "typeorm";
import { User } from "./entity/User"; // Asegúrate de tener esta entidad o modifica según tu entidad

export const AppDataSource = new DataSource({
    type: "postgres",
    host: process.env.TYPEORM_HOST,
    port: parseInt(process.env.TYPEORM_PORT || "5432"),
    username: process.env.TYPEORM_USERNAME,
    password: process.env.TYPEORM_PASSWORD,
    database: process.env.TYPEORM_DATABASE,
    synchronize: process.env.TYPEORM_SYNCHRONIZE === "true",
    logging: process.env.TYPEORM_LOGGING === "true",
    entities: ["src/entity/*.ts"], // Cambia la ruta según donde estén tus entidades
});
