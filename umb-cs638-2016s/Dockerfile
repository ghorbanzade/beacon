FROM ubuntu:trusty as builder
LABEL maintainer="pejman@ghorbanzade.com"
LABEL stage=intermediate
ENV DEBIAN_FRONTEND noninteractive
ARG VERBOSE_BUILD_LOGS
ENV VERBOSE_BUILD_LOGS ${VERBOSE_BUILD_LOGS:-false}

RUN apt-get update \
  && apt-get install -y --no-install-recommends \
    ghostscript make r-base \
    texlive-latex-base texlive-latex-extra latex-beamer \
  && rm -rf /var/lib/apt/lists/*

RUN groupadd -r pejman && useradd -u 8002 --no-log-init -r -g pejman pejman

COPY . /opt/umb-cs638-2016s

RUN chown -R pejman:pejman /opt
USER pejman

RUN make -C /opt/umb-cs638-2016s

# ---- production ---

FROM alpine:3.8
COPY --from=builder /opt/bin /opt/bin
