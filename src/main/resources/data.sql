INSERT INTO users (id, age, email, name , password) VALUES (1, 25, 'info@memorynotfound.com', 'admin' , '$2a$12$8FTi1QnxJDM1i/dqtsIkEeWCplhy/ddhwR1fLMR2RJ75KQ1aUd/8W');

INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);