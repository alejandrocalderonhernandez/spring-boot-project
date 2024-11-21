-- Insertar datos en la tabla AppUser
INSERT INTO App_User (name, age, mail, password) VALUES ('User1', 25, 'user1@example.com', '$2a$10$2Dbs7pKECSqPQg9sIYYdFeaKDlCiW6IDm9Ok/lmv7DmVquLUd0Rpa');
INSERT INTO App_User (name, age, mail, password) VALUES ('User2', 30, 'user2@example.com', '$2a$10$2Dbs7pKECSqPQg9sIYYdFeaKDlCiW6IDm9Ok/lmv7DmVquLUd0Rpa');
INSERT INTO App_User (name, age, mail, password) VALUES ('User3', 28, 'user3@example.com', '$2a$10$2Dbs7pKECSqPQg9sIYYdFeaKDlCiW6IDm9Ok/lmv7DmVquLUd0Rpa');
INSERT INTO App_User (name, age, mail, password) VALUES ('User4', 29, 'user4@example.com', '$2a$10$2Dbs7pKECSqPQg9sIYYdFeaKDlCiW6IDm9Ok/lmv7DmVquLUd0Rpa');

-- Insertar datos en la tabla Page para cada usuario
INSERT INTO Page (date_creation, title, id_User) VALUES (CURRENT_TIMESTAMP, 'User1 Page', 1);
INSERT INTO Page (date_creation, title, id_User) VALUES (CURRENT_TIMESTAMP, 'User2 Page', 2);
INSERT INTO Page (date_creation, title, id_User) VALUES (CURRENT_TIMESTAMP, 'User3 Page', 3);

-- Insertar datos en la tabla Post para cada página
-- Posts para la página de User1
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 1 for User1', 'img1.jpg', 1);
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 2 for User1', 'img2.jpg', 1);
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 3 for User1', 'img3.jpg', 1);
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 4 for User1', 'img4.jpg', 1);
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 5 for User1', 'img5.jpg', 1);

-- Posts para la página de User2
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 1 for User2', 'img1.jpg', 2);
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 2 for User2', 'img2.jpg', 2);
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 3 for User2', 'img3.jpg', 2);
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 4 for User2', 'img4.jpg', 2);
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 5 for User2', 'img5.jpg', 2);

-- Posts para la página de User3
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 1 for User3', 'img1.jpg', 3);
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 2 for User3', 'img2.jpg', 3);
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 3 for User3', 'img3.jpg', 3);
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 4 for User3', 'img4.jpg', 3);
INSERT INTO Post (date_creation, content, img, id_page) VALUES (CURRENT_TIMESTAMP, 'Content 5 for User3', 'img5.jpg', 3);
