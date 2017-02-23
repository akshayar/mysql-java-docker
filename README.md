# mysql-java-docker
Experiments with Docker. Creating a simple web service which connects to MySQL. The intent is to dockerize and compare the performance and other metrics agains that of Docker. 

# Docker Learning Path
## Pluralsight has some great resources to learn Docker 

  Learning Path "Container Management using Docker" https://app.pluralsight.com/paths/skills/docker
  
  "Play by Play: Docker for Java Developers with Arun Gupta and Michael Hoffman" https://app.pluralsight.com/library/courses/play-by-play-docker-java-developers-arun-gupta-michael-hoffman/table-of-contents
  
  "Containers and Images: The Big Picture" https://app.pluralsight.com/library/courses/containers-images-big-picture/table-of-contents
  
## Docker MySQL 
  "Docker MySQL" https://hub.docker.com/_/mysql/
  
## Docker Maven Plugin
  https://dmp.fabric8.io/
  
## Docker Machine
  https://docs.docker.com/machine/get-started/
  
# Issues Faced
# Lessons Learned
# Open questions
1. 2 Sets of configuraitons. One for Docker and another for VM. It should support running integration testing after install. 
2. How to build the pipeline ? It should support DEV, QA and PROD installation after integration test. How to give 3 sets of docker images one for DEV , one for QA and another for prod. The deployment configuration could be different in these environments. For example PROD would have clustering , but DEV may not. What is the best approach of achieving this. 
3. How to support native clustering? SWARM ?
4. How to build a system with multiple micro-services? How to support multiple services and their integration?
5. How to buid monitoring dashboard?
6. How to package properties file outside of Jar ? And create image. 
7. 
