from sympy import *
import sys

x = symbols('x')

f = S(str(sys.argv[1])).subs(x, float(sys.argv[2]))

file = open('rez.txt', 'w')
file.write(str(f))
file.close()