public class Process implements Runnable{
    //instance variables
    //always positive values
    int processID;
    int readyTime; //when the process is ready
    int serviceTime; //total CPU usage required by each process

    public int getProcessID() {
        return processID;
    }

    public void setProcessID(int processID) {
        this.processID = processID;
    }

    public int getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(int readyTime) {
        this.readyTime = readyTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "Process " + processID +" {" +
                "readyTime = " + readyTime +
                ", serviceTime =" + serviceTime +
                '}';
    }
    synchronized public void run() {
        long start = System.currentTimeMillis();
       // System.out.println("Process: "+ processID + " is running");

    }
}
