#!/bin/bash

service --status-all | awk '{print $2, $4}'
