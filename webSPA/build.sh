#!/bin/bash
rm -fr dist && npm i && npm run build && docker build -t webspa .
