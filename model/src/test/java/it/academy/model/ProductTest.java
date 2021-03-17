package it.academy.model;


import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductTest extends  BaseTest {

    @Test
    public void createProduct(){
        //GIVEN-WHEN-THEN

        //Given
        Product product = new Product();
        product.setProductName("Apple Iphone");
        product.setDescription("iPhone 12 512Gb");


        ProductPrice productPrice1 = new ProductPrice();
        productPrice1.setProduct(product);
        productPrice1.setPriceValue(BigDecimal.valueOf(5999.99));
        productPrice1.setCurrency(Currency.BYN);
        ProductPrice productPrice2 = new ProductPrice();
        productPrice2.setProduct(product);
        productPrice2.setPriceValue(BigDecimal.valueOf(2000.99));
        productPrice2.setCurrency(Currency.EUR);

        List<ProductPrice> prices = new ArrayList<>();
        prices.add(productPrice1);
        prices.add(productPrice2);
       // product.setProductPrice(prices);

        //When
        Session session = factory.openSession();
        Transaction tx = null;
        Serializable id = null;
        try {
            tx = session.beginTransaction();
            product.setProductPrices(prices);
            session.save(productPrice1);
            session.save(productPrice2);
            id = session.save(product);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        //then
        Assert.assertNotNull(id);
    }

    @Test
    public void read(){
        cleanInsert("Product.xml");
        Session session = factory.openSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product", Product.class).list();

        Assert.assertNotNull(products);
        Assert.assertEquals(3, products.size());
        List<ProductPrice> prices = products.stream().filter(n -> "4028b88177448dad0177448db34e0002".equals(n.getProductId()))
                .map(product -> product.getProductPrices()).findFirst().orElse(null);
        Assert.assertNotNull(prices);
        Assert.assertEquals(1,prices.size());
        session.getTransaction().commit();
       deleteDataset();
        session.close();
    }

    @Test
    public void delete(){
        cleanInsert("Product.xml");
        Session session = factory.openSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product", Product.class).list();
        List<ProductPrice> prices = session.createQuery("from ProductPrice", ProductPrice.class).list();

        //т.к. не указано каскадирование, надо удалить сразу связанную таблицу(ProductPrice) via FK
        prices.stream().forEach(productPrice -> session.delete(productPrice));
       products.stream().forEach(product -> session.delete(product));

        session.getTransaction().commit();

        Assert.assertEquals(
                0,
                session.createQuery("from Product").list().size()
        );

        deleteDataset();
        session.close();
    }


    //usage Criteria APi
//    @Test
//    public void queryProductPrice(){
//        cleanInsert("Product.xml");
//
//        Session session = factory.openSession();
//        session.beginTransaction();
//        List priceValue = session.createCriteria(ProductPrice.class)
//                                .add(Restrictions.eq("priceValue", BigDecimal.valueOf(5990.99)))
//                                .list();
//
//
//        Session session2 = factory.openSession();
//        CriteriaBuilder cb = session2.getCriteriaBuilder();
//        CriteriaQuery<ProductPrice> query = cb.createQuery(ProductPrice.class);
//        Root<ProductPrice> root = query.from(ProductPrice.class);
//        query.where(cb.equal(root.get("priceValue"), BigDecimal.valueOf(5990.99)));
//        List<ProductPrice> list2 = session2.createQuery(query).list();
//
//
//        Assert.assertEquals(1,priceValue.size());
//        Assert.assertEquals(1,list2.size());
//    }
}
