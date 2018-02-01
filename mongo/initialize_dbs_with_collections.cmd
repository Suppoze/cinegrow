@ECHO OFF

rem purge database
echo Purging databases
rmdir /Q /S data
mkdir data
cd data
mkdir db
mkdir conf
cd ..

rem startup conf and initialize
echo Initializing configuration server
start starter\start-conf.cmd
TIMEOUT /T 2
mongo --port 27001 config scripts/conf-initiate.js

rem startup sharddb and initialize
echo Initializing sharded server
start starter\start-db.cmd
TIMEOUT /T 2
mongo --port 27011 config scripts/shard-initiate.js

rem startup shardserver
echo Initializing mongos
start starter\start-serv.cmd
TIMEOUT /T 15
mongo --port 27021 config scripts/shard-serv-initiate.js

echo Sharding
mongo --port 27021 config scripts/shard.js