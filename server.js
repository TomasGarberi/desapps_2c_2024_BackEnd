require('dotenv').config();
const express = require('express');
const cors = require('cors');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
const mongoose = require('mongoose');
const { body, validationResult } = require('express-validator');

// Conectar a MongoDB Atlas usando la variable de entorno
mongoose.connect(process.env.MONGODB_URI, {
  useNewUrlParser: true,
  useUnifiedTopology: true
}).then(() => {
  console.log('Conectado a MongoDB Atlas');
}).catch((error) => {
  console.log('Error conectando a MongoDB:', error);
});

const app = express();

// Middleware
app.use(cors());
app.use(express.json());

// Definir el esquema y modelo de usuarios
const userSchema = new mongoose.Schema({
  email: { type: String, required: true, unique: true },
  password: { type: String, required: true }
});

const User = mongoose.model('User', userSchema);

// Definir el esquema y modelo de posts
const postSchema = new mongoose.Schema({
  username: { type: String, required: true },
  content: { type: String, required: true }
});

const Post = mongoose.model('Post', postSchema);

// Ruta básica de prueba
app.get('/', (req, res) => {
  res.send('Hello from the backend!');
});

// Ruta de registro con validación
app.post('/register',
  [
    body('email').isEmail().withMessage('El correo electrónico no es válido'),
    body('password').isLength({ min: 6 }).withMessage('La contraseña debe tener al menos 6 caracteres')
  ],
  async (req, res) => {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
      return res.status(400).json({ errors: errors.array() });
    }

    const { email, password } = req.body;

    try {
      const hashedPassword = await bcrypt.hash(password, 10);
      const user = new User({ email, password: hashedPassword });
      await user.save();
      res.status(201).send('Usuario registrado');
    } catch (error) {
      res.status(500).send('Error al registrar el usuario');
    }
  }
);

// Ruta de login
app.post('/login', async (req, res) => {
  const { email, password } = req.body;

  // Buscar al usuario en MongoDB
  const user = await User.findOne({ email });
  if (!user) {
    return res.status(404).send('Usuario no encontrado');
  }

  try {
    if (await bcrypt.compare(password, user.password)) {
      const token = jwt.sign({ email: user.email }, 'secretkey', { expiresIn: '1h' });
      res.json({ token });
    } else {
      res.status(403).send('Contraseña incorrecta');
    }
  } catch {
    res.status(500).send('Error al iniciar sesión');
  }
});

// Middleware de autenticación para verificar el token JWT
function authenticateToken(req, res, next) {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  if (!token) return res.sendStatus(401);

  jwt.verify(token, 'secretkey', (err, user) => {
    if (err) return res.sendStatus(403);
    req.user = user;
    next();
  });
}

// Ruta para crear un post (requiere autenticación)
app.post('/posts', authenticateToken, async (req, res) => {
  const post = new Post({
    username: req.user.email,  // Extraemos el email del token JWT decodificado
    content: req.body.content, // El contenido del post viene en el cuerpo de la solicitud
  });

  await post.save(); // Guardar en MongoDB
  res.status(201).send('Post creado exitosamente');
});

// Ruta para obtener todos los posts (requiere autenticación)
app.get('/posts', authenticateToken, async (req, res) => {
  const posts = await Post.find(); // Obtener todos los posts desde MongoDB
  res.json(posts);
});

// Puerto en el que corre el servidor
const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});

