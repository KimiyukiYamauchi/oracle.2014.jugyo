select ename, sal, comm,
sal + coalesce(comm, 0)
from employees
/
