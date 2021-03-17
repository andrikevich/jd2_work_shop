package it.academy.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "T_SHOP_USER")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ShopUser {

    @Id
    @Column(name = "SHOP_USER_ID")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @GeneratedValue(generator = "uuid-generator")
    private String shopUserId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private  String password;
}
