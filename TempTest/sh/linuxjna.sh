cd
cd TempTest
export LD_LIBRARY_PATH=/root/TcComm/com/pkm/linuxjna/irir
export PATH=$JAVA_HOME/jre/bin:$PATH
export CLASSPATH=$JAVA_HOME/jre/lib:$CLASSPATH
for loop in `ls /root/TempTest/lib/*.jar`;do
export CLASSPATH=${loop}:${CLASSPATH}
done
echo "build begin......"
$JAVA_HOME/bin/javac -encoding utf8 com/pkm/linuxjna/irir/TcIrisJNA.java
echo "build end......"
echo "Batching"
export LANG=en_US.UTF8
java -server -Xms1200m -Xmx1500m -XX:MaxNewSize=128m com.pkm.linuxjna.irir.TcIrisJNA