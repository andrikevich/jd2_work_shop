package it.academy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_PRODUCT")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Product {

    @Id
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @GeneratedValue(generator = "uuid-generator")
    @Column(name = "PRODUCT_ID")
    private  String productId;

    @Column(name = "P_PRODUCT_NAME")
    private String productName;


    @Column(name = "P_PRODUCT_DESCRIPTION")
    private String description;

    @OneToMany (mappedBy = "product")
    private List<ProductPrice> productPrices;

    // владелец мэппинга в поле products таблицы Promo
    @ManyToMany(mappedBy = "products")
    private List<Promo> promos;

}
