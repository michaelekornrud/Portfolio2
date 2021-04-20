--- DOCKER  COCOMMANDS --- 

-- docker -v                                                            Checks version of docker
-- docker build -f Dockerfile -t docker-spring-boot .                   Builds container 
-- docker images                                                        Lists all containers 
-- docker run -p 8080:8080 docker-spring-boot                           Starts docker container   
-- docker login                                                         Log in to docker hub
-- docker tag docker-spring-boot michaelekornrud/docker-spring-boot     Creates a tag 
-- docker push michaelekornrud/docker-spring-boot                       Pushes latest tag to docker.io


--- OTHER COMMANDS ---

-- mvn clean -e spring-boot:run 