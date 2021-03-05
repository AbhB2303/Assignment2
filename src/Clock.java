public class Clock {
    private int Clock;

    public void Tick(int tick)
    {

        try {
            Thread.sleep(tick*1000);
            Clock += tick;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getTime()
    {
        return Clock;
    }
}
