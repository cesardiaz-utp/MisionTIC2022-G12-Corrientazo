CREATE TABLE Mesa (
    id INTEGER PRIMARY KEY,
    numero VARCHAR(10) NOT NULL
);

CREATE TABLE TipoOpcion (
    id INTEGER PRIMARY KEY,
    tipo VARCHAR(10) NOT NULL,
    precio INTEGER NOT NULL
);

CREATE TABLE Adicional (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio INTEGER NOT NULL
);

CREATE TABLE Sopa (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE Principio (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE Carne (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE Ensalada (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE Jugo (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE Pedido (
    id INTEGER PRIMARY KEY,
    cliente VARCHAR(100),
    estado VARCHAR(50) NOT NULL,
    id_mesa INTEGER NOT NULL,
    id_tipo_opcion INTEGER NOT NULL,
    id_sopa INTEGER,
    id_principio INTEGER NOT NULL,
    id_carne INTEGER NOT NULL,
    id_ensalada INTEGER,
    id_jugo INTEGER NOT NULL,
    CONSTRAINT Pedido_Mesa_FK FOREIGN KEY (id_mesa) REFERENCES Mesa (id), 
    CONSTRAINT Pedido_TipoOpcion_FK FOREIGN KEY (id_tipo_opcion) REFERENCES TipoOpcion (id), 
    CONSTRAINT Pedido_Sopa_FK FOREIGN KEY (id_sopa) REFERENCES Sopa (id), 
    CONSTRAINT Pedido_Principio_FK FOREIGN KEY (id_principio) REFERENCES Principio (id), 
    CONSTRAINT Pedido_Carne_FK FOREIGN KEY (id_carne) REFERENCES Carne (id), 
    CONSTRAINT Pedido_Ensalada_FK FOREIGN KEY (id_ensalada) REFERENCES Ensalada (id), 
    CONSTRAINT Pedido_Jugo_FK FOREIGN KEY (id_jugo) REFERENCES Jugo (id)
);

CREATE TABLE PedidoAdicional (
    id_pedido INTEGER,
    id_adicional INTEGER,
    CONSTRAINT PedidoAdicional_PK PRIMARY KEY (id_pedido, id_adicional),
    CONSTRAINT PedidoAdicional_Pedido_FK FOREIGN KEY (id_pedido) REFERENCES Pedido (id), 
    CONSTRAINT PedidoAdicional_Adicional_FK FOREIGN KEY (id_adicional) REFERENCES Adicional (id) 
);
