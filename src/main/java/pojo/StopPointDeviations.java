package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

/**
 * Created by Pierre on 02/08/2017.
 */

@Getter
public class StopPointDeviations implements Serializable {

    @JsonProperty("StopInfo")
    private StopInfo stopInfo;

    @JsonProperty("Deviation")
    private Deviation deviation;
}
