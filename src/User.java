import java.util.LinkedList;
import java.util.Queue;
//time distributed equally to each process
public class User{
    private char userID;
    private Queue<Process> Processes = new LinkedList<>();


    public int getProcessesReadyTime() {
        return Processes.peek().getReadyTime();

    }



    public Queue<Process> getProcesses() {
        return Processes;
    }

    public Process DequeueProcess()
    {
        return Processes.remove();
    }

    public void setProcesses(Queue<Process> processes) {
        Processes = processes;
    }
    public Process getSpecificProcesses(int i) {
        for (Process MyProcess:
             Processes) {
            if(MyProcess.getProcessID() == i){
                return MyProcess;
            }

        }
        return null;
    }


    public char getUserID() {
        return userID;
    }

    public void setUserID(char userID) {
        this.userID = userID;
    }

    public User(char userID) {
        this.userID = userID;
    }

    public void AddProcess(Process NewProcess) //add a new process in the Waiting Queue
    {
        Processes.add(NewProcess);
    }


    @Override
    public String toString() {
        return "User" + userID + ": " +
                " Waiting Queue: " + Processes.toString() + "\n";

    }
}
