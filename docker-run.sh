HOME=//c/Users/lumy
PATH=$HOME/IdeaProjects/serveur-web/sql/
# -v $PATH:/app/ext  # To Add Under linux
echo docker.exe run --name episql -p 127.0.0.1:5432:5432 -e POSTGRES_PASSWORD=pwd_epi -e POSTGRES_DB=epirss -e POSTGRES_USER=epi -e PGDATA=/app/ext/pgdata -d postgres

#--link episql