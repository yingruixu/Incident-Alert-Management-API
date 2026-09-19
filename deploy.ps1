mvn clean package -DskipTests

docker build -t incident-backend:dev .

.\k3d-windows-amd64.exe  image import incident-backend:dev -c mycluster

kubectl rollout restart deployment backend -n incident

kubectl rollout status deployment backend -n incident