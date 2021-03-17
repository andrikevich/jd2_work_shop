package it.academy.dao;

import it.academy.model.Visitor;

public interface VisitorDao {


    int saveVisitor(Visitor visitor);

    int getMaxVisitor();

    void deleteAll();
    Visitor getVisitor();

}
