select empno, ename, dname
from employees
natural join departments
where deptno in(10, 20)
/
