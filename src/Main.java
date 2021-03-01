/*
Abhinav Batra; 40086809
Mihai Olaru; 40111734

Programming Assignment 2
Simulating Fair-shar Process Scheduling
*/

import java.util.*;
import java.time.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scheduler scheduler = new Scheduler();
        Thread ScheduleThread = new Thread(scheduler);
        ScheduleThread.start(); //starts scheduling thread
        try {
            ScheduleThread.join();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
