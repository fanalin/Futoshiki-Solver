README
=
A solver for Futoshiki games

Installation
==
The futoshiki solver needs the GNU Linear Programming Kit (GLPK) to work.
This library is a C library which you must download speficially for your platform and put
the files in your PATH variable.

To use GLPK, the solver uses JAVA ILP, a facade for different MIP libraries.
This library is not available via maven.
Download the jar file from the project page at sourceforge (https://sourceforge.net/projects/javailp/)
 and put it into the ${project.basedir}/lib directory of this project.