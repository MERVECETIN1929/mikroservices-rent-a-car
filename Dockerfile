FROM ubuntu:latest
LABEL authors="merve"

ENTRYPOINT ["top", "-b"]