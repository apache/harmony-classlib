package javax.sql.rowset.spi;

import javax.sql.RowSetReader;
import javax.sql.RowSetWriter;

import org.apache.harmony.sql.internal.nls.Messages;

class ProviderImpl extends SyncProvider {

    private String className;

    private String vendor;

    private String version;

    private SyncProvider impl;

    private String errMsg;

    public ProviderImpl() {
        // default constructor
    }

    public ProviderImpl(String name) {
        className = name;

        try {
            Class<?> implClass = Class.forName(className, true, Thread
                    .currentThread().getContextClassLoader());
            impl = (SyncProvider) implClass.newInstance();
        } catch (ClassNotFoundException e) {
            errMsg = Messages.getString("sql.40", className); //$NON-NLS-1$
        } catch (Exception e) {
            // ignore
        }
    }

    public ProviderImpl(String name, String vendor, String version) {
        this(name);

        this.vendor = vendor;
        this.version = version;
    }

    public SyncProvider getImpl() throws SyncFactoryException {
        if (null == impl) {
            throw new SyncFactoryException(Messages.getString(
                    "sql.40", className)); //$NON-NLS-1$
        }
        return impl;
    }

    @Override
    public int getDataSourceLock() throws SyncProviderException {
        checkClassNameValid();
        return impl.getDataSourceLock();
    }

    @Override
    public int getProviderGrade() {
        return impl == null ? 0 : impl.getProviderGrade();
    }

    @Override
    public String getProviderID() {
        return impl == null ? className : impl.getProviderID();
    }

    @Override
    public RowSetReader getRowSetReader() {
        return impl == null ? null : impl.getRowSetReader();
    }

    @Override
    public RowSetWriter getRowSetWriter() {
        return impl == null ? null : impl.getRowSetWriter();
    }

    @Override
    public String getVendor() {
        return impl == null ? vendor : impl.getVendor();
    }

    @Override
    public String getVersion() {
        return impl == null ? version : impl.getVersion();
    }

    @Override
    public void setDataSourceLock(int dataSourceLock)
            throws SyncProviderException {
        checkClassNameValid();
        impl.setDataSourceLock(dataSourceLock);
    }

    @Override
    public int supportsUpdatableView() {
        return impl == null ? 0 : impl.supportsUpdatableView();
    }

    private void checkClassNameValid() throws SyncProviderException {
        if (null == impl) {
            throw new SyncProviderException(errMsg);
        }
    }

}
