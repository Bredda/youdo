INSERT INTO users(id, password, username)
VALUES (1, '$2a$10$JZStZdDZ/DvVUEZzlhHfH.SlQ.IzM9pJuplK74PtTOFMk5OFJ7wtq', 'younup');

INSERT INTO todos(id, text, is_completed, user_id)
VALUES (2, 'Take over the world', false, 1);

INSERT INTO todos(id, text, is_completed, user_id)
VALUES (1, 'Buy some beer', true, 1);

INSERT INTO todos(id, text, is_completed, user_id)
VALUES (3, 'Take some rest', false, 1);