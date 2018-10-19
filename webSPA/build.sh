#!/bin/bash
rm -fr dist && npm run build && docker build -t webspa .
