-- Crea una tabla de jugadores si no existe
CREATE TABLE IF NOT EXISTS jugador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nick VARCHAR(255),
    dataRegistre DATETIME

INSERT INTO jugador (nick, dataRegistre) VALUES
    ('Jugador1', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador2', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador3', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador4', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador5', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador6', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador7', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador8', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador9', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador10', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador11', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador12', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador13', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador14', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador15', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador16', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador17', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador18', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador19', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador20', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador21', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador22', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador23', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador24', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador25', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador26', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY)),
    ('Jugador27', DATE_SUB(NOW(), INTERVAL ROUND(RAND() * 365) DAY));
