FROM openjdk:15

WORKDIR /usrapp/bin

ENV PORT 8002

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","com.wesmart.stark.StarkApplication"]
