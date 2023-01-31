USE hotsix;

## create table operating_cal (`seq` INT,`year` INT, `month` INT, `day` INT, `day_of_week` INT, `reg_by` VARCHAR(255), `reg_date` DATETIME);
insert into operating_cal (`year`, `month`, `day`, `day_of_week`, `reg_by`, `reg_date`)
select date_format(d, '%Y'), date_format(d, '%m'), date_format(d, '%d'), dayofweek(d), 'admin', NOW()
from (
  select @rnum:=@rnum+1 as rownum, date(adddate('2023-01-01', interval @rnum day)) as d
  from (select @rnum:=-1) r, dummy
  ) dummy
where year(d) < 2024;