
#!/bin/bash

echo "tcp"
netstat -tulpn | grep tcp | awk '{print $4, $7}' | awk -F'/' '{print $1, $2}' | awk '{print $1, $3}' | awk -F":" '{print $NF}' | sort -n | uniq


echo "udp"
netstat -tulpn | grep udp | awk '{print $4, $6}' | awk -F'/' '{print $1, $2}' | awk '{print $1, $3}' | awk -F":" '{print $NF}' | sort -n | uniq

