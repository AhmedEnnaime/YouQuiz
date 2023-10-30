run:
	@docker compose up -d
jenkins-build:
	@docker build -t myjenkins-blueocean:2.414.3-1 .
dind-run:
	@docker run \
       --name jenkins-docker \
       --rm \
       --detach \
       --privileged \
       --network youquiz \
       --network-alias docker \
       --env DOCKER_TLS_CERTDIR=/certs \
       --volume jenkins-docker-certs:/certs/client \
       --volume jenkins-data:/var/jenkins_home \
       --publish 2376:2376 \
       --publish 3000:3000 --publish 4000:4000 \
       docker:dind \
       --storage-driver overlay2
jenkins-run:
	@docker run \
       --name jenkins-blueocean \
       --detach \
       --network youquiz \
       --env DOCKER_HOST=tcp://docker:2376 \
       --env DOCKER_CERT_PATH=/certs/client \
       --env DOCKER_TLS_VERIFY=1 \
       --publish 8080:8080 \
       --publish 50000:50000 \
       --volume jenkins-data:/var/jenkins_home \
       --volume jenkins-docker-certs:/certs/client:ro \
       --volume "$HOME":/home \
       --restart=on-failure \
       --env JAVA_OPTS="-Dhudson.plugins.git.GitSCM.ALLOW_LOCAL_CHECKOUT=true" \
       myjenkins-blueocean:2.414.3-1
down:
	@docker compose down
test:
	mvn clean verify
clean:
	mvn clean install