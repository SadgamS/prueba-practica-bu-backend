CREATE SCHEMA IF NOT EXISTS usuarios;
CREATE SCHEMA IF NOT EXISTS proyecto;

CREATE SEQUENCE IF NOT EXISTS usuarios.personas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE IF NOT EXISTS usuarios.personas (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('usuarios.personas_id_seq'),
    nombres VARCHAR(100) NOT NULL,
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100),
    tipo_documento VARCHAR(15) NOT NULL,
    documento_identidad VARCHAR(15) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    genero VARCHAR(1) NOT NULL,
    "_creado_por" BIGINT NOT NULL,
	"_estado" varchar(20) NOT NULL,
	"_fecha_creacion" timestamp(6) NOT NULL,
	"_fecha_modificacion" timestamp(6) NULL,
	"_modificado_por" BIGINT NULL,
	"_transaccion" varchar(20) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS usuarios.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS usuarios.roles (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('usuarios.roles_id_seq'),
    nombre VARCHAR(50) NOT NULL,
    "_creado_por" BIGINT NOT NULL,
    "_estado" varchar(20) NOT NULL,
    "_fecha_creacion" timestamp(6) NOT NULL,
    "_fecha_modificacion" timestamp(6) NULL,
    "_modificado_por" BIGINT NULL,
    "_transaccion" varchar(20) NOT NULL
    CHECK (nombre IN ('ADMIN', 'USUARIO'))
);

CREATE SEQUENCE IF NOT EXISTS usuarios.usuarios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS usuarios.usuarios (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('usuarios.usuarios_id_seq'),
    id_persona BIGINT NOT NULL,
    nombre_usuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    "_creado_por" BIGINT NOT NULL,
	"_estado" varchar(20) NOT NULL,
	"_fecha_creacion" timestamp(6) NOT NULL,
	"_fecha_modificacion" timestamp(6) NULL,
	"_modificado_por" BIGINT NULL,
	"_transaccion" varchar(20) NOT NULL,
    FOREIGN KEY (id_persona) REFERENCES usuarios.personas(id)
);

CREATE SEQUENCE IF NOT EXISTS usuarios.usuarios_roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS usuarios.usuarios_roles (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('usuarios.usuarios_roles_id_seq'),
    id_usuario BIGINT NOT NULL,
    id_rol BIGINT NOT NULL,
    "_creado_por" BIGINT NOT NULL,
    "_estado" varchar(20) NOT NULL,
    "_fecha_creacion" timestamp(6) NOT NULL,
    "_fecha_modificacion" timestamp(6) NULL,
    "_modificado_por" BIGINT NULL,
    "_transaccion" varchar(20) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios.usuarios(id),
    FOREIGN KEY (id_rol) REFERENCES usuarios.roles(id)
);


-- Roles
INSERT INTO usuarios.roles
(nombre, "_creado_por", "_estado", "_fecha_creacion", "_fecha_modificacion", "_modificado_por", "_transaccion")
VALUES('ADMIN', 1, 'ACTIVO', now(), NULL, NULL, 'CREACION');

INSERT INTO usuarios.roles
(nombre, "_creado_por", "_estado", "_fecha_creacion", "_fecha_modificacion", "_modificado_por", "_transaccion")
VALUES('USUARIO', 1, 'ACTIVO', now(), NULL, NULL, 'CREACION');

-- Usuario Admin
INSERT INTO usuarios.personas
(nombres, apellido_paterno, apellido_materno, tipo_documento, documento_identidad, fecha_nacimiento, genero, "_creado_por", "_estado", "_fecha_creacion", "_fecha_modificacion", "_modificado_por", "_transaccion")
VALUES('Administrador', 'Admin', 'Admin', 'CI', '12345678', '1990-01-01', 'M', 1, 'ACTIVO', now(), NULL, NULL, 'CREACION');

-- contrasena: admin
INSERT INTO usuarios.usuarios
(id_persona, nombre_usuario, contrasena, "_creado_por", "_estado", "_fecha_creacion", "_fecha_modificacion", "_modificado_por", "_transaccion")
VALUES(1, 'admin', '$2a$10$q1mSg8kzD7PnaUA9huv67OPoLPR4ijax.Wu8OoNfEdqKljeVY0W02', 1, 'ACTIVO', now(), NULL, NULL, 'CREACION');

-- Asignar roles
INSERT INTO usuarios.usuarios_roles
(id_usuario, id_rol, "_creado_por", "_estado", "_fecha_creacion", "_fecha_modificacion", "_modificado_por", "_transaccion")
VALUES(1, 1, 1, 'ACTIVO', now(), NULL, NULL, 'CREACION');

INSERT INTO usuarios.usuarios_roles
(id_usuario, id_rol, "_creado_por", "_estado", "_fecha_creacion", "_fecha_modificacion", "_modificado_por", "_transaccion")
VALUES(1, 2, 1, 'ACTIVO', now(), NULL, NULL, 'CREACION');