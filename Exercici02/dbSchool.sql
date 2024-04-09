2024-04-08 15:49:00+00:00 [Note] [Entrypoint]: Entrypoint script for MySQL Server 8.3.0-1.el8 started.
2024-04-08 15:49:00+00:00 [Note] [Entrypoint]: Switching to dedicated user 'mysql'
2024-04-08 15:49:00+00:00 [Note] [Entrypoint]: Entrypoint script for MySQL Server 8.3.0-1.el8 started.
2024-04-08 15:49:01+00:00 [Note] [Entrypoint]: Initializing database files
2024-04-08T15:49:01.093627Z 0 [System] [MY-015017] [Server] MySQL Server Initialization - start.
2024-04-08T15:49:01.095830Z 0 [System] [MY-013169] [Server] /usr/sbin/mysqld (mysqld 8.3.0) initializing of server in progress as process 80
2024-04-08T15:49:01.125347Z 1 [System] [MY-013576] [InnoDB] InnoDB initialization has started.
2024-04-08T15:49:01.632505Z 1 [System] [MY-013577] [InnoDB] InnoDB initialization has ended.
2024-04-08T15:49:03.225326Z 6 [Warning] [MY-010453] [Server] root@localhost is created with an empty password ! Please consider switching off the --initialize-insecure option.
2024-04-08T15:49:06.149175Z 0 [System] [MY-015018] [Server] MySQL Server Initialization - end.
2024-04-08 15:49:06+00:00 [Note] [Entrypoint]: Database files initialized
2024-04-08 15:49:06+00:00 [Note] [Entrypoint]: Starting temporary server
mysqld will log errors to /var/lib/mysql/0a8ac78162ec.err
mysqld is running as pid 126
2024-04-08 15:49:07+00:00 [Note] [Entrypoint]: Temporary server started.
'/var/lib/mysql/mysql.sock' -> '/var/run/mysqld/mysqld.sock'
Warning: Unable to load '/usr/share/zoneinfo/iso3166.tab' as time zone. Skipping it.
Warning: Unable to load '/usr/share/zoneinfo/leap-seconds.list' as time zone. Skipping it.
Warning: Unable to load '/usr/share/zoneinfo/leapseconds' as time zone. Skipping it.
Warning: Unable to load '/usr/share/zoneinfo/tzdata.zi' as time zone. Skipping it.
Warning: Unable to load '/usr/share/zoneinfo/zone.tab' as time zone. Skipping it.
Warning: Unable to load '/usr/share/zoneinfo/zone1970.tab' as time zone. Skipping it.
2024-04-08 15:49:09+00:00 [Note] [Entrypoint]: Creating database dbapi
2024-04-08 15:49:09+00:00 [Note] [Entrypoint]: Creating user usuari
2024-04-08 15:49:09+00:00 [Note] [Entrypoint]: Giving user usuari access to schema dbapi

2024-04-08 15:49:09+00:00 [Note] [Entrypoint]: Stopping temporary server
2024-04-08 15:49:11+00:00 [Note] [Entrypoint]: Temporary server stopped

2024-04-08 15:49:11+00:00 [Note] [Entrypoint]: MySQL init process done. Ready for start up.

2024-04-08T15:49:11.210284Z 0 [System] [MY-015015] [Server] MySQL Server - start.
2024-04-08T15:49:11.517969Z 0 [System] [MY-010116] [Server] /usr/sbin/mysqld (mysqld 8.3.0) starting as process 1
2024-04-08T15:49:11.528776Z 1 [System] [MY-013576] [InnoDB] InnoDB initialization has started.
2024-04-08T15:49:11.710489Z 1 [System] [MY-013577] [InnoDB] InnoDB initialization has ended.
2024-04-08T15:49:11.969328Z 0 [Warning] [MY-010068] [Server] CA certificate ca.pem is self signed.
2024-04-08T15:49:11.969368Z 0 [System] [MY-013602] [Server] Channel mysql_main configured to support TLS. Encrypted connections are now supported for this channel.
2024-04-08T15:49:11.971968Z 0 [Warning] [MY-011810] [Server] Insecure configuration for --pid-file: Location '/var/run/mysqld' in the path is accessible to all OS users. Consider choosing a different directory.
2024-04-08T15:49:11.996274Z 0 [System] [MY-011323] [Server] X Plugin ready for connections. Bind-address: '::' port: 33060, socket: /var/run/mysqld/mysqlx.sock
2024-04-08T15:49:11.996375Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.3.0'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server - GPL.
^C^C^C^Cdassdasasdadsadasdasda2024-04-08T15:54:26.019443Z 9 [Warning] [MY-013360] [Server] Plugin mysql_native_password reported: ''mysql_native_password' is deprecated and will be removed in a future release. Please use caching_sha2_password instead'
2024-04-08T15:54:57.321180Z 10 [Warning] [MY-013360] [Server] Plugin mysql_native_password reported: ''mysql_native_password' is deprecated and will be removed in a future release. Please use caching_sha2_password instead'
2024-04-08T15:55:11.031736Z 11 [Warning] [MY-013360] [Server] Plugin mysql_native_password reported: ''mysql_native_password' is deprecated and will be removed in a future release. Please use caching_sha2_password instead'
2024-04-08T15:56:24.826882Z 12 [Warning] [MY-013360] [Server] Plugin mysql_native_password reported: ''mysql_native_password' is deprecated and will be removed in a future release. Please use caching_sha2_password instead'
