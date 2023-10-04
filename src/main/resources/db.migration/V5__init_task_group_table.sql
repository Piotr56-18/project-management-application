CREATE TABLE task_groups(
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(100) NOT NULL,
    done BIT
)
alter table tasks add column task_group_id int null;
alter table tasks add foreign key (task_group_id) references task_groups (id);