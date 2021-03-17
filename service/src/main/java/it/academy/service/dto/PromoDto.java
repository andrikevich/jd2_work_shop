package it.academy.service.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PromoDto {

    @NotEmpty
    private String productName;
    @NotEmpty
    private String productDescription;
    @NotEmpty
    private String promoDescription;


}
