#!/bin/bash

# Runs all python files for generating emoji metadata

echo "Parsing emoji.json"
python3 parse.py

echo "Filling database"
python3 fill_database.py

echo "Optimizing"
python3 optimize.py

echo "Finished"
