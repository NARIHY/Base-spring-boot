-- Création de la table "user"
CREATE TABLE `user` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `creation_date` DATETIME NOT NULL,
    `last_modified_date` DATETIME
);

-- Ajout de quelques données d'exemple pour tester la table "user"
INSERT INTO `user` (`name`, `email`, `password`, `creation_date`, `last_modified_date`)
VALUES
('John Doe', 'john.doe@example.com', 'password123', NOW(), NOW()),
('Jane Smith', 'jane.smith@example.com', 'securePass456', NOW(), NOW()),
('Alice Brown', 'alice.brown@example.com', 'myPassword789', NOW(), NOW());
