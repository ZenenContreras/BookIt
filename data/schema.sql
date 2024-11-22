-- Tabla tipo_notificacion
CREATE TABLE if not exists tipo_notificacion (
                                   id SERIAL PRIMARY KEY,
                                   estado VARCHAR(255) NOT NULL
);

-- Tabla tipo_usuario
CREATE TABLE if not exists tipo_usuario (
                              id SERIAL PRIMARY KEY,
                              estado VARCHAR(255) NOT NULL
);

-- Tabla estado_pago
CREATE TABLE if not exists estado_pago (
                             id SERIAL PRIMARY KEY,
                             estado VARCHAR(255) NOT NULL
);

-- Tabla metodo_pago
CREATE TABLE if not exists metodo_pago (
                             id SERIAL PRIMARY KEY,
                             estado VARCHAR(255) NOT NULL
);

-- Tabla usuario
CREATE TABLE if not exists usuario (
                         id SERIAL PRIMARY KEY,
                         nombre VARCHAR(255) NOT NULL,
                         correo VARCHAR(255) UNIQUE NOT NULL,
                         contraseña VARCHAR(255) NOT NULL,
                         tipo_usuarioid INT NOT NULL,
                         FOREIGN KEY (tipo_usuarioid) REFERENCES tipo_usuario (id)
);

-- Tabla evento
CREATE TABLE if not exists evento (
                        id SERIAL PRIMARY KEY,
                        nombre VARCHAR(255) NOT NULL,
                        disponibilidad INT NOT NULL,
                        fecha DATE NOT NULL,
                        descripcion VARCHAR(255),
                        capacidad INT NOT NULL
);

-- Tabla reserva
CREATE TABLE if not exists reserva (
                         id SERIAL PRIMARY KEY,
                         cantidad_entradas INT NOT NULL,
                         usuarioid INT NOT NULL,
                         eventoid INT NOT NULL,
                         fecha_reserva DATE NOT NULL,
                         estado_pagoid INT NOT NULL,
                         FOREIGN KEY (usuarioid) REFERENCES usuario (id),
                         FOREIGN KEY (eventoid) REFERENCES evento (id),
                         FOREIGN KEY (estado_pagoid) REFERENCES estado_pago (id)
);

-- Tabla pago
CREATE TABLE if not exists pago (
                      id SERIAL PRIMARY KEY,
                      monto INT NOT NULL,
                      fecha_pago DATE NOT NULL,
                      reservaid INT NOT NULL,
                      metodo_pagoid INT NOT NULL,
                      FOREIGN KEY (reservaid) REFERENCES reserva (id),
                      FOREIGN KEY (metodo_pagoid) REFERENCES metodo_pago (id)
);

-- Tabla notificacion
CREATE TABLE if not exists notificacion (
                              id SERIAL PRIMARY KEY,
                              usuarioid INT NOT NULL,
                              fecha_envio DATE NOT NULL,
                              mensaje VARCHAR(255),
                              tipo_notificacionid INT NOT NULL,
                              FOREIGN KEY (usuarioid) REFERENCES usuario (id),
                              FOREIGN KEY (tipo_notificacionid) REFERENCES tipo_notificacion (id)
);
