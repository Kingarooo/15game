# 15 puzzle

Suposing that you are in the project directory, to run this program, you should have Java installed and run this comand:

`
javac *.java && java main <search_mode> < input.txt
`

search_mode has this options:
- DFS
- BFS
- IDFS
- Greedy_Manhattan
- Greedy_Misplaced
- Astar_Manhattan
- Astar_Misplaced

In the folder project there is a file called input.txt. You should edit with the configuration you want in this format:

1 2 3 4 5 6 8 12 13 9 0 7 14 11 10 15 

1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0 

First line is the initial configuration and second line is goal configuration.
