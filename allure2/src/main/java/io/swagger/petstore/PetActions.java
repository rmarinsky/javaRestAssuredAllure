package io.swagger.petstore;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.swagger.petstore.entities.MessageResponse;
import io.swagger.petstore.entities.Pet;

import static io.restassured.RestAssured.given;

class PetActions {

    private RequestSpecification requestSpecification;

    PetActions() {
        requestSpecification = new RequestSpecBuilder()
                .addHeader("api_key", "1qa2ws3ed4rfvcxz")
                .setBaseUri("http://petstore.swagger.io")
                .setBasePath("/v2/pet")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL).addFilter(new AllureRestAssured()).build();
    }

    Pet addNewPet(Pet petRequest) {
        return given(requestSpecification)
                .body(petRequest)
                .post().as(Pet.class);
    }

    void deletePet(Pet pet) {
        given(requestSpecification)
                .delete(pet.getId());
    }

    MessageResponse getAbsentPet(Pet pet) {
        return given(requestSpecification)
                .get(pet.getId())
                .then()
                .extract().body().as(MessageResponse.class);
    }

}
