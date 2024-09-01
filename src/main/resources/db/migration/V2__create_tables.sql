CREATE SEQUENCE IF NOT EXISTS proyecto.clientes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS proyecto.clientes (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('proyecto.clientes_id_seq'),
    id_usuario BIGINT NOT NULL,
    "_creado_por" BIGINT NOT NULL,
	"_estado" varchar(20) NOT NULL,
	"_fecha_creacion" timestamp(6) NOT NULL,
	"_fecha_modificacion" timestamp(6) NULL,
	"_modificado_por" BIGINT NULL,
	"_transaccion" varchar(20) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios.usuarios(id)
);

CREATE SEQUENCE IF NOT EXISTS proyecto.cuentas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS proyecto.cuentas (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('proyecto.cuentas_id_seq'),
    tipo_producto varchar(50) NOT NULL,
    numero_cuenta varchar(100) NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    moneda varchar(5) NOT NULL,
    fecha_apertura DATE NOT NULL,
    sucursal varchar(50) NOT NULL,
    id_cliente BIGINT NOT NULL,
    "_creado_por" BIGINT NOT NULL,
    "_estado" varchar(20) NOT NULL,
    "_fecha_creacion" timestamp(6) NOT NULL,
    "_fecha_modificacion" timestamp(6) NULL,
    "_modificado_por" BIGINT NULL,
    "_transaccion" varchar(20) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES proyecto.clientes(id),
    CHECK (moneda IN ('BS', 'USD'))
);