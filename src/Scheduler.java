//handles what to switch and when to switch
//handles processes in cyclical manner
//equal quantum for each user
//first scheduler cycle at 1
//no priority in users or processes
import java.util.*;
import java.io.*;
import java.time.*;

public class Scheduler implements Runnable{
    private long start;
    private int quantum;
    Queue<User> UserQueue = new LinkedList<>();



    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public void InputReader(){
        System.out.println("Reading from file");
        try {
            File file = new File("Input.txt");
            Scanner scan = new Scanner(file);
            setQuantum(scan.nextInt());
            //System.out.println("Quantum: " + quantum);
            while(scan.hasNextLine()){
                char temp = (scan.next()).charAt(0);
                if((65 <= temp && temp<= 90) || (97 <= temp && temp<= 122)) //if a letter
                {
                    User NewUser = new User(temp); //new user with letter ID
                    UserQueue.add(NewUser); //add new user to the queue
                    temp = (scan.next()).charAt(0); //number of processes
                   //System.out.println(NewUser.toString()); //print new user to console
                    for (int i = 0; i < temp-48; i++) { //loop and find ready and service time for each process
                       Process NewProcess = new Process(); //create new process
                       NewProcess.setReadyTime(scan.nextInt());
                       NewProcess.setServiceTime(scan.nextInt());
                       NewProcess.setProcessID(i+1);
                       NewUser.AddProcess(NewProcess);
                    }
                }




            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
      //  System.out.println(UserQueue);
    }

    @Override
    synchronized public void run() {
        System.out.println("Beginning scheduler");
        InputReader();


        start = System.currentTimeMillis();
        while(true) //run scheduler until all processes are complete
        {
            //System.out.println("Entering While loop");
            if(UserQueue.isEmpty())
            {
                System.out.println("All processes complete!");
                break;
            }
            else {
                for (User User : UserQueue) { //checking each user in UserQueue
                    if (User.getProcesses().isEmpty()) { //check if the user is done all of its processes
                        System.out.println("All processes complete for this user");
                        UserQueue.remove(User);
                    } else {//if not finished all processes for the current user
                        int QuantumPerUser = quantum/UserQueue.size(); //setting quantum per user
                        int QuantumPerProcess = QuantumPerUser / User.getProcesses().size(); //set quantum for each process that user owns
                        for (Process UserProcess : User.getProcesses())  //iterate through each process the user owns
                        {



                            if ((UserProcess.getReadyTime() == (System.currentTimeMillis() - start) / 1000) && (System.currentTimeMillis() - start) % 1000 == 0) {
                                    //begin process for given quantum

                                    if(UserProcess.getServiceTime() == 0) //if process is complete, dequeue process and print it finishing
                                    {
                                        System.out.println("Time " + (System.currentTimeMillis() - start)/1000 + ", User " + User.getUserID() + ", Process " + UserProcess.getProcessID() + ", Finished");
                                        User.DequeueProcess(); //dequeue complete process
                                    }else
                                    {
                                        Thread CurrentProcess = new Thread(UserProcess);

                                        CurrentProcess.start(); //start and run for given quantum



                                        try {
                                            CurrentProcess.join(QuantumPerProcess);//or that it reached end of exec
                                            if((UserProcess.getServiceTime()-QuantumPerProcess) >= 0)
                                            {
                                                UserProcess.setServiceTime(UserProcess.getServiceTime()-QuantumPerProcess);
                                            }
                                            else
                                            {
                                                UserProcess.setServiceTime(0);
                                            }


                                            //System.out.println("Time " + (System.currentTimeMillis() - start)/1000 + ", User " + User.getUserID() + ", Process " + UserProcess.getProcessID() + ", Paused");
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }else if((System.currentTimeMillis() - start) % 1000 == 0)
                            {

                            }
                        }//checks each process in each user to see if it is ready
                    }
                }
            }




        }
        //System.out.println("Just left the loop");




    }
}
