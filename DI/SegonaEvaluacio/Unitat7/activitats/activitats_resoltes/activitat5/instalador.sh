#!/bin/sh
# Build executable
source ../venv/bin/activate
[ -e build ] && rm -r build
[ -e dist ] && rm -r dist
[ -e hello-world.spec ] && rm hello-world.spec
[ -e hello-world.rpm ] && rm hello-world.rpm
pyinstaller --onefile -n "hello-world" --add-data="resources:resources" -w hello_world.py

# Create folders.
[ -e pkg ] && rm -r pkg
mkdir -p pkg/opt/hello-world
mkdir -p pkg/usr/share/applications
mkdir -p pkg/usr/share/icons/hicolor/scalable/apps

# Copy executable file
cp -r "dist/hello-world" "pkg/opt/hello-world"
# Copy icon file
cp -f resources/hello-world.svg pkg/usr/share/icons/hicolor/scalable/apps/hello-world.svg
# Copy desktop entry file
cp -f hello-world.desktop pkg/usr/share/applications

# Change permissions
find "pkg/opt/hello-world/hello-world" -type f -exec chmod 755 -- {} +
find pkg/usr/share -type f -exec chmod 644 -- {} +

# Create package
# fpm --verbose -C pkg -s dir -t rpm -n "hello-world" -v 0.0.1 -p hello-world.rpm
fpm -C pkg -s dir -t rpm -n "hello-world" -v 0.0.1 -p hello-world.rpm

# Install package
sudo dnf remove hello-world-0.0.1-1.x86_64
sudo dnf install hello-world.rpm