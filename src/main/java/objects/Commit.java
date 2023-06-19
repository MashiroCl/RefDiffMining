package objects;

import java.util.LinkedList;
import java.util.List;
import refdiff.Detector;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Commit {
	public String sha1;
	public final List<RefactoringWrap> refactorings;
	
	public Commit(String repoPath, String repoName, String sha1){
		this.refactorings = new LinkedList<>();
		this.sha1 = sha1;
		try {
		var refs = Detector.extractRefs(repoPath, repoName, sha1);
		for(Refactoring r:refs) {
			this.refactorings.add(new RefactoringWrap(r));
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.printf("Refactoring detection failed for commit %s", this.sha1);
		}
	}
	
	
	public String getSha1() {
		return this.sha1;
	}
	
	public List<RefactoringWrap> getRefactorings() {
		return this.refactorings;
	}

}
