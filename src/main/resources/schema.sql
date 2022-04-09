DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions (
                               id INT AUTO_INCREMENT  PRIMARY KEY,
                               amount NUMERIC NOT NULL,
                               client_id INT NOT NULL,
                               date DATE DEFAULT NULL
);