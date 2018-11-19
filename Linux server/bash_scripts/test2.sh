
#!/bin/bash

#df -h
#lsblk

 lsblk -ln | awk '{print $1, $4, $6, $7}'

