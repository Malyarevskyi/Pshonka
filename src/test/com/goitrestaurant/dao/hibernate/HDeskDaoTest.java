package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.DeskDao;
import com.goitrestaurant.model.Desk;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath:application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HDeskDaoTest {

    private DeskDao deskDao;

    @Autowired
    public void setDeskDao(DeskDao deskDao) {
        this.deskDao = deskDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateDeleteDesk() throws Exception {
        String title = "testDesk";
        deskDao.create(new Desk(title));

        Desk desk = deskDao.getAll().get(0);
        assertEquals(desk.getDeskTitle(), title);

        deskDao.delete(desk.getId());
        assertTrue(deskDao.getAll().size() == 0);
        
    }
}