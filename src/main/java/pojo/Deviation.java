package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Created by Pierre on 01/08/2017.
 */

@Getter
@AllArgsConstructor
public class Deviation implements Serializable {

    @JsonProperty("Consequence")
    private String consequence;

    @JsonProperty("ImportanceLevel")
    private int importanceLevel;

    @JsonProperty("Text")
    private String text;
}
