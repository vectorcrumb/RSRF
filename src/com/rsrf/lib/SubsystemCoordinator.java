package com.rsrf.lib;

import java.util.Vector;

import com.rsrf.robot.subsystems.SubComponent;

public class SubsystemCoordinator {
	
	private Vector<SubComponent> subsystems;
	private static SubsystemCoordinator instance;
	
	private SubsystemCoordinator () {
		this.subsystems = new Vector<SubComponent>();
		// TODO: Add IO components getInstance()
	}
	
	public static SubsystemCoordinator getInstance() {
		if (instance == null) {
			instance = new SubsystemCoordinator();
		}
		return instance;
	}
	
	public void updateSubsystems() {
		for (SubComponent subsystem : subsystems) {
			subsystem.update();
		}
	}
	
	public void stopSubsystems() {
		for (SubComponent subsystem : subsystems) {
			subsystem.disable();
		}
	}
	
	public void addSubsytem(SubComponent subsystem) {
		subsystems.addElement(subsystem);
	}
	
	// TODO: Add removeSubsystem()
}
