#!/bin/bash

version=v0.1-rc
baseurl=https://github.com/vegaops/vegaops-core/releases/download/$version
vegahome=/opt
au=`uname  -a`

macos="Darwin"
ost=linux
rel="vegaops-$version-linux.tar.gz"

if [[ $au =~ $macos ]];then
    rel="vegaops-$version-macos.tar.gz"
    ost=macos
fi

echo "Downloading vegaops vegaops-$version-$ost.tar.gz ..."
if [ ! -f $rel ]; then
    curl -o $rel $baseurl/$rel
fi

echo "Install vegaops to $vegahome/vegaops"
if [ -d $vegahome/vegaops-$version-$ost ]; then
    echo "$vegahome/vegaops-$version-$ost already exists!"
    exit 1
fi
sudo tar -xvf vegaops-v0.1*.tar.gz -C $vegahome/

if [ -d $vegahome/vegaops ]; then
    echo "$vegahome/vegaops already exists!"
fi

sudo mv "$vegahome/vegaops-$version-$ost" $vegahome/vegaops

sudo chmod a+x $vegahome/vegaops/bin/vegaops
sudo ln -s $vegahome/vegaops/bin/vegaops /usr/local/bin/vegaops

echo "Install vegaops successful, use vegaops cli to begin your journey!"
