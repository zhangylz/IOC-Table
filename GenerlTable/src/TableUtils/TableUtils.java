package TableUtils;

public class TableUtils {

	public static String transTableRows(String... args){
		int count = 1;
		String tableRows = "";
		for(String arg : args){
			if(count == args.length){
				tableRows = tableRows + arg;
			}else{
				tableRows = tableRows + arg + ",";
			}
			count ++;
		}
		return tableRows;
	}
	
}
