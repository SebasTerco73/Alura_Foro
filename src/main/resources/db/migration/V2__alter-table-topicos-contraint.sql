ALTER TABLE topicos
ADD CONSTRAINT unique_titulo_mensaje UNIQUE (titulo, mensaje);