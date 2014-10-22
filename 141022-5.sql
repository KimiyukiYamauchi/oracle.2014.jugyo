select deptno, job, count(*), avg(sal)
from employees
group by deptno, job
having count(*) >= 2
order by deptno
/
