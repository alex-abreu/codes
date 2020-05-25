######################################
# INSTALL OPENCV ON UBUNTU
######################################

# |          THIS SCRIPT IS TESTED CORRECTLY ON          |
# |------------------------------------------------------|
# | OS               | OpenCV       | Test | Last test   |
# |------------------|--------------|------|-------------|
# | Ubuntu 12.04 LTS | OpenCV 2.4.12| OK   |  May 2019   |


# VERSION TO BE INSTALLED

OPENCV_VERSION='2.4.12'


# 1. KEEP UBUNTU OR DEBIAN UP TO DATE

sudo  apt-get -y update
sudo  apt-get -y upgrade       # Uncomment this line to install the newest versions of all packages currently installed
# sudo apt-get -y dist-upgrade  # Uncomment this line to, in addition to 'upgrade', handles changing dependencies with new versions of packages
# sudo apt-get -y autoremove    # Uncomment this line to remove packages that are now no longer needed


# 2. INSTALL THE DEPENDENCIES

sudo  apt-get install -y build-essential checkinstall cmake pkg-config yasm

sudo  apt-get install -y git gfortran

sudo  apt-get install -y libjpeg8-dev libjasper-dev libpng12-dev

sudo  apt-get install -y libtiff4-dev

sudo  apt-get install -y libavcodec-dev libavformat-dev libswscale-dev libdc1394-22-dev

sudo  apt-get install -y libxine2-dev libv4l-dev

sudo  apt-get install -y libgstreamer0.10-dev libgstreamer-plugins-base0.10-dev

sudo  apt-get install -y qt4-qmake

sudo  apt-get install -y libqt4-dev

sudo  apt-get install -y libgtk2.0-dev libtbb-dev

sudo  apt-get install -y libatlas-base-dev

sudo  apt-get install -y libfaac-dev libmp3lame-dev libtheora-dev

sudo  apt-get install -y libvorbis-dev libxvidcore-dev

sudo  apt-get install -y libopencore-amrnb-dev libopencore-amrwb-dev

sudo  apt-get install -y x264 v4l-utils

# Python:
sudo apt-get install -y python-dev python-tk python-numpy python3-dev python3-tk python3-numpy

# Java:
sudo apt-get install -y ant default-jdk

# Documentation:
sudo apt-get install -y doxygen


# 3. INSTALL THE LIBRARY

sudo apt-get install -y unzip wget
wget https://github.com/Itseez/opencv/archive/2.4.12.zip
unzip ${OPENCV_VERSION}.zip
rm ${OPENCV_VERSION}.zip
mv opencv-${OPENCV_VERSION} OpenCV
cd OpenCV
mkdir build
cd build
cmake -DWITH_QT=ON -DWITH_OPENGL=ON -DFORCE_VTK=ON -DWITH_TBB=ON -DWITH_GDAL=ON -DWITH_XINE=ON -DBUILD_EXAMPLES=ON -DENABLE_PRECOMPILED_HEADERS=OFF ..
make
sudo make install
sudo ldconfig
