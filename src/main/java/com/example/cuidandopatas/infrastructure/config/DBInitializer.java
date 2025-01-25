package com.example.cuidandopatas.infrastructure.config;

import com.example.cuidandopatas.domain.entity.Lesson;
import com.example.cuidandopatas.domain.entity.Step;
import com.example.cuidandopatas.domain.entity.User;
import com.example.cuidandopatas.domain.enums.DanceEnum;
import com.example.cuidandopatas.domain.repository.LessonRepository;
import com.example.cuidandopatas.domain.repository.StepRepository;
import com.example.cuidandopatas.domain.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Component
public class DBInitializer {

    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final StepRepository stepRepository;

    @Autowired
    public DBInitializer(UserRepository userRepository, LessonRepository lessonRepository, StepRepository stepRepository) {
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.stepRepository = stepRepository;
    }

    @PostConstruct
    public void onInit() {
        initDatabase();
    }

    // Método que se ejecutará después de que se cargue el contexto
    @Transactional
    public void initDatabase() {
        // Crear usuarios
        User user1 = new User();
        user1.setUsername("JohnDoe");
        user1.setPassword("PASSWORD");
        user1.setEmail("test@gmail.com");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("JaneSmith");
        user2.setPassword("PASSWORD");
        user2.setEmail("test2@gmail.com");
        userRepository.save(user2);

        Step step1 = new Step();
        step1.setLeader(Arrays.asList("da un paso atras","", "recuerda que en el tercer tiempo hay un impulso hacia abajo","","","Suelta a tu pareja en la dirección que quieres que salga"));
        step1.setFollower(Arrays.asList("","","en el tercer tiempo impulsate desde abajo", "salta con las rodillas en el pecho","","aterriza con ambos pies en horizontal doblando las rodillas"));
        stepRepository.save(step1);

        // Crear lecciones
        Lesson lesson1 = new Lesson();
        lesson1.setTitle("Swing out");
        lesson1.setUsers(Set.of(user1, user2)); // Asigna usuarios a la lección
        lesson1.setDescription("Swing out con salto");
        lesson1.setSteps(Collections.singletonList(step1));
        lesson1.setTags(Arrays.asList("swing out", "jumping", "partner"));
        lesson1.setUsers(Set.of(user1, user2));
        lesson1.setDanceName(DanceEnum.LINDY_HOP);
        lesson1.setUrlVideo("VIDEO LINK");
        lessonRepository.save(lesson1);


        System.out.println("Base de datos inicializada con datos de prueba");
    }
}