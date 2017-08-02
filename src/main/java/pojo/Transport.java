package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Pierre on 01/08/2017.
 */

@Getter
@Setter
@AllArgsConstructor
public class Transport implements Serializable {

    @JsonProperty("TransportMode")
    private String transportMode;

    @JsonProperty("LineNumber")
    private String lineNumber;

    @JsonProperty("Destination")
    private String destination;

    @JsonProperty("JourneyDirection")
    private int journeyDirection;

    @JsonProperty("GroupOfLine")
    private String groupOfLine;

    @JsonProperty("StopAreaName")
    private String stopAreaName;

    @JsonProperty("StopAreaNumber")
    private int stopAreaNumber;

    @JsonProperty("StopPointNumber")
    private int stopPointNumber;

    @JsonProperty("StopPointDesignation")
    private String stopPointDesignation;

    @JsonProperty("TimeTabledDateTime")
    private Date timeTabledDateTime;

    @JsonProperty("ExpectedDateTime")
    private Date expectedDateTime;

    @JsonProperty("DisplayTime")
    private String displayTime;

    @JsonProperty("JourneyNumber")
    private int journeyNumber;

    @JsonProperty("Deviations")
    private List<Deviation> deviations;

}
