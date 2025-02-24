CREATE TABLE evento_user (
                                     evento_id BIGINT,
                                     user_id BIGINT,

                                     PRIMARY KEY (evento_id, user_id),
                                     FOREIGN KEY (evento_id) REFERENCES evento(id),
                                     FOREIGN KEY (user_id) REFERENCES user(id)
);
