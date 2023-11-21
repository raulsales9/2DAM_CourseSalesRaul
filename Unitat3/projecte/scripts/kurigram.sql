CREATE DATABASE IF NOT EXISTS kurigram;

USE kurigram;-- Crear la tabla 'user'
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    phone INT
);

-- Crear la tabla 'posts'
CREATE TABLE posts (
    idPost INT AUTO_INCREMENT PRIMARY KEY,
    created_at DATE,
    likes INT,
    text VARCHAR(255),
    isSubmitted BOOLEAN,
    image VARCHAR(255),
    title VARCHAR(50),
    id_user INT,
    FOREIGN KEY (id_user) REFERENCES user (id)
);

-- Crear la tabla 'participant'
CREATE TABLE participant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    iduser INT,
    conversation_id INT,
    FOREIGN KEY (iduser) REFERENCES user (id),
    FOREIGN KEY (conversation_id) REFERENCES conversation (id)
);

-- Crear la tabla 'message'
CREATE TABLE message (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(255),
    conversation_id INT,
    iduser INT,
    sender_iduser INT,
    created_at TIMESTAMP,
    FOREIGN KEY (conversation_id) REFERENCES conversation (id),
    FOREIGN KEY (iduser) REFERENCES user (id),
    FOREIGN KEY (sender_iduser) REFERENCES user (id)
);

-- Crear la tabla 'follow'
CREATE TABLE follow (
    id INT AUTO_INCREMENT PRIMARY KEY,
    followers INT,
    following INT,
    user_id INT,
    follow_id INT,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (follow_id) REFERENCES user (id)
);

-- Crear la tabla 'event'
CREATE TABLE event (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    place VARCHAR(50),
    name VARCHAR(50),
    end_date DATE,
    start_date DATE,
    imagen VARCHAR(255)
);

-- Crear la tabla 'conversation'
CREATE TABLE conversation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    last_message_id INT,
    FOREIGN KEY (last_message_id) REFERENCES message (id)
);
