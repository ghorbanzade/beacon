FROM ubuntu:trusty as builder
LABEL maintainer="pejman@ghorbanzade.com"
LABEL stage=intermediate
ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update \
  && apt-get install -y --no-install-recommends \
    g++ gfortran ghostscript liblapack-dev libopenblas-dev \
    make pgf r-base \
    texlive-latex-base texlive-latex-extra texlive-science wget \
  # we won't ever need lists
  && rm -rf /var/lib/apt/lists/* \
  && wget http://cran.r-project.org/src/contrib/kernlab_0.9-27.tar.gz \
  && R CMD INSTALL kernlab_0.9-27.tar.gz

RUN groupadd -r pejman && useradd -u 8002 --no-log-init -r -g pejman pejman

COPY . /opt/umb-cs671-2015s

RUN chown -R pejman:pejman /opt
USER pejman

RUN make -C /opt/umb-cs671-2015s

# ---- production ---

FROM alpine:3.8
COPY --from=builder /opt/bin /opt/bin
