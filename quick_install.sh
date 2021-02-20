#!/bin/bash

version=v0.1-rc
vegahome=/opt
au=`uname  -a`
long_bit=`getconf LONG_BIT`

macos="Darwin"
ost=linux
rel="jre1.8-linux-x64.tar.gz"
oscode=601992

if [[ $au =~ $macos ]];then
    rel="jre1.8-macos.tar.gz"
    ost=macos
    oscode=601994
fi
packcode=616299
baseurl=https://gitee.com/openproclouder/vegaops-core/attach_files

if [ "$long_bit" != "64" ]; then
    echo "VegaOps only support x64 linux/mac."
    exit 1
fi

echo "Downloading vegaops $rel ..."
if [ ! -f $rel ]; then
    curl -L -o $rel $baseurl/$oscode/download/$rel
fi

if [ ! -f vegaops-${version}.tar.gz ]; then
    curl -L -o vegaops-${version}.tar.gz $baseurl/$packcode/download/vegaops-${version}.tar.gz
fi

echo "Install vegaops to $vegahome/vegaops"
if [ -d $vegahome/vegaops-$version-$ost ]; then
    echo "$vegahome/vegaops-$version-$ost already exists!"
    exit 1
fi
sudo tar -xvf vegaops-${version}.tar.gz -C $vegahome/

if [ -d $vegahome/vegaops ]; then
    echo "$vegahome/vegaops already exists!"
fi

sudo mv $vegahome/vegaops-${version} $vegahome/vegaops
sudo tar -xvf $rel -C $vegahome/vegaops/
sudo chmod a+x $vegahome/vegaops/bin/vegaops
sudo ln -s $vegahome/vegaops/bin/vegaops /usr/local/bin/vegaops

echo "Install vegaops successful, use vegaops cli to begin your journey!"
