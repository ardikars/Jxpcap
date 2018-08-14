
Jxnet
=====

Jxnet is a Java library for capturing and sending custom network packet buffers with no copies.
Jxnet wraps a native packet capture library (libpcap/npcap) via JNI (Java Native Interface).

[![CircleCI](https://circleci.com/gh/jxnet/Jxnet/tree/master.svg?style=svg)](https://circleci.com/gh/jxnet/Jxnet/tree/master)
[![Build status](https://ci.appveyor.com/api/projects/status/xqfqnfc3y5jh8dr8/branch/master?svg=true)](https://ci.appveyor.com/project/ardikars/jxnet)

[ ![Download](https://api.bintray.com/packages/ardikars/maven/com.ardikars.jxnet/images/download.svg?version=1.1.4) ](https://bintray.com/ardikars/maven/com.ardikars.jxnet/1.1.4/link)


PLATFORM
========

  - Windows 7/8/10 (32/64 bit)
  - Linux (x86/x64)
  - MacOS


HOW TO BUILD
============

Windows

Install Mingw64 (sjlj) for cross compilation on windows system. See ```.\.scripts\InstallMingw64.ps1```.

Linux

Install libpcap-dev

MacOS

Install libpcap-dev

```sh
./gradlew build -x test
```


HOW TO USE
==========

#### Gradle project ####
Add a dependency to the build.gradle as like below:

```
...
dependencies {
  compile 'com.ardikars.jxnet:jxnet-core:1.1.4'
  compile 'com.ardikars.jxnet:jxnet-packet:1.1.4'
  compile 'com.ardikars.jxnet:jxnet-util:1.1.4'
  ...
}
...
```

*) In Windows you need to install Npcap with WinPcap API-compatible Mode. See ```.\.scripts\InstallNpcap.ps1```.


License
=======

GNU Lesser General Public License, Version 3


Contact
=======

Email: Ardika Rommy Sanjaya (contact@ardikars.com)


Issues
======

Have a bug? Please create an issue here on GitHub!

https://github.com/ardikars/Jxnet/issues

