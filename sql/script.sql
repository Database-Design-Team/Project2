create table artist
(
    artist_id   serial                    not null
        constraint artist_pk
            primary key,
    artist_name varchar(16)               not null,
    date_formed date default CURRENT_DATE not null
);

alter table artist
    owner to zgqjvuamulzrzb;

create unique index artist_artist_id_uindex
    on artist (artist_id);

create unique index artist_artist_name_uindex
    on artist (artist_name);

create table genre
(
    genre_id    serial      not null
        constraint genre_pk
            primary key,
    genre_title varchar(16) not null
);

alter table genre
    owner to zgqjvuamulzrzb;

create unique index genre_genre_id_uindex
    on genre (genre_id);

create unique index genre_genre_title_uindex
    on genre (genre_title);

create table user_account
(
    username    varchar(16)                  not null
        constraint user_pk
            primary key,
    student_id  integer                      not null,
    admin       boolean default false        not null,
    password    varchar                      not null,
    email       varchar                      not null,
    date_joined date    default CURRENT_DATE not null
);

alter table user_account
    owner to zgqjvuamulzrzb;

create table band_member
(
    member_name varchar(16) not null
        constraint band_member_user_username_fk
            references user_account
            on update cascade on delete cascade,
    band_id     integer     not null
        constraint band_member_artist_artist_id_fk
            references artist
            on update cascade on delete cascade
);

alter table band_member
    owner to zgqjvuamulzrzb;

create unique index user_student_id_uindex
    on user_account (student_id);

create unique index user_username_uindex
    on user_account (username);

create unique index user_account_email_uindex
    on user_account (email);

create table audio
(
    audio_file           bytea                     not null,
    song_name            varchar                   not null,
    deleted              boolean                   not null,
    audio_id             serial                    not null
        constraint audio_pkey
            primary key,
    aggregate_popularity double precision,
    artist               serial
        constraint audio_artist_artist_id_fk
            references artist
            on update cascade on delete set null,
    genre                integer
        constraint audio_genre_genre_id_fk
            references genre,
    date_uploaded        date default CURRENT_DATE not null
);

alter table audio
    owner to zgqjvuamulzrzb;

create table library
(
    song       integer                                       not null
        constraint library_audio_audio_id_fk
            references audio
            on update cascade on delete cascade,
    owner      varchar(16)                                   not null
        constraint library_user_username_fk
            references user_account
            on update cascade on delete cascade,
    date_added timestamp with time zone default CURRENT_DATE not null,
    constraint library_pk
        unique (song, owner)
);

alter table library
    owner to zgqjvuamulzrzb;

create table song_listens
(
    song       integer                   not null
        constraint song_statistics_audio_audio_id_fk
            references audio
            on update cascade on delete cascade,
    time_stamp date default CURRENT_DATE not null,
    username   varchar                   not null
        constraint song_statistics_user_account_username_fk
            references user_account
            on update cascade on delete cascade
);

alter table song_listens
    owner to zgqjvuamulzrzb;

create table song_ratings
(
    song_id   integer not null
        constraint song_ratings_audio_audio_id_fk
            references audio
            on update cascade on delete cascade,
    user_name varchar not null
        constraint song_ratings_user_account_username_fk
            references user_account
            on update cascade on delete cascade,
    rating    integer not null,
    constraint song_ratings_pk
        unique (song_id, user_name)
);

alter table song_ratings
    owner to zgqjvuamulzrzb;

create function update_popularity_function() returns trigger
    language plpgsql
as
$$
BEGIN
    UPDATE audio
    SET aggregate_popularity = (
        SELECT AVG(song_ratings.rating)
        FROM song_ratings
        WHERE song_ratings.song_id = new.song_id)
    WHERE audio_id = new.song_id;

    RETURN NEW;
END;
$$;

alter function update_popularity_function() owner to zgqjvuamulzrzb;

create trigger update_popularity_trigger
    after insert
    on song_ratings
    for each row
execute procedure update_popularity_function();

create function audio_dropped_function() returns trigger
    language plpgsql
as
$$
BEGIN
    DELETE
    FROM library
    WHERE song = NEW.audio_id;

    RETURN NEW;
END;
$$;

alter function audio_dropped_function() owner to zgqjvuamulzrzb;

create trigger audio_flagged_trigger
    after update
    on audio
    for each row
    when (new.deleted = true AND old.deleted = false)
execute procedure audio_dropped_function();

create function track_added_function() returns trigger
    language plpgsql
as
$$
DECLARE
    release_id INTEGER;
    song_id    INTEGER;
BEGIN
    UPDATE track
    SET track_number =
            (SELECT COUNT(*)
             FROM track
             WHERE release = release_id)
    WHERE song = song_id;

    RETURN NEW;
END;
$$;

alter function track_added_function() owner to zgqjvuamulzrzb;

create function artist_deleted_function() returns trigger
    language plpgsql
as
$$
BEGIN
    UPDATE audio
    SET deleted = true,
        artist  = null
    WHERE artist = old.artist_id;

    DELETE
    FROM band_member
    WHERE band_id = OLD.artist_id;

    RETURN NEW;
END;
$$;

alter function artist_deleted_function() owner to zgqjvuamulzrzb;

create trigger artist_deleted_trigger
    after delete
    on artist
    for each row
execute procedure artist_deleted_function();


