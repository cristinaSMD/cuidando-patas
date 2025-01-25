-- Insertar datos en la tabla "LESSON"
INSERT INTO "LESSON" (id, title, description, video, dance, created_at, updated_at)
VALUES
    ('7d9f4ea2-2c54-4abf-86c5-53e8cc123456', 'Jazz Basics', 'Introduction to basic jazz dance moves.', 'http://example.com/jazz_basics', 'DANCE1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('2bbc6e23-94a5-4dc8-b7e6-18e456234567', 'Advanced Jazz', 'Learn advanced jazz steps and techniques.', 'http://example.com/advanced_jazz', 'DANCE2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('9fadc013-be69-47cf-a2a1-2fc789456678', 'Jazz Fusion', 'Fusion techniques combining jazz with other styles.', 'http://example.com/jazz_fusion', 'DANCE3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar datos en la tabla "STEP"
INSERT INTO "STEP" (id, created_at, updated_at, lesson_id, leader, follower)
VALUES
    ('8d9a4ea2-3c54-4ac5-86d5-53e8cd223456', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '7d9f4ea2-2c54-4abf-86c5-53e8cc123456', '["Alice", "Bob"]', '["Charlie", "Diana"]'),
    ('9cbe6a23-94b5-5db8-c6e6-18e456654567', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '2bbc6e23-94a5-4dc8-b7e6-18e456234567', '["Eve"]', '["Frank"]'),
    ('bcadc013-bf69-48cf-b2b1-2fc112958678', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '9fadc013-be69-47cf-a2a1-2fc789456678', '["George", "Helen"]', '["Isaac"]');

-- Insertar datos en la tabla intermedia "STEPS" para relacionar "LESSON" y "STEP"
INSERT INTO "STEP_LESSON" (step_id, lesson_id)
VALUES
    ('8d9a4ea2-3c54-4ac5-86d5-53e8cd223456', '7d9f4ea2-2c54-4abf-86c5-53e8cc123456'), -- Warm-up pertenece a 'Jazz Basics'
    ('9cbe6a23-94b5-5db8-c6e6-18e456654567', '7d9f4ea2-2c54-4abf-86c5-53e8cc123456'), -- Jazz Step 1 pertenece a 'Jazz Basics'
    ('8d9a4ea2-3c54-4ac5-86d5-53e8cd223456', '2bbc6e23-94a5-4dc8-b7e6-18e456234567'), -- Jazz Step 2 pertenece a 'Advanced Jazz'
    ('bcadc013-bf69-48cf-b2b1-2fc112958678', '9fadc013-be69-47cf-a2a1-2fc789456678'); -- Cool Down pertenece a 'Jazz Fusion'

-- Insertar datos en la tabla "USER"
INSERT INTO "USER" (id, username, password, email, created_at, updated_at)
VALUES
    ('1a2b3c4d-5678-9ef0-1234-56789abcdef0', 'jazz_dancer_1', 'securepassword1', 'dancer1@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('2b3c4d5e-6789-0f12-3456-789abcdef012', 'jazz_dancer_2', 'securepassword2', 'dancer2@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('3c4d5e6f-7890-1f23-4567-89abcdef0123', 'jazz_dancer_3', 'securepassword3', 'dancer3@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar datos en la tabla intermedia "USER_LESSONS" para relacionar "LESSON" y "USER"
INSERT INTO "USER_LESSONS" (user_id, lesson_id)
VALUES
    ('1a2b3c4d-5678-9ef0-1234-56789abcdef0', '7d9f4ea2-2c54-4abf-86c5-53e8cc123456'), -- Usuario 1 inscrito en la lección 1
    ('2b3c4d5e-6789-0f12-3456-789abcdef012', '2bbc6e23-94a5-4dc8-b7e6-18e456234567'), -- Usuario 2 inscrito en la lección 2
    ('3c4d5e6f-7890-1f23-4567-89abcdef0123', '9fadc013-be69-47cf-a2a1-2fc789456678'), -- Usuario 3 inscrito en la lección 3
    ('1a2b3c4d-5678-9ef0-1234-56789abcdef0', '2bbc6e23-94a5-4dc8-b7e6-18e456234567'); -- Usuario 1 también inscrito en la lección 2