select ename, sal, comm,
sal + nvl(comm,0)
from employees
/
