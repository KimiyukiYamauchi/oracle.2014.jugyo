select deptno, job, count(*), avg(sal)
from employees
group by deptno, job
order by deptno
/
