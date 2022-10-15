REPOSITORY=/home/ubuntu/app
cd $REPOSITORY

JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

sudo lsof -t -i tcp:80 -s tcp:listen | sudo xargs kill

echo "> $JAR_PATH 배포"
sudo nohup java -jar $REPOSITORY/build/libs/Elecvery-Clone-Backend-0.0.1-SNAPSHOT.jar > /home/ubuntu/log 2> /home/ubuntu/error &
-0.0.1-SNAPSHOT