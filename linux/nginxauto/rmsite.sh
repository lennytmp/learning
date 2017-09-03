#!/usr/bin/env bash

if [ -z "$1" ] ; then
  echo "No parameter specified"
  exit 0
fi

rm /etc/nginx/sites-available/$1
rm /etc/nginx/sites-enabled/$1
rm -rf /etc/nginx/ssl/$1
