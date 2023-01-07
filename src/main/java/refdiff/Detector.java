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

}
