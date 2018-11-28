import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.arshdeep.algo.MainActivity;
import com.arshdeep.algo.R;

public class NotifyTest extends AppCompatActivity{

    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Eating eating = new Eating();
        Eating2 eating2 = new Eating2();
        Cooking cooking = new Cooking();
        cooking.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eating2.start();
        eating.start();

    }

    class Eating extends Thread {

        @Override
        public void run() {
            eating1();
        }
    }

    class Eating2 extends Thread {

        @Override
        public void run() {
            eating2();
        }
    }

    class Cooking extends Thread {

        @Override
        public void run() {
            Log.e(TAG, "started cooking");
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e(TAG, "cooking completed");
            startCooking();
        }
    }

    public synchronized void eating1() {
        Log.e(TAG, "request for eating1");
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "started eating1 after completion of cooking");
    }

    public synchronized void eating2() {
        Log.e(TAG, "request for eating2");
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "started eating2 after completion of cooking");
    }

    public synchronized void startCooking() {
        notifyAll();
    }

}
