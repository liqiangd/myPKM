cd
cd TcComm
export LD_LIBRARY_PATH=/root/TcComm/com/teso/finger/fingerMatchClient
export PATH=$JAVA_HOME/jre/bin:$PATH
export CLASSPATH=$JAVA_HOME/jre/lib:$CLASSPATH
for loop in `ls /root/TcComm/lib/*.jar`;do
export CLASSPATH=${loop}:${CLASSPATH}
done
echo "build begin......"
$JAVA_HOME/bin/javac -encoding utf8 com/teso/finger/fingerMatchClient/TcResult.java
$JAVA_HOME/bin/javac -encoding utf8 com/teso/finger/fingerMatchClient/TcCommDriver.java
$JAVA_HOME/bin/javac -encoding utf8 com/teso/finger/fingerMatchClient/TcCommDriverTest.java
echo "build end......"
echo "Batching"
export LANG=en_US.UTF8
java -server -Xms1200m -Xmx1500m -XX:MaxNewSize=128m com.teso.finger.fingerMatchClient.TcCommDriverTest