FROM ubuntu:trusty as builder
LABEL maintainer="pejman@ghorbanzade.com"
LABEL stage=intermediate
ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update \
  && apt-get install -y --no-install-recommends \
    ghostscript make pgf \
    texlive-latex-base texlive-latex-extra texlive-generic-recommended \
  && rm -rf /var/lib/apt/lists/*

RUN groupadd -r pejman && useradd -u 8002 --no-log-init -r -g pejman pejman

COPY . /opt/umb-cs630-2014f

RUN chown -R pejman:pejman /opt
USER pejman

RUN make -C /opt/umb-cs630-2014f

# ---- production ---

FROM alpine:3.8
COPY --from=builder /opt/bin /opt/bin
