package com.goitrestaurant.dao.hibernate;

import com.goitrestaurant.dao.PositionDao;
import com.goitrestaurant.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath:application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HPositionDaoTest {

    private PositionDao positionDao;

    @Autowired
    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateDeletePosition() throws Exception {
        Position position = new Position("testPosition");
        positionDao.create(position);
        List<Position> positions = positionDao.getAll();
        assertEquals(position.getPositionTitle(), positions.get(0).getPositionTitle());
        assertTrue(positions.size() == 1);

        positionDao.delete(positions.get(0).getId());
        positions = positionDao.getAll();
        assertTrue(positions.size() == 0);
    }

}