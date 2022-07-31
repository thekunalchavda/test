package version_pack;
import java.io.*;
import java.util.ArrayList;

//import com.sun.tools.javac.jvm.StringConcat;
//import java.util.Arrays;

//import org.apache.commons.exec.util.StringUtils;

//import com.sun.tools.javac.util.List;
public class FileOperations {

	
	public static void main(String[] args) throws IOException {
	//	File f = new File("E:\\Employe_del.txt");
	
		ArrayList<String> list = new ArrayList<String>();
        File f = new File("D:\\usable_files\\Add_Employee.txt");
        FileReader fr2 = new FileReader(f);
        try (BufferedReader br2 = new BufferedReader(fr2)) {
			String line2;
			System.out.println("Reading text file after");
			
			while((line2 = br2.readLine()) != null){
	            list.add(line2);
	        }
			
		}
        
        System.out.println("*****************************");
        System.out.println(list+" "+list.size());
        System.out.println("*****************************");
        
        String[] filedata = list.toArray(new String[list.size()]);
        //System.out.println(filedata[1]);
        int i=1,j;
        j=filedata.length;
        System.out.println("length is "+j);
        
        String empid[] = new String[j];
        String empname[] = new String[j];
        /*String splitval[] = new String[j];*/
        String tin="asdfg   2222";
        String[] kk = tin.split(" ",5);
        System.out.println(kk[1]);
        
    	int op = 0;
        for(i=1;i<filedata.length;i++)
        {
        	String main = filedata[i].toString();
        	//System.out.println(main);
        	
        	//String main = "how are you ?";
        	op = main.length();
        	System.out.println(op);
        	String[] hh = main.split(" ");
        	System.out.println(hh[0]);
        	
        	
        	//empid[i-1]=splitval[0];
        	//empname[i-1]=splitval[2];
        	
        	//empid[i-1]=filedata[i].toString().substring(0, 4);
        	//empname[i-1]=filedata[i].toString().substring(5);
        	
        }
        
        for(i=0;i<empid.length;i++)
        {	
        	if(empid[i]==null)
        		break;
        	System.out.println(empid[i] + empname[i]);
        }
        
   //main method ends     
	}
	
	public void readFile() throws IOException
	{
		File file = new File("D:\\Add_Employee.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
             
        System.out.println("Reading text file before");
        while((line = br.readLine()) != null){
            //process the line
            System.out.println(line);
        }
        br.close();
        fr.close();
        
        FileWriter fw = new FileWriter(file,true);
        fw.append("2001 \n");
        fw.close();
        
        br.close();
        fr.close();
	}
//main class ends
}
