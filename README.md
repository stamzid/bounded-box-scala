# bounded-box-scala

This Scala project identifies the largest non-overlapping minimum bounding boxes for groups of asterisks within a given grid. Input can be provided through standard input or a text file. This implementation showcases the practical use of the depth-first search (DFS) algorithm.

## Setup and Execution

### Compile the Project

To compile the project, ensuring all Scala files are properly processed, use the following command:

```bash
sbt clean compile
sbt assembly
```

adapted to the Scala version in use.

### Running the Program

You can run the application directly using the jar file created by `sbt assembly`. To make things easier, there is a bash script called `bounding-box` that can be used to execute the jar. The program accepts input in two ways:

1. **From a Text File:**

   Provide a text file as an argument to the program. The text file should contain the grid, with asterisks (`*`) representing points of interest and hyphens (`-`) representing empty spaces. Each line in the file represents a row in the grid.

   ```bash
   ./bounding-box < path/to/yourfile.txt
   ```

2. **Through Stdin**

   ```bash
   ./bounding-box 
   ```

   This will accept the grid of "-" and "*" until a newline is entered. Make sure every line is of the same length









