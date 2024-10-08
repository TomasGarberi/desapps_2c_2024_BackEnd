import express from 'express';
import cors from 'cors';
import { json } from 'body-parser';
import { router as userRouter } from './router/UserRouter'; 

const app = express();

// Middlewares
app.use(cors());
app.use(json());

// Rutas
app.use('/users', userRouter); 
// Añade otras rutas aquí

export default app;
