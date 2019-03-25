ARG FITNESSE_VERSION

FROM openjdk:8-jre-alpine
RUN adduser -D -u 666 fitnesse
RUN mkdir -p /opt/fitnesse
RUN wget -O "/opt/fitnesse/fitnesse-standalone.jar" "http://www.fitnesse.org/fitnesse-standalone.jar?responder=releaseDownload&release=20190224" && \
    chown fitnesse.fitnesse /opt/fitnesse
ADD start.sh /start.sh
RUN chmod o+x /start.sh
ENTRYPOINT ["/start.sh"]