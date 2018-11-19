#!/bin/bash

#top | awk -F"\," '/%Cpu/ {print $4}'


#top -bn1 | awk -F"\," '/%Cpu/ {print $4}' | awk '{print $1}'

#top -bn2 | awk '/^top -/ { p=!p } { if (!p) print }' | awk -F"\," '/%Cpu/ {print $4}' | awk '{print $1}'


top -bn2 | awk '/^top -/ { p=!p } { if (!p) print }' | awk -F"\," '/%Cpu/ {print $1,$2,$4,$5}' | awk '{print $2,$4,$6,$8}'
