#!/bin/sh

set -e

mkdir -p secrets

cd secrets

openssl genpkey -aes-256-cbc -algorithm RSA -out PRIVATE_KEY.pem -pkeyopt rsa_keygen_bits:4096

openssl req -key PRIVATE_KEY.pem -new -x509 -days 365 -out CERTIFICATE_CHAIN.crt

openssl rsa -in PRIVATE_KEY.pem -out PRIVATE_KEY.pem
