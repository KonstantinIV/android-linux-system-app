#!/bin/bash

#df -h
#lsblk
#lsblk -l | awk '{ print $1,$4,$6,$7 }'
df -h | grep ^/dev | awk '{ print $1,$2,$3,$4,$6 }'

