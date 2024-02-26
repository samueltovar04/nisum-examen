package com.nisum.examen.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequest {
    private Long number;
    @JsonProperty("city_code")
    private Long cityCode;
    @JsonProperty("country_code")
    private Long countryCode;
}
