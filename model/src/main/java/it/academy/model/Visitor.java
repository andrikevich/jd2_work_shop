package it.academy.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="T_VISITOR_COUNT")
@NoArgsConstructor
public class Visitor {
    @Id
    @Column
    private String id = "1";

    @Column(name = "count")
    private int count =0;

    public void setId(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
