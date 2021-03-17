package it.academy.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class ProductPrice {

    @Id
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @GeneratedValue(generator = "uuid-generator")
    private String priceId;

    private BigDecimal priceValue;

    private Currency currency;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;
}

enum Currency {
    BYN, EUR, USD
}