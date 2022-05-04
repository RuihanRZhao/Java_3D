# shellcheck disable=SC2164
mkdir build
cd src
cp -rv image ../build
cp -rv storage ../build
javac Craft.java -d ../build
cd ../build
java Craft



