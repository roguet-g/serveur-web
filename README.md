# serveur-web
Serveur Web Java Project Epitech

To run postgresql launch the docker-run.sh
or if it doesnt work, have a look and copy past the docker run command in your cmd/tty.

You should have a serveur postgresql on 127.0.0.1:5432

The default databases is epirss

You're free to run with your db in the project ! :)


!! HAVE A LOOK IN application.conf for schema line:
schema = """CREATE TABLE  IF NOT EXISTS user_rss (
    id        integer,
    name       varchar(40)
);"""
YOU MAY WANT PUT SOME CODE THERE. !!

Docker command:

  docker pull lumy/serveur-web
