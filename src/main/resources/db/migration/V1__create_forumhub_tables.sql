-- Migration script to create tables for FórumHub

-- Table: COURSE
CREATE TABLE COURSE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL
);

-- Table: PROFILE
CREATE TABLE PROFILE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Table: USER
CREATE TABLE USERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Table: TOPIC
CREATE TABLE TOPIC (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    author_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    CONSTRAINT fk_topic_author FOREIGN KEY (author_id) REFERENCES USERS(id),
    CONSTRAINT fk_topic_course FOREIGN KEY (course_id) REFERENCES COURSE(id),
    CONSTRAINT unique_title_message UNIQUE (title, message)
);

-- Table: Answer
CREATE TABLE ANSWER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    topic_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    solution BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_answer_topic FOREIGN KEY (topic_id) REFERENCES TOPIC(id),
    CONSTRAINT fk_answer_author FOREIGN KEY (author_id) REFERENCES USERS(id)
);

-- Table: user_profile (Many-to-Many relationship between USER and PROFILE)
CREATE TABLE USER_PROFILE (
    user_id BIGINT NOT NULL,
    profile_id BIGINT NOT NULL,
    CONSTRAINT fk_user_profile_user FOREIGN KEY (user_id) REFERENCES USERS(id),
    CONSTRAINT fk_user_profile_profile FOREIGN KEY (profile_id) REFERENCES PROFILE(id),
    PRIMARY KEY (user_id, profile_id)
);

INSERT INTO COURSE (id, name, category) VALUES (1, 'MATH', 'Exact Sciences');
