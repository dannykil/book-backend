FROM openjdk:11-jdk-slim

WORKDIR /app

# COPY만 docker-compose 파일의 위치를 기반으로 작동함
COPY . .

# 개행문자 오류 해결 [unix와 window 시스템 차이]
# RUN sed -i 's/\r$//' gradlew

# docker compose가 아닌 개별 배포 시 활성화
# docker compose로 배포 시 비활성화
# *** 수정 시 이미지 재생성해야함
# * 실행 명령어에 .env파일 명시
# >>> docker run -d --name ex09-backend-1 --net ex09_network --net-alias backend --env-file /Users/danny_mac/Documents/docker/ex09/.env -p 8081:8080 ex09-backend

# 도커 빌드 및 배포 순서
# 1) git push
# 2) 젠킨스로 백/프론트 프로젝트 빌드 
# 3) (젠킨스 서버)도커 이미지 생성(docker build -t 이미지명 .)
# 4) (젠킨스 서버)도커 이미지 도커 허브 push
# 5) 서버/로컬에서 컨테이너 실행 
# docker build -t dannielkil/book-db . 
# docker build -t dannielkil/book-backend .
# docker build -t dannielkil/book-frontend .
# docker run -d --name docker-db-1 --hostname db --net docker_network --net-alias docker-db-1 -v ./db/store:/var/lib/mysql -p 3307:3306 docker-db
# docker run -d --name docker-backend-1 --hostname backend --net docker_network --net-alias docker-backend-1 --env-file /Users/danniel.kil/Documents/vscode/book-deploy/docker/.env -p 8081:8080 docker-backend
# docker run -d --name docker-frontend-1 --hostname frontend --net docker_network --net-alias docker-frontend-1 -p 80:80 docker-frontend


# Dockerfile 배포 
# docker build -t docker-db .
# docker run -d --name docker-db-1 -v ./db/store:/var/lib/mysql -p 3307:3306 docker-db
# docker build -t docker-backend .
# docker run -d --name docker-backend-1 --env-file /Users/danniel.kil/Documents/vscode/book-deploy/docker/.env -p 8081:8080 docker-backend
# docker build -t docker-frontend .
# docker run -d --name docker-frontend-1 -p 80:80 docker-frontend



# ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}
# ENV SPRING_DATASOURCE_DRIVER=${SPRING_DATASOURCE_DRIVER}
# ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
# ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}
# ENV SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/metadb?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true
# ENV SPRING_DATASOURCE_DRIVER=com.mysql.cj.jdbc.Driver
# ENV SPRING_DATASOURCE_USERNAME=root
# ENV SPRING_DATASOURCE_PASSWORD=root1234

# RUN은 현재 파일을 위치를 기반으로 작동함
# RUN chmod +x ./gradlew
# RUN ./gradlew clean build

# ENV JAR_PATH=/app/build/libs
# RUN mv ${JAR_PATH}/*.jar /app/app.jar
RUN mv ./target/book-0.0.1-SNAPSHOT.jar /app/book-backend.jar
# RUN mv /Users/danniel.kil/Documents/vscode/book-deploy/docker/backend/book-0.0.1-SNAPSHOT.jar /app/book-backend.jar


# ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "book-backend.jar"]
ENTRYPOINT ["java", "-jar", "book-backend.jar"]

# docker compose up -d 로 인해 구성된 네트워크에 다시 넣으면서 컨테이너 올리는 명령어
# 안됨 >>> 네트워크에 등록이 안됨
# docker run -d --name ex09-backend-1 --net ex09_network --net-alias backend -p 8081:8080 ex09-backend
# 수정
# docker run -d --name ex09-backend-1 --net ex09_network --net-alias backend -p 8081:8080 ex09-backend
# docker run -d --name ex09-backend-1 -p 8081:8080 ex09-backend
# docker run -d -p 8081:8080 ex09-backend
# docker network connect ex09_network [컨테이너ID]
# docker start [컨테이너ID]
# docker run -d --name docker-backend-1 -p 8081:8080 docker-backend

# 1) 컨테이너 삭제 후 재빌드
# 2) 이미지까지 삭제 후 다운로드 미 재빌드

# 2024.06.24 먼가 계속 안됨 

# 2024.06.25 backend Dockerfile에 환경변수 설정한 상태로 compose로 올리고 network 재설정 테스트 했더니 아무문제 없이 잘됨....

# 환경변수 확인(컨테이너가 정상적으로 올라가도 확인되는 환경변수가 없음)
# 1) echo $SPRING_DATASOURCE_URL
# 2) env 명렁어 >>> env | grep SPRING 
# 3) .env 파일 생성 >>> Compose 파일이 위치한 동일 경로에 생성
# Docker Network : 
# https://rimo.tistory.com/27