CREATE TABLE users (
    id int primary key,
    username varchar(255),
    name varchar(255),
	surname varchar(255),
    password varchar(255),
    role varchar(255)
);
CREATE TABLE groups (
    g_id int primary key,
    g_name varchar(255),
    g_year int 
);
CREATE TABLE clubs(
    c_id int primary key,
    c_name varchar(255),
    e_description varchar(255)
);

CREATE TABLE clubstudent (
    cs_id int primary key,
    club_id int,
    volunteers_id int 
);
CREATE TABLE groupstudent (
    gs_id int primary key,
    group_id int,
    student_id int 
);
CREATE TABLE news (
    n_id int primary key,
    n_title varchar(255),
	n_description varchar(255),
    publisher int 
);
CREATE TABLE events (
    e_id int primary key,
    e_event varchar(255),
	e_description varchar(255)
);
CREATE TABLE eventstudent(
    e_id int primary key,
    event_id int,
    listener_id int
);
ALTER TABLE groupstudent
ADD FOREIGN KEY (student_id) REFERENCES users(id);

ALTER TABLE groupstudent
ADD FOREIGN KEY (group_id) REFERENCES groups(g_id);

ALTER TABLE news
ADD FOREIGN KEY (publisher) REFERENCES users(id);

ALTER TABLE eventstudent
ADD FOREIGN KEY (listener_id) REFERENCES users(id);

ALTER TABLE eventstudent
ADD FOREIGN KEY (event_id) REFERENCES events(e_id);


ALTER TABLE clubstudent
ADD FOREIGN KEY (volunteers_id) REFERENCES users(id);

ALTER TABLE clubstudent
ADD FOREIGN KEY (club_id) REFERENCES clubs(c_id);


