package com.buildledger.backend.dto.responce.building;

import com.buildledger.backend.enums.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ResponseBuildingImplementationDTO {

    private long id;
    private String title;
    private String description;
    private double rsp;  // retail selling price
    private int entranceCount;
    private String projectName;

    private  String Type; ;

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

    public double getRsp() {
        return rsp;
    }

    public void setRsp(double rsp) {
        this.rsp = rsp;
    }

    public int getEntranceCount() {
        return entranceCount;
    }

    public void setEntranceCount(int entranceCount) {
        this.entranceCount = entranceCount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public ResponseBuildingImplementationDTO(String title) {
        this.title = title;
    }
}
