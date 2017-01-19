HOME=//c/Users/$USER
PATH=$HOME/IdeaProjects/serveur-web/sql/
echo docker.exe run --name episql -e POSTGRES_PASSWORD=pwd_epi -v $PATH:/app/ext  -e POSTGRES_DB=/app/ext/pgdata -d postgres

#--link episql