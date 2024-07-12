CREATE TABLE TOPIC (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    message_prefix VARCHAR(255) GENERATED ALWAYS AS (LEFT(message, 255)) STORED,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    author_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    CONSTRAINT fk_topic_author FOREIGN KEY (author_id) REFERENCES USERS(id),
    CONSTRAINT fk_topic_course FOREIGN KEY (course_id) REFERENCES COURSE(id),
    CONSTRAINT unique_title_message UNIQUE (title, message_prefix)
);

CREATE TABLE ANSWER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    topic_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    solution BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_answer_topic FOREIGN KEY (topic_id) REFERENCES TOPIC(id),
    CONSTRAINT fk_answer_author FOREIGN KEY (author_id) REFERENCES USERS(id)
);

CREATE TABLE USER_PROFILE (
    user_id BIGINT NOT NULL,
    profile_id BIGINT NOT NULL,
    CONSTRAINT fk_user_profile_user FOREIGN KEY (user_id) REFERENCES USERS(id),
    CONSTRAINT fk_user_profile_profile FOREIGN KEY (profile_id) REFERENCES PROFILE(id),
    PRIMARY KEY (user_id, profile_id)
);

INSERT INTO COURSE (id, name, category) VALUES (1, 'MATH', 'Exact Sciences');