package com.example.cuidandopatas.config;

import com.example.cuidandopatas.entity.Medicine;
import com.example.cuidandopatas.entity.Pet;
import com.example.cuidandopatas.entity.User;
import com.example.cuidandopatas.entity.Visit;
import com.example.cuidandopatas.repository.MedicineRepository;
import com.example.cuidandopatas.repository.PetRepository;
import com.example.cuidandopatas.repository.UserRepository;
import com.example.cuidandopatas.repository.VisitRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class DBInitializer {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final VisitRepository visitRepository;
    private final MedicineRepository medicineRepository;

    @Autowired
    public DBInitializer(UserRepository userRepository, PetRepository petRepository, VisitRepository visitRepository, MedicineRepository medicineRepository) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
        this.visitRepository = visitRepository;
        this.medicineRepository = medicineRepository;
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
        User johnDoe = userRepository.save(user1);
        User user2 = new User();
        user2.setUsername("JaneSmith");
        user2.setPassword("PASSWORD");
        user2.setEmail("test2@gmail.com");
        userRepository.save(user2);

        // Crear pets
        Pet pet1 = new Pet();
        pet1.setName("Pet 1");
        pet1.setBreed("Breed 1");
        pet1.setType("Type 1");
        pet1.setUser(user1);
        pet1.setChip("12345");
        pet1.setDateBirth(LocalDate.now().minusYears(3));
        pet1.setImageFilename("c009d371-d19a-4ca8-9fd6-f3f6c1901568.jpg");
        petRepository.save(pet1);

        // Crear Visits
        Visit visit1 = new Visit();
        visit1.setPet(pet1);
        visit1.setDescription("Visit 1");
        visit1.setDateVisit(LocalDate.now().minusDays(1));
        visit1.setFileURL("https://www.google.com/");
        visitRepository.save(visit1);

        //Medicinas
        Medicine med1 = new Medicine();
        med1.setName("Medicina 1");
        med1.setDose("50g");
        med1.setFrequency("12 horas");
        med1.setDetail("asdasdsa");
        med1.setActive(true);
        med1.setStartDate(LocalDate.now().minusDays(1));
        med1.setEndDate(null);
        med1.setPet(pet1);
        medicineRepository.save(med1);

        System.out.println("User created: " + johnDoe.getUsername() + " with id: " + johnDoe.getId());
        System.out.println("Pet created: " + pet1.getName() + " with id: " + pet1.getId() + " fecha muerte:"+ pet1.getDisableDate());

        System.out.println("Base de datos inicializada con datos de prueba");
    }
}