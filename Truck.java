import java.util.concurrent.locks.ReentrantLock;

public class Truck {

    private final ReentrantLock lock;
    public int idTruck;

    public Truck(int idTruck) {
        this.lock = new ReentrantLock();
        this.idTruck = idTruck;
    }

    public void drive() {
        lock.lock();
    }

    public int countLocks(){
        return lock.getHoldCount();
    }

    public boolean isHeld() {
        return lock.isHeldByCurrentThread();
    }

    public void unlock() {
        if (!isHeld())
            return;
        lock.unlock();
    }
}
