package com.example.cuidandopatas.application;

import com.example.cuidandopatas.domain.entity.Pet;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface LessonServiceAdapter {

    List<Pet> findAllByUserId(UUID userId);

}
