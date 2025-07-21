package com.erudio.controller.swagger;

import com.erudio.data.tdo.BookDto;
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

@Tag(name = "Book controller", description = "Endpoints para gerenciar uma livro")
public interface BookControllerDoc {
    @Operation(summary = "Cadastra uma livro",
            description = "Cadastra uma livro na base de dados",
            responses = {@ApiResponse(description = "Created", responseCode = "201", content = @Content())})
    ResponseEntity<?> create(@RequestBody BookDto bookDto);

    @Operation(summary = "Retorna uma livro pelo ID",
            description = "Retorna um objeto BookDto",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = BookDto.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content())})
    ResponseEntity<BookDto> findById(@PathVariable Long id);

    @Operation(summary = "Retorna todas as livros",
            description = "Retorna um array de objetos BookDto",
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = BookDto.class))))})
    ResponseEntity<List<BookDto>> findAll();

    @Operation(summary = "Atualiza uma livro pelo ID",
            description = "Atualiza uma livro pelo seu ID, e logo em seguida retorna",
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BookDto.class))),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content())})
    ResponseEntity<BookDto> update(@RequestBody BookDto bookDto);

    @Operation(summary = "Remove uma livro pelo ID",
            description = "Remove uma livro da base de dados", responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content()),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content())})
    ResponseEntity<?> remove(@PathVariable Long id);
}
