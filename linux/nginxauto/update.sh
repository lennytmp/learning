#!/usr/bin/env bash

# Step 1: Adding new configurations
echo "Checking if new sites are added"

declare -A configs

while read -r line
do
  configs[$line]=1
done < <(ls -1 /etc/nginx/sites-available/)

while read -r line
do
  if [ -n "${configs[$line]+_}" ]; then
    echo "$line - config found"
  else
    echo "$line - config not found, generating"
    /home/andrey/nginxauto/addsite.sh $line
  fi
done < <(ls /srv/www)

echo "==========="
# Step 2: Removing configs that don't have sites anymore 
echo "Checking if useless configs are present"

declare -A sites 

while read -r line
do
  sites[$line]=1
done < <(ls -1 /srv/www/)

while read -r line
do
  if [ -n "${sites[$line]+_}" ]; then
    echo "$line - corresponding site found"
  else
    echo "$line - no such site, removing"
    /home/andrey/nginxauto/rmsite.sh $line
  fi
done < <(ls /etc/nginx/sites-available/)

