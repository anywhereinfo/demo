FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
EXPOSE 8080

ENV USER_NAME demo
ENV APP_HOME /home/$USER_NAME/app

RUN adduser -S $USER_NAME
RUN mkdir $APP_HOME

ADD ./build/libs/demo-*.jar $APP_HOME/app.jar
RUN chown $USER_NAME $APP_HOME/app.jar

USER $USER_NAME
WORKDIR $APP_HOME
RUN sh -c 'touch app.jar'

CMD echo "The application will start in 10..." && \
    sleep 10 && \
    java -Djava.security.egd=file:/dev/./urandom -jar app.jar