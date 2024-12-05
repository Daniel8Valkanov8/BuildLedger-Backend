package com.buildledger.backend.dto.responce.building;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBuildingDTO {

    private long id;

    private String title;

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResponseBuildingDTO(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
