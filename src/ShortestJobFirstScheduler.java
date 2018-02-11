import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShortestJobFirstScheduler
{
	private List<SJFProcess> processes;
	private String dataSource;
	private String outputFile;
	private int cpu;
	private int swap;
	
	public ShortestJobFirstScheduler(String inFileName, String outputFile)
	{
		this.dataSource = inFileName;
		processes = new ArrayList<SJFProcess>();
		this.outputFile = outputFile;
		cpu = 0;
		swap = 3;
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
			SJFProcess lp = new SJFProcess(id, burst, priority);
			processes.add(lp);
			//System.out.println(id + ", " + burst + ", " + priority);
		}
	}
	
	public void schedule() throws IOException
	{
		Collections.sort(processes);
		StringBuilder sb = new StringBuilder();	
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		writer.write("CpuTime, PID, StartingBurstTime, EndingBurstTime, CompletionTime\n");
		for(int i = 0; i < processes.size(); i ++)
		{
			SJFProcess process = processes.get(i);
			
			int id = process.getId();
			int burst = process.getBurstTime();
			int endBurst = 0;
			int completionTime = burst + cpu;
			
			sb.append(String.valueOf(cpu) + ',' + String.valueOf(id) + ',' + String.valueOf(burst) + ',' 
					  + String.valueOf(endBurst) + ',' + String.valueOf(completionTime) + '\n');
			

			writer.write(sb.toString());
			sb.delete(0, sb.length());
			cpu = completionTime + swap;
		}
		
		writer.close();
		
	}
}
