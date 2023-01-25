#!/bin/bash

ssh -i /var/jenkins_home/ssh/dev chifu@152.168.254.151:9283
podman info
whoami