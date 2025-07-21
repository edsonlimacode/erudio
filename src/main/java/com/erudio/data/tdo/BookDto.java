package com.erudio.data.tdo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class BookDto {

    private Long id;
    private String title;

    @JsonProperty("launch_date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date launchDate;
    private BigDecimal price;

    @JsonProperty("author_id")
    private Long authorId;

}
