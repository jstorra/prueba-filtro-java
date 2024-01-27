CREATE TABLE categoria (
    idCategoria INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombreCategoria VARCHAR(255) NOT NULL
);

CREATE TABLE proveedor (
    idProveedor INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombreProveedor VARCHAR(255) NOT NULL
);

CREATE TABLE producto (
    idProducto INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombreProducto VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    idCategoria INT,
    idProveedor INT
);

CREATE TABLE inventario (
    idInventario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL
);

ALTER TABLE producto ADD FOREIGN KEY idCategoria REFERENCES categoria(idCategoria);
ALTER TABLE producto ADD FOREIGN KEY idProveedor REFERENCES proveedor(idProveedor);

ALTER TABLE inventario ADD FOREIGN KEY idProducto REFERENCES producto(idProducto);