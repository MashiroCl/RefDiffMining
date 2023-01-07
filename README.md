# RefDiffMining

Use RefDiff to mine refactorings for specific commit for git repository


## Example usage
Detect commit whose sha1 is `<sha1>` in repository located in `<repo_parent_path>/<repo_name>/<.git>` and output json file under `<output_directory>`
```
java -jar RefDiff.jar -r <repo_parent_path> -n <repo_name> -o <output_directory> -c <sha1>
```
