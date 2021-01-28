#!/bin/bash

version=v0.1-rc
baseurl=https://github.com/vegaops/vegaops-core/releases/download/$version
vegahome=/opt
au=`uname  -a`
long_bit=`getconf LONG_BIT`

macos="Darwin"
ost=linux
rel="vegaops-$version-linux-x64.tar.gz"

if [[ $au =~ $macos ]];then
    rel="vegaops-$version-macos.tar.gz"
    ost=macos
fi

if [ "$long_bit" != "64" ]; then
    echo "VegaOps only support x64 linux/mac."
    exit 1
fi

echo "Downloading vegaops $rel ..."
if [ ! -f $rel ]; then
    curl -L -o $rel $baseurl/$rel
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
