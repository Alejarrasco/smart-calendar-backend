UPDATE solicitude SET recurrent = 0 WHERE end_date IS NULL
UPDATE solicitude SET recurrent = 1 WHERE end_date IS NOT NULL

UPDATE solicitude SET end_date = '2023-12-03' WHERE solicitude_id = 1
UPDATE solicitude SET end_date = '2023-12-03' WHERE solicitude_id = 3

UPDATE solicitude SET start_date = '2023-11-15' WHERE solicitude_id = 2