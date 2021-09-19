create table day_of_week_time_blocked (
    id SERIAL not null,
    tenant varchar(255) not null,
    day_of_week INTEGER not null,
    start_time TIME not null,
    end_time TIME not null,
    primary key (id)
)
