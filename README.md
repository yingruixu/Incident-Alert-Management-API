# Incident & Alert Management API

Lightweight Incident & Alert Management system with a Vue 3 frontend, Spring Boot backend, MySQL storage, and Prometheus/Grafana for monitoring.

## Features
- Dashboard: real-time stats, incident trend chart, severity distribution
- Incidents: CRUD, search, filter, status timeline
- Alerts: rule management, thresholds, enable/disable
- Monitoring: service CPU/Memory/Disk metrics (Prometheus)
- Auth: JWT-based login, token refresh, route guards

## Tech Stack
- Frontend: Vue 3, Vite, Element Plus, Pinia, Chart.js
- Backend: Java 17, Spring Boot 3, Spring Data JPA
- Database: MySQL 8
- Monitoring: Prometheus + Grafana
- Container: Docker / Docker Compose
- Build: Maven

## Quickstart (recommended: Docker)
Prerequisites: Docker, Docker Compose, Java 17, Maven

1. Build backend artifact (skip tests for faster local start):

```bash
mvnw.cmd clean package -DskipTests
```

2. Start services with Docker Compose:

```bash
docker compose up -d
```

3. Access the apps:

- Frontend: http://localhost:3001 (login required)
- Backend API: http://localhost:8080
- Prometheus: http://localhost:9090
- Grafana: http://localhost:3000

## Local development

- Backend (hot reload):

```bash
cd src/main/java
mvnw spring-boot:run
```

- Frontend (dev server):

```bash
cd frontend
npm install
npm run dev
```

## Configuration
- Backend properties: see `src/main/resources/application.properties` for default values and database settings.
- Kubernetes manifests are in the `k8s/` folder. Use overlays for environment-specific configs (e.g., `k8s/overlays/prod`).

## Kubernetes
- A base set of manifests is available at `k8s/base/` for backend, frontend, MySQL, and ingress.
- To deploy to a cluster, adjust secrets (JWT key, DB credentials) and apply manifests with `kubectl apply -k k8s/overlays/<env>`.


## Project structure 

- frontend/: Vue 3 application (Vite)
- src/: Spring Boot backend source
- k8s/: Kubernetes manifests
- docker-compose.yml: local multi-service stack

プロジェクト名：Incident & Alert Management Dashboard
概要
このプロジェクトは、システムの障害（インシデント）とアラートを一元管理するためのWebアプリケーションです。バックエンドからフロントエンド、インフラ、CI/CD、監視まで、すべてを自分で設計・実装しました。

目的：DevOpsやSREのスキルを実践的に学び、クラウドネイティブな環境での運用を経験すること。

アーキテクチャ図（Architecture）
ここにアーキテクチャ図を入れてください（draw.ioなどで作成した画像）

全体の流れ：

text
ユーザー → フロントエンド（Vue.js） → バックエンド（Spring Boot） → MySQL
　　　　　　　　　　　　　　　　　　　　　　　　　↓
　　　　　　　　　　　　　　Prometheus（監視） → Grafana（可視化）
　　　　　　　　　　　　　　　　　　　　　　　　　　　↓
　　　　　　　　　　　　　　　　　　　　　　　　Loki（ログ収集）
使用している技術（Technology Stack）
カテゴリ	技術
バックエンド	Java 17, Spring Boot 3, Spring Data JPA, JWT認証
フロントエンド	Vue 3, Vite, Element Plus, Chart.js
データベース	MySQL 8
コンテナ	Docker, Docker Compose
CI/CD	Jenkins（Pipeline）
クラウド	AWS EC2
コンテナオーケストレーション	Kubernetes（K3s）
監視・可観測性	Prometheus, Grafana, Loki, Promtail
機能一覧（Features）
ダッシュボード：システム全体の状態を一目で確認できる。障害の数、アラートの数、CPU・メモリ使用率などを表示。

インシデント管理：障害の登録、ステータス更新、解決までの流れを管理できる。

アラート管理：Prometheusから受け取ったアラートを一覧表示し、対応状況を追跡できる。

監視画面：Grafanaのダッシュボードを埋め込み、JVMのメモリ使用量やHTTPリクエスト数などをリアルタイムで確認できる。

ユーザー認証：JWT（JSON Web Token）を使ったログイン機能。Spring Securityで保護。

プロジェクトの中で苦労したこと・解決したこと（Troubleshooting）
問題	原因	解決方法
JWT認証で403エラーが発生	環境変数からJWT_SECRETが正しく読み込めていなかった	System.getenv() で強制的に環境変数を読み込むように修正
MySQLに接続できない	データベースが作られていなかった / ユーザー権限が不足	CREATE DATABASE でDBを作成し、ユーザーに権限を付与
Nginxがバックエンドにプロキシできない	Service名が間違っていた / rewriteルールが不足	KubernetesのService名を修正し、rewriteルールを追加
CoreDNSが動かず、クラスタ内の名前解決ができない	CoreDNSのforward設定が間違っていた	ConfigMapを修正し、CoreDNS Podを再起動
Prometheusがバックエンドのメトリクスを取得できない（403エラー）	Spring Securityが/actuator/prometheusをブロックしていた	SecurityConfigで/actuator/prometheusをpermitAll()に設定
K3dにDockerイメージをインポートできない（digest not foundエラー）	k3dの仕様による問題	docker save + ctr images import で手動インポート
Kubernetes構成（Kubernetes Configuration）
K3sクラスタ上で、以下のマニフェスト（YAML）を使ってデプロイしています。

リソース	用途
Namespace	incident 名前空間にすべてのリソースを隔離
Deployment	バックエンド（2レプリカ）、フロントエンド（2レプリカ）
Service	ClusterIPで内部通信
Ingress	外部からのアクセスをバックエンドとフロントエンドに振り分け
ConfigMap	アプリケーションの設定ファイルを管理
Secret	データベースのパスワードやJWT_SECRETを管理
StatefulSet	MySQLのデータ永続化（PVC付き）
HPA（Horizontal Pod Autoscaler）	CPU使用率に応じてPodを自動的に増減
PDB（Pod Disruption Budget）	メンテナンス時に最低1つのPodが常に稼働するように保証
CI/CDパイプライン（Jenkins Pipeline）
コードをGitHubにプッシュ

Jenkinsが自動的に検知

Mavenでビルド → Dockerイメージを作成

DockerイメージをDocker Hubにプッシュ

KubernetesのDeploymentを更新（kubectl set image）

ロールバックが必要な場合は、前のイメージに戻す

どうやって動かすか（How to Run）
必要なもの：

Docker & Docker Compose

Kubernetesクラスタ（K3d推奨）

Jenkins（CI/CD用）

ローカルで動かす場合：

bash
# リポジトリをクローン
git clone https://github.com/yingruixu/Incident-Alert-Management-AP.git

# Docker Composeで起動
cd incident-api
docker-compose up -d

# ブラウザでアクセス
# フロントエンド: http://localhost
# バックエンドAPI: http://localhost:8080/api
# Prometheus: http://localhost:9090
# Grafana: http://localhost:3000
これから追加したい機能（Future Plans）
ArgoCD：GitOpsによるデプロイ自動化

Terraform：AWSインフラのコード化

Alertmanager：PrometheusのアラートをSlackやメールに通知

cert-manager：HTTPS対応（Let's Encrypt）

Loki + Promtail：ログの一元管理（すでに一部導入済み）
