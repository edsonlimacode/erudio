package com.erudio.data.tdo;

import com.erudio.model.Book;
import com.erudio.serializer.GenreSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({"id", "age", "first_name", "last_name", "genre", "created_at"})
@Schema(hidden = true)
//@JsonFilter("PersonFilterProperties")
public class PersonDto extends RepresentationModel<PersonDto> {

    private Long id;

    //altera o nome da propriedade
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    //@JsonIgnore
    private int age;

    //não retorna a propriedade se ela for nula
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String propertyNUll;

    @JsonIgnore
    private String propertyToIgnore = "ola";

    //private String propertyPassword = "dado sensivel";

    //Serializa o objeto de forma condicional de acordo com o valor
    @JsonSerialize(using = GenreSerializer.class)
    private String genre;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonProperty("created_at")
    private Date createdAt;

    private List<Book> books;

    private boolean status;
}
