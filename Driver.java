import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Driver implements Runnable {
    public int idDriver;
    public Yard yard;
    public Truck truck;
    public int time;
    public Driver(int idDriver, Yard yard) {
        this.idDriver = idDriver;
        this.yard = yard;
    }

    Semaphore semaphore = new Semaphore(2);

    public void drive() {
        try {
            BlockingQueue<Truck> queue = yard.getTruckQueue();
            semaphore.acquire();
            truck = queue.take();
            truck.drive();
            if (truck.isHeld()) {
                time = truck.getTime();
                System.out.println("El conductor " + idDriver + " esta manejando la unidad " + truck.idTruck);
                Thread.sleep(time);
                queue.put(truck);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("El conductor " + idDriver + " ha dejado de conducir la unidad "+ truck.idTruck);
            System.out.println("El conductor " + idDriver + " condujo por " + time/1000 + "s");
            truck.unlock();
            rest();
            semaphore.release();
        }
    }

    public void rest(){
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean compare(int countsTruck1, int countsTruck2){
        return countsTruck1 > countsTruck2;
    }

    @Override
    public void run() {
        while (true){
            drive();
        }
    }
}
