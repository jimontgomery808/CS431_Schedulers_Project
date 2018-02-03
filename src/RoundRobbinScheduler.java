import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoundRobbinScheduler
{
	private List<Process> processes;
	private int swap;
	private int timeQuantum;
	private String dataSource;
	private boolean firstProcess;
	private int cpu;
	private String outputFile;
	
	public RoundRobbinScheduler(String fileName, int timeQuantum, String outputFile)
	{
		this.dataSource = fileName;
		processes = new ArrayList<Process>();
		swap = 3;
		this.timeQuantum = timeQuantum;
		firstProcess = true;
		cpu = 0;
		this.outputFile = outputFile;
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
	}

	
	public void scheduleProcess() throws IOException
	{
		boolean lastProcessTerminated = false;
		int processIndex = 0;
		
		StringBuilder sb = new StringBuilder();	
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		writer.write("CpuTime, PID, StartingBurstTime, EndingBurstTime, CompletionTime\n");
		
		while(!processes.isEmpty())
		{
			if(processIndex == processes.size())
			{
				processIndex = 0;
			}

			int currentStartB = 0;
			int currentEndB = 0;
			int completionTime = 0;
			
			if(!lastProcessTerminated)
			{
				if(!firstProcess)
				{
					if(!lastProcessTerminated)
					{
						cpu += (swap + timeQuantum);
					}
					else
					{
						cpu += completionTime + swap;
					}
				}
				else
				{
					cpu = 0;
					firstProcess = false;
				}
			}
			
			sb.append(String.valueOf(cpu) + ',');
			sb.append(String.valueOf((processes.get(processIndex).getId())) + ',');
			currentStartB = processes.get(processIndex).getStartB();
			sb.append(String.valueOf(currentStartB) + ',');

			currentEndB = currentStartB - timeQuantum;
			if(currentEndB <= 0)
			{
				currentEndB = 0;
				processes.get(processIndex).setEndB(0);
				completionTime = cpu + currentStartB;
				cpu = completionTime + swap;
				lastProcessTerminated = true;
				processes.remove(processIndex);
				processIndex --;
			}
			else
			{
				completionTime = 0;
				lastProcessTerminated = false;
				processes.get(processIndex).setStartB(currentEndB);
			}
			sb.append(String.valueOf(currentEndB) + ',');
			sb.append(String.valueOf(completionTime) + ",\n");
			
			writer.write(sb.toString());
			processIndex ++;
			sb.delete(0, sb.length());
		}
		writer.close();
	}
	
	
	
}
