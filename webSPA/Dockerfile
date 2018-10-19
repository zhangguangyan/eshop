FROM httpd:2.4
RUN mkdir -p /etc/apache2/conf-enabled && echo 'PassEnv API_GATEWAY' > /etc/apache2/conf-enabled/expose-env.conf
COPY ./apache-proxy.conf /usr/local/apache2/conf/httpd.conf
COPY ./dist/ /usr/local/apache2/htdocs/