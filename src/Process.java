
public class Process
{
	private int id;
	private int burstTime;
	private int priority;
	private int startB;
	private int endB;
	
	public Process(int id, int burstTime, int priority)
	{
		this.id = id;
		this.burstTime = burstTime;
		this.priority = priority;
		this.startB = burstTime;
		this.endB = -1;
	}
	
	public int getStartB()
	{
		return startB;
	}
	public void setStartB(int startB)
	{
		this.startB = startB;
	}
	public int getEndB()
	{
		return endB;
	}
	public void setEndB(int endB)
	{
		this.endB = endB;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getBurstTime()
	{
		return burstTime;
	}
	public void setBurstTime(int burstTime)
	{
		this.burstTime = burstTime;
	}
	public int getPriority()
	{
		return priority;
	}
	public void setPriority(int priority)
	{
		this.priority = priority;
	}
}
