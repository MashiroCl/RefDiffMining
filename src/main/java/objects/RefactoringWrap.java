package objects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import refdiff.Detector;

@JsonSerialize
public class RefactoringWrap {
	private final String type;
	private final List<Location> leftSideLocations = new LinkedList<>();
	private final List<Location> rightSideLocations = new LinkedList<>();
	
	public RefactoringWrap(Refactoring refactoring) {
		this.type = refactoring.getType();
		this.leftSideLocations.add(
				new Location(refactoring.getLeftStartLine(),
						refactoring.getBeforeFilePath(),
						refactoring.getBeforeCodeElement())
				);
		this.rightSideLocations.add(
				new Location(refactoring.getRightStartLine(),
						refactoring.getAfterFilePath(),
						refactoring.getAfterCodeElement())
				);
		
	}
	public List<Location> getLeftSideLocations() {
		return this.leftSideLocations;
	}
	
	public List<Location> getRightSideLocations() {
		return rightSideLocations;
	}
	public String getType() {
		return type;
	}
	
}
