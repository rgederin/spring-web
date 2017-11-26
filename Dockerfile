FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD /target/recipe.jar recipe.jar
RUN sh -c 'touch /recipe.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /recipe.jar" ]