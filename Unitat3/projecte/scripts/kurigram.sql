CREATE DATABASE IF NOT EXISTS kurigram;

USE kurigram;


DROP TABLE IF EXISTS User;
CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    phone INT
);

DROP TABLE IF EXISTS Post;
CREATE TABLE Post (
    idPost INT AUTO_INCREMENT PRIMARY KEY,
    created_at DATE,
    likes INT,
    text VARCHAR(255),
    isSubmitted BOOLEAN,
    image VARCHAR(255),
    title VARCHAR(50),
    id_user INT,
    UNIQUE KEY `unique_user_post` (`id_user`),
    FOREIGN KEY (id_user) REFERENCES user (id)
);


DROP TABLE IF EXISTS Message;
CREATE TABLE Message (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(255),
    conversation_id INT,
    iduser INT,
    sender_iduser INT,
    created_at TIMESTAMP,
    FOREIGN KEY (conversation_id) REFERENCES Conversation (id),
    FOREIGN KEY (iduser) REFERENCES user (id),
    FOREIGN KEY (sender_iduser) REFERENCES user (id),
    INDEX (conversation_id),
    INDEX (iduser),
    INDEX (sender_iduser)
);

DROP TABLE IF EXISTS Follow;
CREATE TABLE Follow (
    id INT AUTO_INCREMENT PRIMARY KEY,
    followers INT,
    following INT,
    user_id INT,
    follow_id INT,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (follow_id) REFERENCES user (id)
);

DROP TABLE IF EXISTS Event;
CREATE TABLE Event (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    place VARCHAR(50),
    name VARCHAR(50),
    end_date DATE,
    start_date DATE,
    imagen VARCHAR(255)
);

DROP TABLE IF EXISTS Conversation;
CREATE TABLE Conversation (
    id INTEGER NOT NULL AUTO_INCREMENT,
    last_message_id INTEGER,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Participant;
CREATE TABLE Participant (
    id INTEGER NOT NULL AUTO_INCREMENT,
    conversation_id INTEGER,
    iduser INT,
    PRIMARY KEY (id),
    FOREIGN KEY (conversation_id) REFERENCES Conversation (id),
    FOREIGN KEY (iduser) REFERENCES user (id)
);

