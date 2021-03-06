import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FCFSScheduler
{
	private List<Process> processes;
	private double turnAroundTime = 0;
	private String dataSource;
	private String outputFile;
	private int cpu;
	private int swap;
	private int size;
	
	public FCFSScheduler(String inFileName, String outputFile)
	{
		this.dataSource = inFileName;
		processes = new ArrayList<Process>();
		this.outputFile = outputFile;
		cpu = 0;
		swap = 3;
	}
	
	public double getAvgCPU()
	{
		return turnAroundTime/processes.size();
	}
	
	public void initProcesses() throws IOException
	{
		File file = new File(dataSource);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		
		int id;
		int burst;
		int priority;
		
		while ((line = br.readLine()) != null)
		{
			id = Integer.valueOf(line);
			burst = Integer.valueOf(br.readLine());
			priority = Integer.valueOf(br.readLine());
			Process lp = new Process(id, burst, priority);
			processes.add(lp);
			//System.out.println(id + ", " + burst + ", " + priority);
		}
		size = processes.size();
	}
	
	public void schedule() throws IOException
	{
		StringBuilder sb = new StringBuilder();	
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		writer.write("CpuTime, PID, StartingBurstTime, EndingBurstTime, CompletionTime\n");
		for(int i = 0; i < processes.size(); i ++)
		{
			Process process = processes.get(i);
			
			int id = process.getId();
			int burst = process.getBurstTime();
			int endBurst = 0;
			int completionTime = burst + cpu;
			
			turnAroundTime += (completionTime - cpu);
			
			sb.append(String.valueOf(cpu) + ',' + String.valueOf(id) + ',' + String.valueOf(burst) + ',' 
					  + String.valueOf(endBurst) + ',' + String.valueOf(completionTime) + '\n');
			

			writer.write(sb.toString());
			sb.delete(0, sb.length());
			
			cpu = completionTime + swap;
		}
		
		writer.close();
		
	}
}
