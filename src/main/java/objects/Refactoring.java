package objects;

import refdiff.Detector;
import refdiff.core.diff.Relationship;

import java.util.NoSuchElementException;
import java.util.Optional;

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
		String type = rel.getType().name();
		// the type for MOVE CLASS & MOVE METHOD & MOVE INTERFACE are all MOVE,
		if(type.equals("MOVE")) {
			type = "MOVE_"+rel.getNodeBefore().getType().replace("Declaration", "");
			System.out.println(type);
		}
		if(type.equals("EXTRACT")) {
			type = "EXTRACT_" + rel.getNodeBefore().getType().replace("Declaration", "");
		}
		this.type = type;
		this.beforeCodeElement = rel.getNodeBefore().getLocalName();
		this.afterCodeElement = rel.getNodeAfter().getLocalName();
		this.beforeFilePath = rel.getNodeBefore().getLocation().format().split(":")[0];
		this.afterFilePath = rel.getNodeAfter().getLocation().format().split(":")[0];
		this.leftStartLine = rel.getNodeBefore().getLocation().getLine();
		this.rightStartLine =  rel.getNodeAfter().getLocation().getLine();
		
		String localName = rel.getNodeBefore().getLocalName();
		String nameSpace = rel.getNodeBefore().getNamespace();
		String simpleSpace = rel.getNodeBefore().getSimpleName();
		String type2 = rel.getNodeBefore().getType().toString();
//		System.out.println(localName+"@"+nameSpace+"@"+simpleSpace+"@"+type2);
	}
	
	public static void main(String []args) throws Exception {
		String dataRootPath = "/Users/leichen/Code/pythonProject/pythonProject/pythonProject/SCRMDetection/experiment/data";
		String repoName = "mbassador";
		String sha1 = "a48811455c057e7e0568f758ef8b831ac7b9f528";
		sha1 = "89f44544bd9d68c94c5fc84dcef167cb33b25df5";
		var refs = Detector.extractRefs(dataRootPath, repoName, sha1);
		for(Refactoring r: refs){
			System.out.println("refactoring type:"+r.getType());
		}
		
	}
	
	
}
