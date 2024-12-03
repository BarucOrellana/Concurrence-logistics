import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Se encarga de generar los conductores, los camiones y orquestar la asignación de cada uno.
 */
public class Yard implements Runnable {

    private List<Driver> driversList;

    //Manejar cola de tareas
    private BlockingQueue<Truck> truckQueue;

    public Yard(int drivers, int trucks) {
        if (drivers < trucks){
            throw new IllegalArgumentException("Debe haber mas conductores que camiones");
        }

        this.driversList = new ArrayList<>();
        this.truckQueue = new LinkedBlockingQueue<>(trucks);

        for (int i = 0; i < trucks; i++) {
            truckQueue.add(new Truck(i+1));
        }

        for (int i = 0; i < drivers; i++) {
            driversList.add(i , new Driver(i+1, this));
        }
    }

    public BlockingQueue<Truck> getTruckQueue(){
        return this.truckQueue;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(driversList.size());
        for(Driver d : driversList){
            executorService.submit(d);
        }
    }
}
