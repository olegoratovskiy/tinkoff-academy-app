package com.app.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageDto implements Serializable {

    private String name;
    private Long size;
    private String uid;
}
