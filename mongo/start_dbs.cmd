@ECHO OFF

rem startup conf
start mongod --config configs/conf.yml

rem startup sharddb
start mongod --config configs/db.yml

rem startup shardserver
start mongos --config configs/shard-serv.yml