FROM openjdk:7-slim as builder
LABEL maintainer="pejman@ghorbanzade.com"
LABEL stage=intermediate
ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update \
  && apt-get install -y --no-install-recommends \
    ant ant-optional make texlive-latex-base \
  # we won't ever need lists
  && rm -rf /var/lib/apt/lists/*

RUN groupadd -r pejman && useradd -m -u 8002 --no-log-init -r -g pejman pejman

COPY . /opt/umb-cs680-2015f

RUN chown -R pejman:pejman /opt
USER pejman

RUN make -C /opt/umb-cs680-2015f

# ---- production ---

FROM alpine:3.8
COPY --from=builder /opt/bin /opt/bin
