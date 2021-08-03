FROM ubuntu:latest

RUN apt-get -y update && apt-get -y upgrade
RUN apt-get -y install \ 
    net-tools \ 
    build-essential \
    vim \
    git \
    libpcre3-dev \
    libssl-dev \
    zlib1g-dev

WORKDIR /

RUN git clone https://github.com/arut/nginx-rtmp-module.git
RUN git clone https://github.com/nginx/nginx.git
WORKDIR /nginx
RUN ./auto/configure --add-module=../nginx-rtmp-module
RUN make
RUN make install

# As no prefix is specified it will be installed on /usr/local/nginx/

RUN /usr/local/nginx/sbin/nginx -t

RUN mkdir -p /var/www
RUN mkdir -p /var/mp4s

EXPOSE 80
EXPOSE 8080
EXPOSE 443
EXPOSE 1935


CMD ["/usr/local/nginx/sbin/nginx", "-g", "daemon off;"]
# CMD ["tail", "-f", "/dev/null"]