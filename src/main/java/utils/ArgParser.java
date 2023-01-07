package utils;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.helper.HelpScreenException;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class ArgParser {
    public static Namespace parseArgs(String[] args) throws ArgumentParserException {
        var parser = ArgumentParsers.newFor("refdiff").build();
        parser.description("Run RefDiff for given repository on specific commit.");
        parser.addArgument("-r", "--root")
                .help("Path to the repository's parent directory")
                .type(String.class)
                .metavar("path")
                .required(true);
        parser.addArgument("-n", "--name")
		        .help("Repository name")
		        .type(String.class)
		        .metavar("name")
		        .required(true);
        parser.addArgument("-o", "--output")
                .help("Path to the output json file")
                .type(String.class)
                .metavar("path")
                .required(true);
        parser.addArgument("-c", "--commit")
        		.help("The commit sha1")
        		.type(String.class)
        		.metavar("sha1")
        		.required(true);
        try {
            return parser.parseArgs(args);
        } catch (HelpScreenException e) {
            System.exit(0);
            return null;
        }
}
}
