package org.usfirst.frc2992.Robot;

import java.io.File;

public class RunTest {
	static FillData filler;
	static FollowPath follower;
	static File data;
	static State state;
	
	public enum State{
		FileCheck, Run;
	}


	public static void Init(String path){
		state= State.Run;
		filler = new FillData(data = new File(path));
		
		if(state == State.FileCheck){
			for(int i=0; i< filler.dataPts.robot.s.size(); i++){
				System.out.println(filler.dataPts.left.s.get(i).vel);
			}
		}
		if(state == State.Run){
		follower = new FollowPath(filler.dataPts);
		follower.PID_LoopInit(RobotMap.leftmotors, RobotMap.rightmotors);
		}
	}
	
	public static void Run(){
		
		if(state == State.Run){
		follower.enable();
		}
	}
}
