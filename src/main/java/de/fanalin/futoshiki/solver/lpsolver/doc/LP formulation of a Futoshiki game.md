LP formulation
=
The LP problem is designed as followed:

The variables x(i,j,k) are binary, 1 meaning that the value at position (i,j) is k.

The objective does not really matter - we need only a feasible solution, there is no real need to
optimize anything.

Field is already set through the game specification
--
If field (i,j) is already known to have value k, then
x(i,j,k) = 1. The equations x(i,j,l) = 0 for l!=k are not necessary
 as they can be derived from the rule "Field only has one value".

Field only has one value
--
Each field should be set exactly once:
 For all (i, j): Sum(k=1..n) x(i,j,k) = 1

Value once in row
--
Each value is set in each row exactly once:
 For all (i, k): Sum(j=1..n) x(i,j,k) = 1

Value once in column
--
Each value is set in each column exactly once:
 For all (j, k): Sum(i=1..n) x(i,j,k) = 1

Inequality constraints value(i,j) < value(r,s)
--
In Futoshiki games, neighbored fields may have an inequality in the form of value(i,j) < value(r,s).
This chapter describes the implications for the left hand side. See next chapter for the right hand
side.

Typically these fields are neighbors, but it's easier to forget the neighborhood for now.
It re-appears as soon we are creating the LP constraints from the Futoshiki game, as only neighbored
fields have inequalities.

Given our variables, we can describe the smaller-than side of the equation as followed:
If a field (i,j) has value k, then the field (r,s) on the other side of the equation must be greater
than k. This also means that all values l on the other side with l <= k are not allowed.
In other words: if x(i,j,k)=1, then x(r,s,l) = 0 (which is the same as x(r,s,l)< 1) for all l <= k.

For all k, for all l < k: x(r,s,l) - 1 < M*(1 - x(i,j,k) )

See chapter "Formulation of if-clauses in LP" for the LP formulation of if-clauses.

Inequality constraints value(r,s) > value(i,j)
--
If a field (r,s) has value l, then the field (i,j) on the other side of the equation must contain a
value smaller than l. This also means that all values k with k >= l are not allowed.
In other words: if x(r,s,l)=1, then x(i,j,k) = 0 (which is the same as x(i,j,k)< 1) for all k >= l.


Formulation of if-clauses in LP
--
For a binary p: "if p=1, then a < b" can be modelled as a-b < M*(1-p), where M is "big enough"

Proof:
If p=1, then the equation means a-b < 0 <=> a < b.

If p=0, then the equation means a-b < M <=> a < b + M, which is always true for big enough M.