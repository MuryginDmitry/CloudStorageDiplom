package ru.netology.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class FileNameEditRequest {

    private String filename;

    @JsonCreator
    public FileNameEditRequest(String filename) {
        this.filename = filename;
    }
}