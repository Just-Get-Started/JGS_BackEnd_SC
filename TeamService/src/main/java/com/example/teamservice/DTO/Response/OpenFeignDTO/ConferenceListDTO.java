package com.example.teamservice.DTO.Response.OpenFeignDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConferenceListDTO {
    private List<ConferenceDTO> conferenceDTOList;
}
