#!/bin/bash

set -exa

cd "$INPUT_SBT_PROJECT_DIRECTORY"

set +x
if [ ! -f build.sbt ]; then
  echo "No build.sbt present, exiting"
  exit 1
fi

if [ ! -d ./project/ ]; then
  mkdir project
fi

set -x
/usr/bin/sbt "${@}"

#echo ::set-output name=time::$time
