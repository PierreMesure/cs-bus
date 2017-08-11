package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Pierre on 02/08/2017.
 */

@Getter
public class Departure implements Serializable {

    @JsonProperty("LatestUpdate")
    private Date latestUpdate;

    @JsonProperty("DataAge")
    private int dataAge;

    @JsonProperty("Buses")
    private List<Transport> buses;

    @JsonProperty("Metros")
    private List<Transport> metros;

    @JsonProperty("Trains")
    private List<Transport> trains;

    @JsonProperty("Trams")
    private List<Transport> trams;

    @JsonProperty("Ships")
    private List<Transport> ships;

    @JsonProperty("StopPointDeviations")
    private List<StopPointDeviations> stopPointDeviations;
}
