cd C:\Users\shaun\Downloads\ntchat\incident-api

# 1) 后端打包
.\mvnw clean package -DskipTests

# 2) 构建镜像
docker build -t shaun/backend:v1.0.6 .

# 3) 前端打包并构建镜像
cd frontend
npm install
npm run build
docker build -t shaun/frontend:v1.0.5 .

# 4) 导入到 k3d 集群
cd ..
.\k3d-windows-amd64.exe image import shaun/backend:v1.0.6 -c mycluster
.\k3d-windows-amd64.exe image import shaun/frontend:v1.0.5 -c mycluster

# 5) 应用 k8s 配置
kubectl apply -f k8s/base/namespace.yaml
kubectl apply -f k8s/base/backend -f k8s/base/frontend -f k8s/base/mysql -f k8s/base/ingress.yaml

# 6) 滚动更新
kubectl rollout restart deployment backend frontend -n incident
kubectl rollout status deployment backend -n incident --timeout=180s
kubectl rollout status deployment frontend -n incident --timeout=180s


git add .
git commit -m "feat: add dynamic user info in header and dashboard"
git push