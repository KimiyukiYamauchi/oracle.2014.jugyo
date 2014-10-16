alter session set nls_date_format = 'DD-MON-RR'
/
alter session set nls_date_language = 'AMERICAN'
/
select ename, to_char(hiredate, 'fmDDTH "of" Month')
from employees
/
