SELECT *
FROM db_1702.iptest;

SELECT *
FROM db_1702.iptest
WHERE inet_aton(?) BETWEEN inet_aton(start) AND inet_aton(stop);