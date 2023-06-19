package refdiff;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

import refdiff.core.RefDiff;
import refdiff.core.diff.CstDiff;
import refdiff.core.diff.Relationship;
import refdiff.parsers.java.JavaPlugin;

import objects.Refactoring;

public class Detector {
	public static List<Refactoring> extractRefs(String dataRootPath, String repoName, String sha1) throws Exception{
		File dataRoot = new File(dataRootPath);
		JavaPlugin javaPlugin = new JavaPlugin(dataRoot);
		RefDiff refDiff = new RefDiff(javaPlugin);
		File targetGitFile = new File(Paths.get(dataRootPath, repoName, ".git").toString());
		CstDiff diff = refDiff.computeDiffForCommit(targetGitFile, sha1);
		List<Refactoring> refs= new ArrayList<>();
		for (Relationship rel: diff.getRefactoringRelationships()) {
			refs.add(new Refactoring(rel));
		}
		return refs;
	}
	
	
	public static void main(String [] args) throws Exception {
		String dataRootPath = "";
		String repoName = "mbassador";
		// Extract Method
		String sha1 = "02f18f8e4a120e05ab20cd1cc9a4f51d871394e3";
		// Move Method
		sha1 = "9ce3ceb6f4f13ff016ee6c7e24ca6a38eb1c189f";
		extractRefs(dataRootPath, repoName, sha1);
		
	}

}
