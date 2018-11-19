#!/bin/bash

target="/root/scripts"
let count=0
for f in "$target"/*
do
    echo $(basename $f)
done
