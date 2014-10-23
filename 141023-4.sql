select empno, ename, sal, grade
from employees
join salgrades
on sal between losal and hisal
/
