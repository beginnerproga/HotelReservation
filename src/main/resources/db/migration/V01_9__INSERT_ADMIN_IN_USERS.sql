INSERT INTO users (username, password, email, role)
VALUES ('admin', '$2a$10$zPflpfVuRTdLvvcU8brtTO7CFoxZ0fzjCqtmEHbn5UQTy/YAVzu1e', 'admin@mail.ru', 'ROLE_ADMIN') ON CONFLICT (username) DO NOTHING