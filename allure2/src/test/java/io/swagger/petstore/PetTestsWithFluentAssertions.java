package io.swagger.petstore;

import io.swagger.petstore.asserts.MessageResponseAssert;
import io.swagger.petstore.asserts.PetAssert;
import io.swagger.petstore.entities.MessageResponse;
import io.swagger.petstore.entities.Pet;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class PetTestsWithFluentAssertions {

    @Test
    public void addNewPetToStoreTest() {
        Pet testPet = new Pet(null,
                "Pet_" + RandomStringUtils.randomAlphabetic(8), //Pet name
                RandomStringUtils.randomNumeric(8), //Pet ID
                null, null,
                "available"); //Pet status

        Pet petResponse = new PetActions().addNewPet(testPet);

        PetAssert.assertThat(petResponse).isEqualTo(testPet);
    }

    @Test
    public void deletePetFromStoreTest() {
        Pet testPet = new Pet(null,
                "Pet_" + RandomStringUtils.randomAlphabetic(8), //Pet name
                RandomStringUtils.randomNumeric(8), //Pet ID
                null, null,
                "available"); //Pet status

        PetActions petAction = new PetActions();

        petAction.addNewPet(testPet);
        petAction.deletePet(testPet);

        MessageResponse messageResponse = petAction.getAbsentPet(testPet);

        MessageResponseAssert.assertThat(messageResponse).hasMessage("Pet not found");
    }

}
