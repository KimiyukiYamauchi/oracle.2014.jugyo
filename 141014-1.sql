select empno, ename, yomi
from employees
where upper(yomi) = 'TAKAHASHI'
/
