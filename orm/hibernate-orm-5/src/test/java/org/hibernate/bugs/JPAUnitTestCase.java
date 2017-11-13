package org.hibernate.bugs;

import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.bugs.hhh12087.RootEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM,
 * using the Java Persistence API.
 */
public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;
    private EntityManagerFactory entityManagerFactoryFixed;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
        entityManagerFactoryFixed = Persistence.createEntityManagerFactory("templatePUFixed");
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
        entityManagerFactoryFixed.close();
    }

    // This test fails. On standard dialect
    @Test
    public void hhh12087Test() throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        testRunner(em);
    }
    
    // This test works. On fixed dialect
    @Test
    public void hhh12087TestFixed() throws Exception {
        EntityManager em = entityManagerFactoryFixed.createEntityManager();
        testRunner(em);
    }
    
    private void testRunner(EntityManager em) {
        em.getTransaction().begin();
        
        // Here we retrive all rows from view sorted by status desc
        List<RootEntity> rootEntitiesAll = getAllRows(em);
        
        // Retriving first five rows from view sorted by status desc
        List<RootEntity> rootEntitiesFirst = getLimitedRows(em, 0, 5);
        // Retriving second five rows from view sorted by status desc
        List<RootEntity> rootEntitiesSecond = getLimitedRows(em, 5, 10);
        
        // I'm printing the output so you can see one test failed for disordered result and my fixed dialect return exactly correct result
        System.out.append("--------------------\n");
        System.out.append(rootEntitiesAll.get(0).getId() + "\t should be equal to \t" + rootEntitiesFirst.get(0).getId() + "\n");
        System.out.append(rootEntitiesAll.get(1).getId() + "\t should be equal to \t" + rootEntitiesFirst.get(1).getId() + "\n");
        System.out.append(rootEntitiesAll.get(2).getId() + "\t should be equal to \t" + rootEntitiesFirst.get(2).getId() + "\n");
        System.out.append(rootEntitiesAll.get(3).getId() + "\t should be equal to \t" + rootEntitiesFirst.get(3).getId() + "\n");
        System.out.append(rootEntitiesAll.get(4).getId() + "\t should be equal to \t" + rootEntitiesFirst.get(4).getId() + "\n");
        System.out.append(rootEntitiesAll.get(5).getId() + "\t should be equal to \t" + rootEntitiesSecond.get(0).getId() + "\n");
        System.out.append(rootEntitiesAll.get(6).getId() + "\t should be equal to \t" + rootEntitiesSecond.get(1).getId() + "\n");
        System.out.append(rootEntitiesAll.get(7).getId() + "\t should be equal to \t" + rootEntitiesSecond.get(2).getId() + "\n");
        System.out.append(rootEntitiesAll.get(8).getId() + "\t should be equal to \t" + rootEntitiesSecond.get(3).getId() + "\n");
        System.out.append(rootEntitiesAll.get(9).getId() + "\t should be equal to \t" + rootEntitiesSecond.get(4).getId() + "\n");
        System.out.append("--------------------\n");
        assert rootEntitiesAll.get(0).getId() == rootEntitiesFirst.get(0).getId();
        assert rootEntitiesAll.get(1).getId() == rootEntitiesFirst.get(1).getId();
        assert rootEntitiesAll.get(2).getId() == rootEntitiesFirst.get(2).getId();
        assert rootEntitiesAll.get(3).getId() == rootEntitiesFirst.get(3).getId();
        assert rootEntitiesAll.get(4).getId() == rootEntitiesFirst.get(4).getId();
        
        assert rootEntitiesAll.get(5).getId() == rootEntitiesSecond.get(0).getId();
        assert rootEntitiesAll.get(6).getId() == rootEntitiesSecond.get(1).getId();
        assert rootEntitiesAll.get(7).getId() == rootEntitiesSecond.get(2).getId();
        assert rootEntitiesAll.get(8).getId() == rootEntitiesSecond.get(3).getId();
        assert rootEntitiesAll.get(9).getId() == rootEntitiesSecond.get(4).getId();
                
        em.getTransaction().commit();
        em.close();
    }
    
    private List<RootEntity> getAllRows(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<RootEntity> cq = cb.createQuery(RootEntity.class);
        Root<RootEntity> c = cq.from(RootEntity.class);
        return em.createQuery(cq.select(c).orderBy(cb.desc(c.get("status")))).getResultList();
    }
    
    private List<RootEntity> getLimitedRows(EntityManager em, int start, int end) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<RootEntity> cq = cb.createQuery(RootEntity.class);
        Root<RootEntity> c = cq.from(RootEntity.class);
        CriteriaQuery<RootEntity> select = cq.select(c).orderBy(cb.desc(c.get("status")));
        TypedQuery<RootEntity> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult(start);
        typedQuery.setMaxResults(end);
        return typedQuery.getResultList();
    }
}
