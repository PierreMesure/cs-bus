package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Created by Pierre on 02/08/2017.
 */

@Getter
@AllArgsConstructor
public class StopInfo implements Serializable {

    @JsonProperty("GroupOfLine")
    private String groupOfLine;

    @JsonProperty("StopAreaName")
    private String stopAreaName;

    @JsonProperty("StopAreaNumber")
    private int stopAreaNumber;

    @JsonProperty("TransportMode")
    private String transportMode;
}
