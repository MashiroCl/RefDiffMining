package objects;

import refdiff.core.diff.Relationship;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public final class Refactoring {

	private final String type;
	private final String beforeCodeElement;
	private final String afterCodeElement;
	private final String beforeFilePath;
	private final String afterFilePath;
	private final int leftStartLine;
	private final int rightStartLine;
	
	public String getType() {
		return type;
	}


	public String getBeforeCodeElement() {
		return beforeCodeElement;
	}


	public String getAfterCodeElement() {
		return afterCodeElement;
	}


	public String getBeforeFilePath() {
		return beforeFilePath;
	}


	public String getAfterFilePath() {
		return afterFilePath;
	}


	public int getLeftStartLine() {
		return leftStartLine;
	}


	public int getRightStartLine() {
		return rightStartLine;
	}

	public Refactoring(String type, String beforeCodeElement, String afterCodeElement, String beforeFilePath, String afterFilePath, int leftStartLine, int rightStartLine) {
		this.type = type;
		this.beforeCodeElement = beforeCodeElement;
		this.afterCodeElement = afterCodeElement;
		this.beforeFilePath = beforeFilePath;
		this.afterFilePath = afterFilePath;
		this.leftStartLine = leftStartLine;
		this.rightStartLine = rightStartLine;
	}
	

	public Refactoring(Relationship rel) {
		String type = rel.getType().toString();
		// the type for MOVE CLASS & MOVE METHOD are both MOVE,
		if(type.equals("MOVE")) {
			if(rel.getStandardDescription().toString().equals("Method")) {
				type = "MOVE_Method";
			}
			else {type = "MOVE_Class";}
		}
		this.type = type;
		this.beforeCodeElement = rel.getNodeBefore().getLocalName();
		this.afterCodeElement = rel.getNodeAfter().getLocalName();
		this.beforeFilePath = rel.getNodeBefore().getLocation().format().split(":")[0];
		this.afterFilePath = rel.getNodeAfter().getLocation().format().split(":")[0];
		this.leftStartLine = rel.getNodeBefore().getLocation().getLine();
		this.rightStartLine =  rel.getNodeAfter().getLocation().getLine();
	}
	
	
	
}
