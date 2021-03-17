package it.academy.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "T_PERSON")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Person {

    @Id
    @Column(name = "P_ID")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @GeneratedValue(generator = "uuid-generator")
    private String personId;

    @Column(name = "P_NAME", length = 1000)
    private String name;

    @Column(name = "P_SEC_NAME", length = 1000)
    private String secondName;

    @Column(name = "P_BIRTH_DATE")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OrderColumn (name = "C_ORDER")
    @ElementCollection
    private String[] comments;

    @OneToOne
    @JoinColumn(name ="SHOP_USER_ID")
    private ShopUser shopUser;
}

enum Status {
    NEW, UPDATED,DELETED
}
