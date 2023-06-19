package objects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Location {
	private final int startLine;
	// endLine information is not provided by RefDiff. To keep the data format the same as RefactoringMiner, set an endLine value to -1.
	private final int endLine = -1;
	private final String filePath;
	private final String codeELement;

	public Location(int startLine, String filePath, String codeElement) {
		this.startLine = startLine;
		this.filePath = filePath;
		this.codeELement = codeElement;
	}

	public int getStartLine() {
		return startLine;
	}

	public String getFilePath() {
		return filePath;
	}

	public int getEndLine() {
		return endLine;
	}

	public String getCodeELement() {
		return codeELement;
	}
	
	
	
}
