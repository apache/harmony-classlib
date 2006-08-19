package java.lang.management;

import javax.management.openmbean.CompositeData;

/**
 * <p>
 * Thread information.
 * </p>
 * 
 * @since 1.5
 */
public class ThreadInfo {

    public static ThreadInfo from(CompositeData cd) {
        return null;
    }

    ThreadInfo() {
        super();
    }

    public long getBlockedCount() {
        return -1;
    }

    public long getBlockedTime() {
        return -1;
    }

    public String getLockName() {
        return null;
    }

    public long getLockOwnerId() {
        return -1;
    }

    public String getLockOwnerName() {
        return null;
    }

    public StackTraceElement[] getStackTrace() {
        return null;
    }

    public long getThreadId() {
        return -1;
    }

    public String getThreadName() {
        return null;
    }

    public Thread.State getThreadState() {
        return null;
    }

    public long getWaitedCount() {
        return -1;
    }

    public long getWaitedTime() {
        return -1;
    }

    public boolean isInNative() {
        return false;
    }

    public boolean isSuspended() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
