-- Insert data into faculty table
INSERT INTO faculty (faculty_name) VALUES 
  ('Faculty of Science'),
  ('Faculty of Arts'),
  ('Faculty of Engineering');

-- Insert data into group table
INSERT INTO `group` (group_name) VALUES 
  ('Group A'),
  ('Group B'),
  ('Group C');

-- Insert data into period table
INSERT INTO period (weekday, start_time, end_time) VALUES 
  ('MON', '08:00:00', '08:30:00'),
  ('MON', '08:30:00', '09:00:00'),
  ('MON', '09:00:00', '09:30:00'),
  ('MON', '09:30:00', '10:00:00'),
  ('MON', '10:00:00', '10:30:00'),
  ('MON', '10:30:00', '11:00:00'),
  ('MON', '11:00:00', '11:30:00'),
  ('MON', '11:30:00', '12:00:00'),
  ('TUE', '08:00:00', '08:30:00'),
  ('TUE', '08:30:00', '09:00:00'),
  ('TUE', '09:00:00', '09:30:00'),
  ('TUE', '09:30:00', '10:00:00'),
  ('TUE', '10:00:00', '10:30:00'),
  ('TUE', '10:30:00', '11:00:00'),
  ('TUE', '11:00:00', '11:30:00'),
  ('TUE', '11:30:00', '12:00:00'),
  ('WED', '08:00:00', '08:30:00'),
  ('WED', '08:30:00', '09:00:00'),
  ('WED', '09:00:00', '09:30:00'),
  ('WED', '09:30:00', '10:00:00'),
  ('WED', '10:00:00', '10:30:00'),
  ('WED', '10:30:00', '11:00:00'),
  ('WED', '11:00:00', '11:30:00'),
  ('WED', '11:30:00', '12:00:00'),
  ('THU', '08:00:00', '08:30:00'),
  ('THU', '08:30:00', '09:00:00'),
  ('THU', '09:00:00', '09:30:00'),
  ('THU', '09:30:00', '10:00:00'),
  ('THU', '10:00:00', '10:30:00'),
  ('THU', '10:30:00', '11:00:00'),
  ('THU', '11:00:00', '11:30:00'),
  ('THU', '11:30:00', '12:00:00'),
  ('FRI', '08:00:00', '08:30:00'),
  ('FRI', '08:30:00', '09:00:00'),
  ('FRI', '09:00:00', '09:30:00'),
  ('FRI', '09:30:00', '10:00:00'),
  ('FRI', '10:00:00', '10:30:00'),
  ('FRI', '10:30:00', '11:00:00'),
  ('FRI', '11:00:00', '11:30:00'),
  ('FRI', '11:30:00', '12:00:00');

-- Insert data into space table
INSERT INTO space (space_name, space_description, space_status, space_type, capacity) VALUES 
  ('Room 101', 'Lecture Hall', 'OPEN', 'CLASSROOM', 50),
  ('Lab 201', 'Computer Lab', 'CLOSED', 'LABORATORY', 30),
  ('Conference Room', 'Meeting Room', 'OPEN', 'AUDITORIUM', 20);

-- Insert data into subject table
INSERT INTO subject (subject_name, subject_description, subject_code, faculty_id) VALUES 
  ('Mathematics', 'Introduction to Calculus', 'MATH101', 1),
  ('History', 'World History', 'HIST101', 2),
  ('Computer Science', 'Data Structures', 'CS202', 3);

-- Insert data into person table
INSERT INTO person (first_name, last_name, email, username, aud_date, aud_host, aud_user) VALUES 
  ('John', 'Doe', 'john.doe@example.com', 'john.doe', NOW(), 'localhost', 'admin'),
  ('Jane', 'Smith', 'jane.smith@example.com', 'jane.smith', NOW(), 'localhost', 'admin'),
  ('Bob', 'Johnson', 'bob.johnson@example.com', 'bob.johnson', NOW(), 'localhost', 'admin');

-- Insert data into person table w/o aud fields
INSERT INTO person (first_name, last_name, email, username) VALUES 
  ('John', 'Doe', 'john.doe@example.com', 'john.doe'),
  ('Jane', 'Smith', 'jane.smith@example.com', 'jane.smith'),
  ('Bob', 'Johnson', 'bob.johnson@example.com', 'bob.johnson');

-- Insert data into schedule table
INSERT INTO schedule (open_date, close_date, aud_date, aud_host, aud_user, space_space_id, period_period_id) VALUES 
  ('2023-01-15', '2023-05-15', NOW(), 'localhost', 'admin', 1, 1),
  ('2023-02-10', '2023-06-10', NOW(), 'localhost', 'admin', 2, 2),
  ('2023-03-20', '2023-07-20', NOW(), 'localhost', 'admin', 3, 3);

-- Insert data into schedule table w/o aud fields
INSERT INTO schedule (open_date, close_date, period_id, space_id) VALUES 
  ('2023-01-15', '2023-05-15', 1, 1),
  ('2023-02-10', '2023-06-10', 2, 2),
  ('2023-03-20', '2023-07-20', 3, 3);


-- Insert data into solicitude table
INSERT INTO solicitude (date_time, solicitude_status, recurrent, start_date, end_date, aud_date, aud_host, aud_user, subject_subject_id, person_person_id) VALUES 
  ('2023-01-05 10:00:00', 1, 0, '2023-01-10', '2023-01-20', NOW(), 'localhost', 'admin', 1, 1),
  ('2023-02-15 14:30:00', 2, 1, '2023-02-20', '2023-03-20', NOW(), 'localhost', 'admin', 2, 2),
  ('2023-03-25 09:00:00', 0, 0, '2023-04-01', '2023-04-10', NOW(), 'localhost', 'admin', 3, 3);

-- Insert data into solicitude table w/o aud fields
INSERT INTO solicitude (register_date, solicitude_status, recurrent, start_date, end_date, subject_id, person_id) VALUES 
  ('2023-01-05', 'APPROVED', 0, '2023-01-10', '2023-01-20', 1, 1),
  ('2023-02-15', 'APPROVED', 1, '2023-02-20', NULL, 2, 2),
  ('2023-03-25', 'PENDING', 0, '2023-04-01', '2023-04-10', 3, 3);

-- Insert data into assignation table
INSERT INTO assignation (approve_date, aud_date, aud_host, aud_user, schedule_schedule_id, solicitude_id) VALUES 
  ('2023-01-12', NOW(), 'localhost', 'admin', 1, 1),
  ('2023-02-20', NOW(), 'localhost', 'admin', 2, 2),
  ('2023-03-30', NOW(), 'localhost', 'admin', 3, 3);

-- Insert data into assignation table w/o aud fields
INSERT INTO assignation (approve_date, ai_generated, schedule_id, solicitude_id) VALUES 
  ('2023-01-12', 0, 1, 1),
  ('2023-02-20', 0, 2, 2),
  (NULL, 0, 3, 3);

/* ALTER TABLE assignation DROP FOREIGN KEY FKj8pu1iycyqeit70wlv6abbkep; */
