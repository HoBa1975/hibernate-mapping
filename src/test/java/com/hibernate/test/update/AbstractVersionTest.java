package com.hibernate.test.update;


import com.hibernate.test.update.pojo.inheritance.FileVersion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractVersionTest {
    private Session session;

    private Transaction transaction;

    @Before
    public void setUp() throws IOException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void tearDown() {
        if(transaction.isActive()) {
            transaction.rollback();
        }
        session.close();
    }

    @Test
    public void testWithDynamicUpdate() {

        // create version
        FileVersion version = new FileVersion();
        version.setId("1");
        version.setFileSize(100L);
        version.setCheckSumMD5("ABCDEF");
        version.setVariantName("A");
        session.persist(version);
        session.flush();

        // update version
        session.evict(version);
        List<FileVersion> result = session.createQuery("from FileVersion", FileVersion.class)
                .getResultList();
        assertThat(result).hasSize(1);

        version = result.get(0);
        version.setCheckSumMD5("XXXXXXXX");
        version.setVariantName("B");
        version.setFileSize(200L);
        session.persist(version);
        session.flush();

        session.evict(version);
        result = session.createQuery("from FileVersion", FileVersion.class)
                .getResultList();
        assertThat(result).hasSize(1);

        version = result.get(0);
        assertThat(version.getCheckSumMD5()).isEqualTo("XXXXXXXX");
        assertThat(version.getVariantName()).isEqualTo("B");
        assertThat(version.getFileSize()).isEqualTo(200L);
    }
}
