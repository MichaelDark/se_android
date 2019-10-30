package ua.temnokhud.lab1.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    private long id;
    private long importance;
    private Date createdDateTime;
    private String imagePath;
    private String title;
    private String description;

}
