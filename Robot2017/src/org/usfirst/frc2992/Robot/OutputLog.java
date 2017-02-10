package org.usfirst.frc2992.Robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

import java.io.FileWriter;

public class OutputLog {
	File log;
	FileWriter fileWrite;
	BufferedWriter printLine;
	String filePath = "/home/lvuser/Output.txt";
	double lineNum = 0;
	public OutputLog() throws IOException{
		log = new File(filePath);
		fileWrite = new FileWriter(log);
		printLine = new BufferedWriter(fileWrite);
	}
	
	
	public void enable(){
		try{
				log.createNewFile();
				//System.out.println(4);
				fileWrite.flush();
				
			} catch (IOException e){
				e.printStackTrace();
			}
		
		try {
			printLine.write("enabled");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void Update(double data) throws IOException{
		lineNum++;
		printLine.write("Current Draw" + lineNum + ": ");
		printLine.write("[" + data + "]");
		printLine.newLine();
	}
	
	public void Update(double data, double data2) throws IOException{
		lineNum++;
		printLine.write("Vision Update Position" + lineNum + ": ");
		printLine.write("[" + data);
		printLine.write("," + data2 + "]");
		printLine.newLine();
	}


	public void disable() {
		// TODO Auto-generated method stub
			try {
				printLine.close();
				fileWrite.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
