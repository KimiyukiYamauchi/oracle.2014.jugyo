select ename, sal, comm,
sal + nvl2(comm, comm, 0)
from employees
/
