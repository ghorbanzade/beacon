FROM openjdk:8-slim as builder
LABEL maintainer="pejman@ghorbanzade.com"
LABEL stage=intermediate
ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update \
  && apt-get install -y --no-install-recommends \
    ant ant-optional \
  && rm -rf /var/lib/apt/lists/*

RUN groupadd -r pejman && useradd -m -u 8002 --no-log-init -r -g pejman pejman

COPY . /opt/umb-cs681-2016s

RUN chown -R pejman:pejman /opt
USER pejman

RUN ant -buildfile /opt/umb-cs681-2016s/build.xml init-ivy \
  && ant -buildfile /opt/umb-cs681-2016s/build.xml

# ---- production ---

FROM alpine:3.8
COPY --from=builder /opt/build /opt/build
