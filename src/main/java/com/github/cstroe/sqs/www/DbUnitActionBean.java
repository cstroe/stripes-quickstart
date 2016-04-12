package com.github.cstroe.sqs.www;

import com.github.cstroe.sqs.hibernate.HibernateSessionUtil;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;

import java.io.InputStream;
import java.sql.SQLException;

// TODO move this to testing code
@UrlBinding("/app/resetdb")
public class DbUnitActionBean extends BaseActionBean {
    @DefaultHandler
    public Resolution reset() {
        Session currentSession = HibernateSessionUtil.getCurrentSession();
        currentSession.doWork(connection -> {
            try {
                // http://stackoverflow.com/questions/3526556/session-connection-deprecated-on-hibernate
                IDatabaseConnection dbConnection = new DatabaseConnection(connection);

                // http://stackoverflow.com/questions/2653322/getresourceasstream-not-loading-resource-in-webapp
                InputStream seed = Thread.currentThread().getContextClassLoader().getResourceAsStream("test_db.xml");
                IDataSet seedDataset = new FlatXmlDataSetBuilder().setColumnSensing(true).build(seed);

                DatabaseOperation.CLEAN_INSERT.execute(dbConnection, seedDataset);
            } catch (Exception ex) {
                throw new SQLException(ex);
            }
        });

        return new RedirectResolution(ViewActionBean.class);
    }
}
