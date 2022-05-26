select * from student
where age > 5 AND age < 40;

select student.name from student;

select * from student
where name like '%i%';

select * from student
where age < student.id;

select * from student
order by age;