select deptno, count(*), avg(sal)
from employees
group by deptno
order by deptno
/
