select ename, deptno, sal,
case deptno when 10 then sal * 1.1
            when 20 then sal * 1.2
            else sal end NEW_SAL
from employees
/
