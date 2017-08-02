package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Pierre on 02/08/2017.
 */

@Getter
@Setter
@AllArgsConstructor
public class StopPointDeviations implements Serializable {

    @JsonProperty("StopInfo")
    private StopInfo stopInfo;

    @JsonProperty("Deviation")
    private Deviation deviation;
}
