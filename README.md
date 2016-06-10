# Chebyshev-Polynomials--Dynamic-Programming
The following program will calculate the Chebyshev Polynomial up to whatever the user enters.

Recurrence Relation Used:
T[n+1][x] = 2x*T[n][x] - T[n-1][x]

My approach was to use dynamic programming to calculate polynomials above n = 1 due to the 
fact that the calculations are often repeated otherwise.
        
