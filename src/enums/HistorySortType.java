package enums;

import java.util.Comparator;

import commperator.ComparatorAllShareName;
import commperator.ComparatorMethodName;
import commperator.ComparatorTime;


public enum HistorySortType{
	TIME("time", new ComparatorTime()),
	METHOD("methode", new ComparatorMethodName()),
	ALLSHARENAME("allsharename", new ComparatorAllShareName());
	
	private String methodname;
	private Comparator<?> compartype;
	
	HistorySortType(String name,  Comparator<?> compareclass){
		this.methodname = name;
		this.compartype = compareclass;
	}
	public static Comparator<?> getCompareType(String name){
		for (HistorySortType status : values()) {
		      if (name.equals(status.methodname)) {
		        return status.compartype;
		      }
		}
		return new ComparatorTime();
	}
	public String getMethodName(){
		return methodname;
	}
}
