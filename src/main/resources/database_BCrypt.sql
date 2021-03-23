# drop table users_roles;
# drop table users;
# drop table roles;


INSERT preDB3131.users
VALUES (null, 45, 'Admin', '$2a$10$DK2Yd6NWHU9cGcJqOjbpBuV1RSF51HfEncxiD7XXLNTVYfPiR/KMi ', 'Admins', 'admin'),
       (null, 31, 'Test2Role', '$2a$10$MmdCwK3PrBUSdXs/1dcecOG9jDp28Rt0G502Bna9ZbMgWFSE6MhV.', 'LastNameTest2Role',
        'test2role'),
       (null, 12, 'Test', '$2a$10$tkaaFW0TJ6UTwr9rbofhD.e3nnmxAmHWofy1U5AQnTh7ogbFAgSgK', 'LastNameTest', 'test');
INSERT preDB3131.roles
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');
INSERT preDB3131.users_roles
VALUES (1, 2),
       (1, 1),
       (2, 2),
       (2, 1),
       (3, 1);
