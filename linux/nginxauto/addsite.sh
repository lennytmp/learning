#!/usr/bin/env bash

if [ -z "$1" ] ; then
  echo "No parameter specified"
  exit 0
fi

mkdir /etc/nginx/ssl/$1

openssl req -newkey rsa:2048 -nodes -keyout /etc/nginx/ssl/$1/domain.key \
  -x509 -days 365 -out /etc/nginx/ssl/$1/domain.crt \
  -subj "/C=US/ST=New York/L=Brooklyn/O=Example Brooklyn Company/CN=$1"

cat >/etc/nginx/sites-available/$1 <<EOF
server {
	listen 80;
	listen [::]:80; 
  listen 443 ssl;
	root /srv/www/$1;
	index index.html index.htm index.nginx-debian.html;
	server_name $1;
  ssl_certificate /etc/nginx/ssl/$1/domain.crt;
  ssl_certificate_key /etc/nginx/ssl/$1/domain.key;
	location / {
		try_files \$uri \$uri/ =404;
	}
}
EOF

ln -s /etc/nginx/sites-available/$1 /etc/nginx/sites-enabled/$1
service nginx reload
