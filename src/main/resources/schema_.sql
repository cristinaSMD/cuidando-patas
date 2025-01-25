CREATE TABLE "USER" (
    id UUID PRIMARY KEY,                      -- Identificador único de tipo UUID.
    username VARCHAR(50) NOT NULL UNIQUE,     -- Nombre de usuario (máx. 50 caracteres, único y no nulo).
    password VARCHAR(100) NOT NULL,           -- Contraseña (máx. 100 caracteres, no nulo).
    email VARCHAR(100) NOT NULL UNIQUE,       -- Correo electrónico (máx. 100 caracteres, único y no nulo).
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, -- Fecha y hora de creación.
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL -- Fecha y hora de última actualización.
);

CREATE TABLE "LESSON" (
    id UUID PRIMARY KEY,                              -- Identificador único de tipo UUID.
    title VARCHAR(255) NOT NULL,                     -- Título de la lección (máx. 255 caracteres, no nulo).
    description VARCHAR(1000),                       -- Descripción de la lección (máx. 1000 caracteres, puede ser nulo).
    video VARCHAR(500) NOT NULL,                     -- URL del video (máx. 500 caracteres, no nulo).
    dance VARCHAR(50) NOT NULL,                      -- Tag de estilo de danza (máx. 50 caracteres, no nulo).
    tags VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, -- Fecha y hora de creación.
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Fecha y hora de última actualización.
    CONSTRAINT fk_dance_tag CHECK (dance IN ('DANCE1', 'DANCE2', 'DANCE3')) -- Enum restringido (ajustar nombres).
);

CREATE TABLE STEP (
    ID UUID PRIMARY KEY, -- Clave primaria generada como UUID
    CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Fecha de creación (no modificable)
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Fecha de actualización
    -- Relación con LESSON
    LESSON_ID UUID NOT NULL, -- Relación con lección (Muchos pasos pueden pertenecer a una lección)
    -- Líderes y seguidores (podemos usar columnas serializadas o diseñar tablas intermedias)
    LEADER TEXT, -- Campo para texto serializado (JSON o separado por comas)
    FOLLOWER TEXT -- Campo para texto serializado (JSON o separado por comas)
);

-- Tabla intermedia para relacionar lecciones y pasos.
CREATE TABLE "STEP_LESSON" (
    step_id UUID NOT NULL,                              -- ID de Step.
    lesson_id UUID NOT NULL,                          -- ID de Lesson.
    PRIMARY KEY (step_id, lesson_id),
    FOREIGN KEY (lesson_id) REFERENCES "LESSON"(id)
);

-- Tabla intermedia para relacionar lecciones y usuarios.
CREATE TABLE "USER_LESSON" (
    user_id UUID NOT NULL,                            -- ID de User.
    lesson_id UUID NOT NULL,                          -- ID de Lesson.
    PRIMARY KEY (user_id, lesson_id),
    FOREIGN KEY (lesson_id) REFERENCES "LESSON"(id)
);

CREATE TABLE USER_LESSONS (
    user_id UUID,
    lesson_id UUID,
    PRIMARY KEY (user_id, lesson_id),
    FOREIGN KEY (user_id) REFERENCES "USER" (id),
    FOREIGN KEY (lesson_id) REFERENCES LESSON (id)
);
