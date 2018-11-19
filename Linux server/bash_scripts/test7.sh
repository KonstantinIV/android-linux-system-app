#!/bin/bash





ifstat -i eth0 -q 1 1 | tail -1 | awk '{print $1,$2}'

