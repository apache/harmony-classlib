#!/bin/sh
svn status --verbose $1 | sed -n 's/[^0-9]*\([0-9]*\)[^0-9]*\([0-9]*\).*/revision r\1\/r\2/p'
