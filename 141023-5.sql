select e.empno, e.ename, m.empno, m.ename
from employees e
join employees m
on e.mgr = m.empno
/
