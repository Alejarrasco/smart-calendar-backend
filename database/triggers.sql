-- Trigger for assignation table
DELIMITER //
CREATE TRIGGER after_assignation_insert
AFTER INSERT ON assignation
FOR EACH ROW
BEGIN
    INSERT INTO h_assignation (
        assignation_id,
        ai_generated,
        approve_date,
        schedule_id,
        solicitude_id,
        assignation_status,
        aud_date, 
        aud_host, 
        aud_user)
    VALUES (
        NEW.assignation_id,
        NEW.ai_generated,
        NEW.approve_date,
        NEW.schedule_id,
        NEW.solicitude_id,
        NEW.assignation_status,
        NEW.aud_date, 
        NEW.aud_host, 
        NEW.aud_user);
END;
//

-- Trigger for calendar_access table
DELIMITER //
CREATE TRIGGER after_calendar_access_insert
AFTER INSERT ON calendar_access
FOR EACH ROW
BEGIN
    INSERT INTO h_calendar_access (
        calendar_access_id,
        person_id,
        space_id,
        calendar_access_status,
        registered_date,
        aud_date,
        aud_host, 
        aud_user)
    VALUES (
        NEW.calendar_access_id,
        NEW.person_id,
        NEW.space_id,
        NEW.calendar_access_status,
        NEW.registered_date,
        NEW.aud_date,
        NEW.aud_host, 
        NEW.aud_user);
END;
//

-- Trigger for requirement table
DELIMITER //
DROP TRIGGER IF EXISTS after_requirement_insert;
CREATE TRIGGER after_requirement_insert
AFTER INSERT ON requirement
FOR EACH ROW
BEGIN
    INSERT INTO h_requirement (
        requirement_id,
        classes_per_week, 
        max_alumni,
        periods_per_class,
        semester, 
        space_type,  
        subject_id,
        requirement_status,
        aud_date, 
        aud_host, 
        aud_user)
    VALUES (
        NEW.requirement_id,
        NEW.classes_per_week, 
        NEW.max_alumni,
        NEW.periods_per_class,
        NEW.semester, 
        NEW.space_type,
        NEW.subject_id,  
        NEW.requirement_status,
        NEW.aud_date, 
        NEW.aud_host, 
        NEW.aud_user);
END;
//

-- Trigger for responsible table
DELIMITER //
CREATE TRIGGER after_responsible_insert
AFTER INSERT ON responsible
FOR EACH ROW
BEGIN
    INSERT INTO h_responsible (
        responsible_id,
        person_id,
        subject_id,
        responsible_status,
        aud_date, 
        aud_host, 
        aud_user)
    VALUES (
        NEW.responsible_id,
        NEW.person_id,
        NEW.subject_id,
        NEW.responsible_status,
        NEW.aud_date, 
        NEW.aud_host, 
        NEW.aud_user);
END;
//

-- Trigger for schedule table
DELIMITER //
CREATE TRIGGER after_schedule_insert
AFTER INSERT ON schedule
FOR EACH ROW
BEGIN
    INSERT INTO h_schedule (
        close_date, 
        open_date,
        period_id,
        space_id,
        schedule_status,
        aud_date, 
        aud_host, 
        aud_user)
    VALUES (
        NEW.close_date, 
        NEW.open_date,
        NEW.period_id,
        NEW.space_id,
        NEW.schedule_status,
        NEW.aud_date, 
        NEW.aud_host, 
        NEW.aud_user);
END;
//

-- Trigger for solicitude table
DELIMITER //
CREATE TRIGGER after_solicitude_insert
AFTER INSERT ON solicitude
FOR EACH ROW
BEGIN
    INSERT INTO h_solicitude (
        solicitude_id,
        start_date,
        end_date,
        recurrent,
        register_date,
        solicitude_status,
        person_id,
        subject_id,
        aud_date,
        aud_host,
        aud_user)
    VALUES (
        NEW.solicitude_id,
        NEW.start_date,
        NEW.end_date,
        NEW.recurrent,
        NEW.register_date,
        NEW.solicitude_status,
        NEW.person_id,
        NEW.subject_id,
        NEW.aud_date,
        NEW.aud_host,
        NEW.aud_user);
END;
//

-- Trigger for space table
DELIMITER //
CREATE TRIGGER after_space_insert
AFTER INSERT ON space
FOR EACH ROW
BEGIN
    INSERT INTO h_space (
        space_id,
        capacity,
        space_description,
        space_name,
        space_status,
        space_type,
        aud_date,
        aud_host,
        aud_user)
    VALUES (
        NEW.space_id,
        NEW.capacity,
        NEW.space_description,
        NEW.space_name,
        NEW.space_status,
        NEW.space_type,
        NEW.aud_date,
        NEW.aud_host,
        NEW.aud_user);
END;
//

-- Trigger for subject table
DELIMITER //
CREATE TRIGGER after_subject_insert
AFTER INSERT ON subject
FOR EACH ROW
BEGIN
    INSERT INTO h_subject (
        subject_id,
        subject_code,
        subject_description,
        subject_name,
        faculty_id,
        subject_status,
        aud_date,
        aud_host,
        aud_user)
    VALUES (
        NEW.subject_id,
        NEW.subject_code,
        NEW.subject_description,
        NEW.subject_name,
        NEW.faculty_id,
        NEW.subject_status,
        NEW.aud_date,
        NEW.aud_host,
        NEW.aud_user);
END;
//
DELIMITER ;

-- Update trigger for assignation table
DELIMITER //
CREATE TRIGGER after_assignation_update
AFTER UPDATE ON assignation
FOR EACH ROW
BEGIN
    UPDATE h_assignation
    SET
        ai_generated = NEW.ai_generated,
        approve_date = NEW.approve_date,
        schedule_id = NEW.schedule_id,
        solicitude_id = NEW.solicitude_id,
        assignation_status = NEW.assignation_status,
        aud_date = NEW.aud_date,
        aud_host = NEW.aud_host,
        aud_user = NEW.aud_user
    WHERE assignation_id = OLD.assignation_id;
END;
//

-- Update trigger for calendar_access table
DELIMITER //
CREATE TRIGGER after_calendar_access_update
AFTER UPDATE ON calendar_access
FOR EACH ROW
BEGIN
    UPDATE h_calendar_access
    SET
        person_id = NEW.person_id,
        space_id = NEW.space_id,
        calendar_access_status = NEW.calendar_access_status,
        registered_date = NEW.registered_date,
        aud_date = NEW.aud_date,
        aud_host = NEW.aud_host,
        aud_user = NEW.aud_user
    WHERE calendar_access_id = OLD.calendar_access_id;
END;
//

-- Update trigger for requirement table
DELIMITER //
CREATE TRIGGER after_requirement_update
AFTER UPDATE ON requirement
FOR EACH ROW
BEGIN
    UPDATE h_requirement
    SET
        classes_per_week = NEW.classes_per_week,
        max_alumni = NEW.max_alumni,
        periods_per_class = NEW.periods_per_class,
        semester = NEW.semester,
        space_type = NEW.space_type,
        requirement_status = NEW.requirement_status,
        aud_date = NEW.aud_date,
        aud_host = NEW.aud_host,
        aud_user = NEW.aud_user
    WHERE requirement_id = OLD.requirement_id;
END;
//

-- Update trigger for responsible table
DELIMITER //
CREATE TRIGGER after_responsible_update
AFTER UPDATE ON responsible
FOR EACH ROW
BEGIN
    UPDATE h_responsible
    SET
        person_id = NEW.person_id,
        subject_id = NEW.subject_id,
        responsible_status = NEW.responsible_status,
        aud_date = NEW.aud_date,
        aud_host = NEW.aud_host,
        aud_user = NEW.aud_user
    WHERE responsible_id = OLD.responsible_id;
END;
//

-- Update trigger for schedule table
DELIMITER //
CREATE TRIGGER after_schedule_update
AFTER UPDATE ON schedule
FOR EACH ROW
BEGIN
    UPDATE h_schedule
    SET
        close_date = NEW.close_date,
        open_date = NEW.open_date,
        period_id = NEW.period_id,
        space_id = NEW.space_id,
        schedule_status = NEW.schedule_status,
        aud_date = NEW.aud_date,
        aud_host = NEW.aud_host,
        aud_user = NEW.aud_user
    WHERE schedule_id = OLD.schedule_id;
END;
//

-- Update trigger for solicitude table
DELIMITER //
CREATE TRIGGER after_solicitude_update
AFTER UPDATE ON solicitude
FOR EACH ROW
BEGIN
    UPDATE h_solicitude
    SET
        start_date = NEW.start_date,
        end_date = NEW.end_date,
        recurrent = NEW.recurrent,
        register_date = NEW.register_date,
        solicitude_status = NEW.solicitude_status,
        person_id = NEW.person_id,
        subject_id = NEW.subject_id,
        aud_date = NEW.aud_date,
        aud_host = NEW.aud_host,
        aud_user = NEW.aud_user
    WHERE solicitude_id = OLD.solicitude_id;
END;
//

-- Update trigger for space table
DELIMITER //
CREATE TRIGGER after_space_update
AFTER UPDATE ON space
FOR EACH ROW
BEGIN
    UPDATE h_space
    SET
        capacity = NEW.capacity,
        space_description = NEW.space_description,
        space_name = NEW.space_name,
        space_status = NEW.space_status,
        space_type = NEW.space_type,
        aud_date = NEW.aud_date,
        aud_host = NEW.aud_host,
        aud_user = NEW.aud_user
    WHERE space_id = OLD.space_id;
END;
//

-- Update trigger for subject table
DELIMITER //
CREATE TRIGGER after_subject_update
AFTER UPDATE ON subject
FOR EACH ROW
BEGIN
    UPDATE h_subject
    SET
        subject_code = NEW.subject_code,
        subject_description = NEW.subject_description,
        subject_name = NEW.subject_name,
        faculty_id = NEW.faculty_id,
        subject_status = NEW.subject_status,
        aud_date = NEW.aud_date,
        aud_host = NEW.aud_host,
        aud_user = NEW.aud_user
    WHERE subject_id = OLD.subject_id;
END;
//
DELIMITER ;

SELECT Concat('DROP TRIGGER ', Trigger_Name, ';') FROM information_schema.TRIGGERS WHERE TRIGGER_SCHEMA = 'smart_calendar';