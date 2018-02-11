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
	private List<Integer> entryTimes;
	private int swap;
	private int timeQuantum;
	private String dataSource;
	private boolean firstProcess;
	private int cpu;
	private String outputFile;
	private int size;
	private double turnAroundTime;
	
	public RoundRobbinScheduler(String fileName, int timeQuantum, String outputFile)
	{
		this.dataSource = fileName;
		processes = new ArrayList<Process>();
		swap = 3;
		this.timeQuantum = timeQuantum;
		firstProcess = true;
		cpu = 0;
		this.outputFile = outputFile;
		entryTimes = new ArrayList<>();
	}
	public double getAvgTurnAround()
	{
		return turnAroundTime/size;
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
			Process process = new Process(id, burst, priority);
			processes.add(process);
		}
		size = processes.size();
	}

	
	public void scheduleProcess() throws IOException
	{
		boolean lastProcessTerminated = false;
		int processIndex = 0;
		
		StringBuilder sb = new StringBuilder();	
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		writer.write("CpuTime, PID, StartingBurstTime, EndingBurstTime, CompletionTime\n");
		
		int index = 0;
		
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
						if(entryTimes.size() <= size)
						{
							entryTimes.add(cpu);
						}
					}
					else
					{
						cpu += completionTime + swap;
						if(entryTimes.size() <= size)
						{
							entryTimes.add(cpu);
						}
					}
				}
				else
				{
					cpu = 0;
					entryTimes.add(cpu);
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
				turnAroundTime += (completionTime - entryTimes.get(processes.get(processIndex).getId() -1));
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
