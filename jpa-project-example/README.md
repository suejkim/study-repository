# H2 DB

1. h2 다운로드 (최신버전 오류발생. v1.4.200 다운로드할 것)
   - https://www.h2database.com/html/download-archive.html
2. /h2/bin/h2.sh 실행
3. JDBC URL 입력
   - jdbc:h2:~/dev/temp/h2db/entity 
   - 연결시험아닌 바로 '연결' (db파일 생성)
   - 파일 생성 후 '연결시험' 가능
4. DDL
```sql
create table board (
   id bigint auto_increment primary key,
   title varchar(50),
   writer varchar(50),
   content varchar(500),
   created_at timestamp,
   updated_at timestamp
);
```
