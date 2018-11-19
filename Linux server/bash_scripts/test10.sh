#!/bin/bash


lsblk -nd | awk '{print $1}'
