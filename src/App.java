import Processes.FCAIProcess;
import Processes.PriorityProcess;
import Processes.Process;
import Schedulers.*;
import contextSwitch.ContextSwitch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        List<Process> processList = new ArrayList<>();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Scheduler scheduler = new Scheduler();
        
        boolean flag = true;

        while (flag)
        {
            System.out.println("\nChoose Scheduling Algorithm:");
            System.out.println("1. Priority Scheduling");
            System.out.println("2. Shortest-Job First (SJF) Scheduling");
            System.out.println("3. Shortest-Remaining Time First (SRTF) Scheduling");
            System.out.println("4. FCAI Scheduling");
            System.out.println("5. Exit");
            int type = Integer.parseInt(reader.readLine());

            if (type == 5) {
                System.out.println("Exiting...");
                break;
            }
            
            System.out.println("Choose number of processes: ");
            int numProcesses = Integer.parseInt(reader.readLine());

            switch (type) {
                case 1:
                {
                    System.out.println("Write process  'Name, Arrival time, Burst time, Priority' ");   
                    for (int i = 0; i < numProcesses; i++) {
                        String input = reader.readLine();
                        String[] parts = input.split(", ");
                        if (parts.length < 4) {
                            System.out.println("Error: Please provide all three values (Name, Arrival time, Burst time , Priority).");
                            continue;
                        }
                        PriorityProcess p = new PriorityProcess(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));
                        processList.add(p);
                    }
                    System.out.println("Enter Context Switch Time: ");
                    int contextSwitchTime = Integer.parseInt(reader.readLine());
                    ContextSwitch.contextSwitchTime = contextSwitchTime;
                    scheduler.setStrategy(new PriorityScheduling()); 
                    scheduler.executeSchedule(processList);   
                    break;
                }
                case 2: {
                    System.out.println("Write process  'Name, Arrival time, Burst time' ");   
                    for (int i = 0; i < numProcesses; i++) {
                        String input = reader.readLine();
                        String[] parts = input.split(", ");
                        if (parts.length < 3) {
                            System.out.println("Error: Please provide all three values (Name, Arrival time, Burst time).");
                            continue;
                        }
                        Process p = new Process(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                        processList.add(p);
                    }
                    scheduler.setStrategy(new SJFScheduling()); 
                    scheduler.executeSchedule(processList);  
                    break;
                }
                case 3: {
                    System.out.println("Write process  'Name, Arrival time, Burst time' ");   
                    for (int i = 0; i < numProcesses; i++) {
                        String input = reader.readLine();
                        String[] parts = input.split(", ");
                        if (parts.length < 3) {
                            System.out.println("Error: Please provide all three values (Name, Arrival time, Burst time).");
                            continue;
                        }
                        Process p = new Process(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                        processList.add(p);
                    }
                    System.out.println("Enter Context Switch Time: ");
                    int contextSwitchTime = Integer.parseInt(reader.readLine());
                    ContextSwitch.contextSwitchTime = contextSwitchTime;
                    scheduler.setStrategy(new SRTFScheduling()); 
                    scheduler.executeSchedule(processList);  
                    break;
    
                }
                case 4: {
                    System.out.println("Write process  'Name, Arrival time, Burst time, Priority, Quantum' ");   
                    for (int i = 0; i < numProcesses; i++) {
                        String input = reader.readLine();
                        String[] parts = input.split(", ");
                        if (parts.length < 5) {
                            System.out.println("Error: Please provide all five values (Name, Arrival time, Burst time, Priority, Quantum).");
                            continue;
                        }
                        Process p = new FCAIProcess(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                        processList.add(p);
                    }
                    scheduler.setStrategy(new FCAIScheduling()); 
                    scheduler.executeSchedule(processList);  
                    break;
                }
                default: {
                    System.out.println("Unknown Process [Not Valid]");
                    break;
                }
            }
            processList.clear();
        }
        
        reader.close();
    }
}
