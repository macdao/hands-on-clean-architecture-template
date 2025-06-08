package com.example.demo.adapter.webopenapi.adapter;

import com.example.demo.adapter.webopenapi.api.PetApi;
import com.example.demo.adapter.webopenapi.model.ModelApiResponse;
import com.example.demo.adapter.webopenapi.model.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PetApiAdapter implements PetApi {

    @Override
    public Pet addPet(Pet pet) {
        Pet cat = new Pet();
        cat.setName("kitty");
        return cat;
    }

    @Override
    public void deletePet(Long petId, String apiKey) {}

    @Override
    public List<Pet> findPetsByStatus(List<String> status) {
        return List.of();
    }

    @Override
    public List<Pet> findPetsByTags(List<String> tags) {
        return List.of();
    }

    @Override
    public Pet getPetById(Long petId) {
        Pet cat = new Pet();
        cat.setId(petId);
        cat.setName("kitty");
        return cat;
    }

    @Override
    public Pet updatePet(Pet pet) {
        return null;
    }

    @Override
    public void updatePetWithForm(Long petId, String name, String status) {}

    @Override
    public ModelApiResponse uploadFile(Long petId, String additionalMetadata, MultipartFile file) {
        return null;
    }
}
