select ordno, date_ordered, cname, ename
from orders
natural join customers
join employees
on orders.salesman_no = employees.empno
/
