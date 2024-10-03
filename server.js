// Importar dependencias necesarias
const express = require('express');
const cors = require('cors');
const bcrypt = require('bcrypt'); // Nueva dependencia para encriptar contraseñas
const jwt = require('jsonwebtoken'); // Nueva dependencia para manejar JWT

const app = express();

// Middleware
app.use(cors());
app.use(express.json());

// Simulamos almacenamiento de usuarios temporal (en un array)
const users = [];  // Esto lo conectaremos a una base de datos más adelante

// Ruta para registro de usuarios
app.post('/register', async (req, res) => {
  const { email, password } = req.body;

  try {
    const hashedPassword = await bcrypt.hash(password, 10); // Encriptar la contraseña
    const user = { email, password: hashedPassword };
    users.push(user); // Guardar el usuario en el array (más tarde en la base de datos)
    res.status(201).send('User registered');
  } catch {
    res.status(500).send('Error registering user');
  }
});

// Ruta para login de usuarios
app.post('/login', async (req, res) => {
  const { email, password } = req.body;

  // Buscar al usuario en el array
  const user = users.find(user => user.email === email);
  if (!user) {
    return res.status(404).send('User not found');
  }

  try {
    // Verificar la contraseña encriptada
    if (await bcrypt.compare(password, user.password)) {
      // Generar un token JWT si la contraseña es correcta
      const token = jwt.sign({ email: user.email }, 'secretkey', { expiresIn: '1h' });
      res.json({ token });
    } else {
      res.status(403).send('Password incorrect');
    }
  } catch {
    res.status(500).send('Error logging in');
  }
});

// Puerto en el que corre el servidor
const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
