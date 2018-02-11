import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LotteryScheduler
{
	private List<Process> processes;
	private int[] entryTimes;
	private int burstNumber;
	private int swap;
	private int timeQuantum;
	private String dataSource;
	private int sum;
	private boolean firstProcess;
	private int cpu;
	private int prioritiesCount;
	private String outputFile;
	private int size;
	private double turnAroundTime;
	private List<Integer> usedPID;
	
	public LotteryScheduler(String fileName, int timeQuantum, String outFile)
	{
		this.dataSource = fileName;
		processes = new ArrayList<Process>();
		swap = 3;
		this.timeQuantum = timeQuantum;
		sum = 0;
		firstProcess = true;
		cpu = 0;
		prioritiesCount = 0;
		outputFile = outFile;
		usedPID = new ArrayList<>();
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
			Process lp = new Process(id, burst, priority);
			processes.add(lp);
			prioritiesCount ++;
			//System.out.println(id + ", " + burst + ", " + priority);
		}
		
		size = processes.size();
		entryTimes = new int[size];
		generateSum();
	}
	
	public void generateSum()
	{
		for(int i = 0; i < processes.size(); i ++)
		{
			sum += processes.get(i).getPriority();
		}
	}

	private boolean pIDUsed(int pid)
	{
		boolean found = false;
		for(int i = 0; i< usedPID.size(); i ++)
		{
			if(pid == usedPID.get(i))
			{
				found = true;
			}
		}
		return found;
	}
	
	public void scheduleProcess() throws IOException
	{
		boolean lastProcessTerminated = false;
		StringBuilder sb = new StringBuilder();
		int entries = 0;
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		writer.write("CpuTime, PID, StartingBurstTime, EndingBurstTime, CompletionTime\n");
		
		while(prioritiesCount > 0)
		{
			burstNumber = randomNumber();
			
			int currentPID = 0;
			int currentStartB = 0;
			int currentEndB = 0;
			int completionTime = 0;
			
			currentPID = pickProcess();
			
			if(!lastProcessTerminated)
			{
				if(!firstProcess)
				{
					if(!lastProcessTerminated)
					{
						cpu += (swap + timeQuantum);
						if(!pIDUsed(currentPID))
						{
							entryTimes[currentPID] = cpu;
							entries ++;
						}
					}
					else
					{
						cpu += completionTime + swap;
						if(!pIDUsed(currentPID))
						{
							entryTimes[currentPID] = cpu;
							entries ++;
						}
					}
				}
				else
				{
					cpu = 0;
					if(!pIDUsed(currentPID))
					{
						entryTimes[currentPID] = cpu;
						entries ++;
					}
					firstProcess = false;
				}
			}
			if(!pIDUsed(currentPID))
			{
				usedPID.add(currentPID);
			}
			
			sb.append(String.valueOf(cpu));
			sb.append(',');
			sb.append(String.valueOf(currentPID + 1));
			sb.append(',');
			currentStartB = processes.get(currentPID).getStartB();
			sb.append(String.valueOf(currentStartB));
			sb.append(',');

			currentEndB = currentStartB - timeQuantum;
			if(currentEndB < 0)
			{
				currentEndB = 0;
			}
			sb.append(String.valueOf(currentEndB));
			sb.append(',');

			
			processes.get(currentPID).setStartB(currentEndB);
			
			if(currentEndB <= 0)
			{
				completionTime = cpu + currentStartB;
				//System.out.println((currentPID +1) + " " + entryTimes[currentPID]);
				turnAroundTime += (completionTime - entryTimes[currentPID]);
				int temp = (completionTime - entryTimes[currentPID]);
				//System.out.println("PID " + currentPID +": " + completionTime + "-" + entryTimes[currentPID] + "=" + temp);
				cpu = completionTime + swap;
				sum -= processes.get(currentPID).getPriority();
				processes.get(currentPID).setPriority(0);
				prioritiesCount --;
				lastProcessTerminated = true;
			}
			else
			{
				completionTime = 0;
				lastProcessTerminated = false;
			}
			
			sb.append(String.valueOf(completionTime));
			sb.append('\n');
			writer.write(sb.toString());
			sb.delete(0, sb.length());
		}
		
		writer.close();
	}
	
	public int randomNumber()
	{
		Random rand = new Random();
		return rand.nextInt(sum) + 1;
	}
	
	public int pickProcess()
	{
		int tempBurstNumber = this.burstNumber;
		int index = 0;
		
		while(tempBurstNumber > 0)
		{
			tempBurstNumber -= processes.get(index).getPriority();
			index ++;	
		}
		
		return index - 1;
	}
	
	public int getBurstNumber()
	{
		return burstNumber;
	}
	public void setBurstNumber(int burstNumber)
	{
		this.burstNumber = burstNumber;
	}
	
	
}
