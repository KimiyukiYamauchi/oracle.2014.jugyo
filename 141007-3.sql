select empno, ename, hiredate
from employees
where hiredate = to_date('2006年10月21日','YYYY"年"MM"月"DD"日"')
/
