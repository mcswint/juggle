Name: Amanda Swinton
Contact Info:
email: acs2211@barnard.edu
phone: 413-271-2110

This is my solution for the Juggle Fest problem posted: http://www.yodlecareers.com/puzzles/jugglefest.html
--------------------------------------------------------
Input: a .txt file containing jugglers and circuits
Output: will produce an output file called output.txt with the assigned teams.


To Run:
	After compiling, run from Test
	The input file can be changed at line 13 in the class

The program consists of 5 classes:
Juggler.java - a juggler object with variables for each of the skills as well as a preference list of circuits

Circuit.java - a circuit object with variables for each of the skills as well as a list for the members in that circuit

MatchRank.java - a comparable class that takes a juggler and a circuit and finds the rank through the dot product of the skills

AssignTeams.java - a class that reads the input from a file, sorts it into arrayLists, assigns jugglers to circuits, and writes to an output file. 

Test.java - a class that runs AssignTeams and gives it the input file.

----------------------------------------------------------

Strategy:

My basic strategy for this puzzle was to first get the jugglers and circuits parsed from the file and into arrayLists.
I would then take the first juggler and find his most preferred circuit. I would add him to the circuit using a method in circuit that had 
3 possible outcomes. If the circuit team was not full, it would add the member list and return a juggler with an id of -1 to confirm the 
successful add and the juggler gets moved to the matched list.
If the circuit team was full, it would compare the rank (calculated in MatchRank) to the other ranks in the list. If the rank was
higher than something on the list, it would add the new juggler to the list, remove the lower juggler from the list, and return the lower 
juggler to the AssignTeams class where it would add it to the back of the juggler list to be resorted. If the new juggler has a lower rank
than all the other members, it would also be returned to the AssignTeams class and added to the back of the juggler list to be resorted. 
Each juggler object has an index that keeps track of which circuits to try in their preferences. If it doesn't get on the first circuits 
team, it will increment so the next time it is called, it returns the next preferred circuit in line. When the index gets greater than the 
size of the preferred list, it returns -1 and the juggler gets moved to the failed list.   

When the juggler list is empty, it moves on to the method assignRest which doesn't take preference into account. It compiles a 
list of circuits that have space on their team and for each spot, it goes through every leftover juggler and finds the best match 
until all spots are filled and all jugglers are matched. 

The writeOut() method parses the circuits into the desired format and outputs them to a file called output.txt.