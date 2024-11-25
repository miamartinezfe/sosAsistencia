-- Tabla de pedidos
CREATE TABLE pedidos (
    id BIGSERIAL PRIMARY KEY,
    estado VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL
);

-- Validar los estados permitidos
ALTER TABLE pedidos
ADD CONSTRAINT estado_check CHECK (estado IN ('PENDIENTE', 'EN_PROCESO', 'CANCELADO', 'COMPLETADO'));

-- Tabla de productos
CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio NUMERIC(10, 2) NOT NULL,
    pedido_id BIGINT,
    FOREIGN KEY (pedido_id) REFERENCES pedidos (id) ON DELETE CASCADE
);

-- Relación entre pedidos y productos
CREATE TABLE pedido_products (
    pedido_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (pedido_id, product_id),
    FOREIGN KEY (pedido_id) REFERENCES pedidos (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);