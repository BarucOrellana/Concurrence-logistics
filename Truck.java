import java.util.Random;
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

    public int getTime(){
        return new Random().nextInt(5000, 15000);
    }
    public void unlock() {
        if (!isHeld())
            return;
        lock.unlock();
    }
}
