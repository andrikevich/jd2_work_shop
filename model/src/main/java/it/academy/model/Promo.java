package it.academy.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="T_PROMO")
@Data
@NoArgsConstructor
public class Promo {

    @Id
    @GenericGenerator(name="gen-uuid", strategy = "uuid")
    @GeneratedValue(generator = "gen-uuid")
    @Column(name = "PROMO_ID")
    private String promoId;

    @Column(name = "PR_DESC")
    private String description;

    @ManyToMany
    @JoinTable(name="T_PRODUCT_PROMO",
                    joinColumns = @JoinColumn(name="PROMO_ID"),
                    inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    private List<Product> products;

}
