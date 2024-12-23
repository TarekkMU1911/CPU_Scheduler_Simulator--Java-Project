package Processes;
public class FCAIProcess extends Process {
    private int priority;
    private int quantum;
    private int FCAIScore;

    public FCAIProcess(String name, int arrivalTime, int burstTime, int priority, int quantum) {
        super(name, arrivalTime, burstTime);
        this.priority = priority;
        this.quantum = quantum;
        this.FCAIScore = 0;
        calculateFCAIScore(1.0, 1.0); // Provide default values to calculate the FCAIScore
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getFCAIScore() {
        return FCAIScore;
    }

    public void calculateFCAIScore(double v1, double v2) {
        this.FCAIScore = (10 - priority) + (int) Math.ceil(arrivalTime / v1) + (int) Math.ceil(burstTime / v2);
    }
}
