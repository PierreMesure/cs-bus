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
public class Response implements Serializable {
    @JsonProperty("StatusCode")
    private int statusCode;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("ExecutionTime")
    private long executionTime;

    @JsonProperty("ResponseData")
    private Departure responseData;


}
