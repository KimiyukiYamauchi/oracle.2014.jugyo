select ename, hiredate, hiredate+90, hiredate-90
from employees
where deptno = 10
/
