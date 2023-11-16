UPDATE solicitude SET recurrent = 0 WHERE end_date IS NULL;
UPDATE solicitude SET recurrent = 1 WHERE end_date IS NOT NULL;

UPDATE solicitude SET end_date = '2023-12-03' WHERE solicitude_id = 1;
UPDATE solicitude SET end_date = '2023-12-03' WHERE solicitude_id = 3;

UPDATE solicitude SET start_date = '2023-11-15' WHERE solicitude_id = 2;

-- Insert data into schedule table w/o aud fields
INSERT INTO schedule (open_date, close_date, period_id, space_id) VALUES 
  ('2023-01-15', '2023-05-15', 28, 1),
  ('2023-02-10', '2023-06-10', 29, 2),
  ('2023-03-20', '2023-07-20', 30, 3);

INSERT INTO assignation (approve_date, ai_generated, schedule_id, solicitude_id) VALUES 
  ('2023-01-12', 0, 4, 1),
  (NULL, 0, 6, 3);

INSERT INTO solicitude (register_date, solicitude_status, recurrent, start_date, end_date, subject_id, person_id) VALUES
  ('2023-02-15', 'APPROVED', 0, '2023-11-17', NULL, 2, 2);

INSERT INTO assignation (approve_date, ai_generated, schedule_id, solicitude_id) VALUES
  (NULL, 0, 5, 4);
 