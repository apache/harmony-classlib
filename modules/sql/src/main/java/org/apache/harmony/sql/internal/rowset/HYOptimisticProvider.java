package org.apache.harmony.sql.internal.rowset;

import javax.sql.RowSetReader;
import javax.sql.RowSetWriter;
import javax.sql.rowset.spi.SyncProvider;
import javax.sql.rowset.spi.SyncProviderException;

import org.apache.harmony.luni.util.NotImplementedException;

public class HYOptimisticProvider extends SyncProvider {

    private final static String providerID = "Apache Harmony";

    private final static int providerGrade = SyncProvider.DATASOURCE_NO_LOCK;

    @Override
    public int getDataSourceLock() throws SyncProviderException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getProviderGrade() {
        return providerGrade;
    }

    @Override
    public String getProviderID() {
        return providerID;
    }

    @Override
    public RowSetReader getRowSetReader() {
        throw new NotImplementedException();
    }

    @Override
    public RowSetWriter getRowSetWriter() {
        return new CachedRowSetWriter();
    }

    @Override
    public String getVendor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDataSourceLock(int dataSourceLock)
            throws SyncProviderException {
        // TODO Auto-generated method stub

    }

    @Override
    public int supportsUpdatableView() {
        // TODO Auto-generated method stub
        return 0;
    }

}
