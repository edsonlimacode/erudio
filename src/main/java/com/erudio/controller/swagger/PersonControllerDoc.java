package com.erudio.controller.swagger;

import com.erudio.data.tdo.PersonDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Person controller", description = "Endpoints para gerenciar uma pessoa")
public interface PersonControllerDoc {
    @Operation(summary = "Cadastra uma pessoa",
            description = "Cadastra uma pessoa na base de dados",
            responses = {@ApiResponse(description = "Created", responseCode = "201", content = @Content())})
    ResponseEntity<?> create(@RequestBody PersonDto personDto);

    @Operation(summary = "Retorna uma pessoa pelo ID",
            description = "Retorna um objeto PersonDto",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PersonDto.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content())})
    ResponseEntity<PersonDto> findById(@PathVariable Long id);

    @Operation(summary = "Retorna todas as pessoas",
            description = "Retorna um array de objetos PersonDto",
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PersonDto.class))))})
    ResponseEntity<List<PersonDto>> findAll();

    @Operation(summary = "Atualiza uma pessoa pelo ID",
            description = "Atualiza uma pessoa pelo seu ID, e logo em seguida retorna",
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonDto.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content())})
    ResponseEntity<PersonDto> update(@RequestBody PersonDto personDto);

    @Operation(summary = "Remove uma pessoa pelo ID",
            description = "Remove uma pessoa da base de dados", responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content()),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content())})
    ResponseEntity<?> remove(@PathVariable Long id);
}
