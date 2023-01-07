package objects;

import java.util.List;
import refdiff.Detector;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Commit {
	public String sha1;
	public List<Refactoring> refs;
	
	public Commit(String repoPath, String repoName, String sha1){
		this.sha1 = sha1;
		try {
		this.refs = Detector.extractRefs(repoPath, repoName, sha1);
		}
		catch(Exception e) {
			System.out.printf("Refactoring detection failed for commit %s", this.sha1);
		}
	}
	
	
	public String getSha1() {
		return this.sha1;
	}
	
	public List<Refactoring> getRefs() {
		return this.refs;
	}

}
