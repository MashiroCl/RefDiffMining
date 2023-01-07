package refdiff;
import java.io.File;
import java.nio.file.Paths;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

import utils.ArgParser;
import objects.Commit;

public class Main {
	
	public static void main(String[] args) throws Exception {
		var ns = ArgParser.parseArgs(args);
		Commit c = new Commit(
				ns.getString("root"),
				ns.getString("name"),
				ns.getString("commit")
				);
		output(ns.getString("output"), c);
		
	}
	
		
	private static void output(String out, Commit commit){
		var mapper = new ObjectMapper();
		var outputFile = new File(Paths.get(out,commit.sha1+".json").toString());
		try {
			mapper.writeValue(outputFile,commit);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
	
	