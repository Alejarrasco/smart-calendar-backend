CREATE VIEW planification AS
SELECT 
    a.assignation_id,
    sp.space_id,
    sp.space_name,
    sb.subject_name,
    pe.first_name,
    pe.last_name,
    sl.recurrent,
    sl.start_date,
    sl.end_date,
    pd.period_id,
    pd.weekday,
    pd.start_time,
    pd.end_time
FROM
    assignation a
        INNER JOIN
    schedule s ON a.schedule_id = s.schedule_id
        INNER JOIN
    space sp ON s.space_id = sp.space_id
        INNER JOIN
    period pd ON s.period_id = pd.period_id
        INNER JOIN
    solicitude sl ON a.solicitude_id = sl.solicitude_id
        INNER JOIN
    person pe ON sl.person_id = pe.person_id
        INNER JOIN
    subject sb ON sl.subject_id = sb.subject_id


--SELECT assignation_id, space_id, space_name, subject_name, first_name, last_name, recurrent, start_date, end_date, period_id, weekday, start_time, end_time FROM planification WHERE recurrent = 0 AND start_date <= '2023-11-13' AND end_date >= DATE_ADD('2023-11-13', INTERVAL 7 DAY) AND space_id = 1

--SELECT assignation_id, space_id, space_name, subject_name, first_name, last_name, recurrent, start_date, end_date, period_id, weekday, start_time, end_time FROM planification WHERE recurrent = 1 AND start_date BETWEEN '2023-11-13' AND DATE_ADD('2023-11-13', INTERVAL 7 DAY) AND space_id = ?
