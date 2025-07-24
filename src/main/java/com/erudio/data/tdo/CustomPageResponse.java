package com.erudio.data.tdo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class CustomPageResponse<T> {
    private List<T> content;
    @JsonProperty("current_page")
    private int page;
    private int size;
    @JsonProperty("total_elements")
    private long totalElements;
    @JsonProperty("total_pages")
    private int totalPages;
    private boolean first;
    private boolean last;

    public CustomPageResponse(Page<T> pageData) {
        this.content = pageData.getContent();
        this.page = pageData.getNumber();
        this.size = pageData.getSize();
        this.totalElements = pageData.getTotalElements();
        this.totalPages = pageData.getTotalPages();
        this.first = pageData.isFirst();
        this.last = pageData.isLast();
    }
}
